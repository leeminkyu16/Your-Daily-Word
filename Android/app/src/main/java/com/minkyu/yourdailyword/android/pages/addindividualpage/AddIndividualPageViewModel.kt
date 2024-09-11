package com.minkyu.yourdailyword.android.pages.addindividualpage

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.minkyu.yourdailyword.android.R
import com.minkyu.yourdailyword.android.infrastructure.YdwViewModel
import com.minkyu.yourdailyword.android.infrastructure.combine
import com.minkyu.yourdailyword.android.models.IQuotesManager
import com.minkyu.yourdailyword.android.services.INavigationService
import com.minkyu.yourdailyword.android.services.ISnackbarService
import com.minkyu.yourdailyword.common.protobased.MultilingualStringModel
import com.minkyu.yourdailyword.common.protobased.QuoteGregorianCalendarOptionsModel
import com.minkyu.yourdailyword.common.protobased.QuoteHebrewCalendarOptionsModel
import com.minkyu.yourdailyword.common.protobased.QuoteLunarCalendarOptionsModel
import com.minkyu.yourdailyword.common.protobased.QuoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddIndividualPageViewModel @Inject constructor(
	private val navigationService: INavigationService,
	private val quotesManager: IQuotesManager,
	private val snackbarService: ISnackbarService,
): YdwViewModel<AddIndividualPageUiState>() {
	val navHostController: NavHostController get() = navigationService.navHostController

	val englishQuoteTextStream: MutableStateFlow<String> = MutableStateFlow(
		value = "",
	)
	val associatedMonthTextStream: MutableStateFlow<String> = MutableStateFlow(
		value = "",
	)
	val associatedDayOfMonthTextStream: MutableStateFlow<String> = MutableStateFlow(
		value = "",
	)
	val gregorianCalendarOptionsModelStream: MutableStateFlow<QuoteGregorianCalendarOptionsModel?> = MutableStateFlow(
		value = null,
	)
	val lunarCalendarOptionsModelStream: MutableStateFlow<QuoteLunarCalendarOptionsModel?> = MutableStateFlow(
		value = null,
	)
	val hebrewCalendarOptionsModelStream: MutableStateFlow<QuoteHebrewCalendarOptionsModel?> = MutableStateFlow(
		value = null,
	)

	override val uiStateStream: Flow<AddIndividualPageUiState> = combine(
		englishQuoteTextStream,
		associatedMonthTextStream.map {
	    	it.replace(
				regex = Regex("[^0-9., ]"),
				replacement = "",
			)
		}
			.flowOn(Dispatchers.Default),
		associatedDayOfMonthTextStream.map {
			it.replace(
				regex = Regex("[^0-9., ]"),
				replacement = "",
			)
		}
			.flowOn(Dispatchers.Default),
		gregorianCalendarOptionsModelStream,
		lunarCalendarOptionsModelStream,
		hebrewCalendarOptionsModelStream,
	) { quoteText: String,
		associatedMonth: String,
		associatedDayOfMonth: String,
		gregorianOptionsModel: QuoteGregorianCalendarOptionsModel?,
		lunarOptionsModel: QuoteLunarCalendarOptionsModel?,
		hebrewOptionsModel: QuoteHebrewCalendarOptionsModel? ->
		AddIndividualPageUiState.Loaded(
			englishQuoteText = quoteText,
			associatedMonthText = associatedMonth,
			associatedDayOfMonthText = associatedDayOfMonth,
			gregorianCalendarOptionsModel = gregorianOptionsModel,
			lunarCalendarOptionsModel = lunarOptionsModel,
			hebrewCalendarOptionsModel = hebrewOptionsModel,
		)
	}

	fun onAddAndSave(
		uiState: AddIndividualPageUiState.Loaded,
	) {
		viewModelScope.launch(Dispatchers.Default) {
			var associatedMonth = 0
			var associatedDayOfMonth = 0
			runCatching {
				assert(uiState.englishQuoteText.isNotBlank())
				associatedMonth = uiState.associatedMonthText.toInt()
				associatedDayOfMonth = uiState.associatedDayOfMonthText.toInt()
			}
				.onSuccess {
					quotesManager.addQuote(
						QuoteModel(
							quotesManager.getNewUid(),
							MultilingualStringModel(
								uiState.englishQuoteText,
							),
							associatedMonth,
							associatedDayOfMonth,
							uiState.gregorianCalendarOptionsModel,
							uiState.lunarCalendarOptionsModel,
							uiState.hebrewCalendarOptionsModel,
						)
					)

					withContext(Dispatchers.IO) {
						quotesManager.saveToDefaultFile()
					}

					snackbarService.showMessage(
						messageStringRes = R.string.add_quote_success_message,
					)
					withContext(Dispatchers.Main) {
						navHostController.popBackStack()
					}
				}
				.onFailure {
					snackbarService.showMessage(
						messageStringRes = R.string.add_quote_failure_message,
					)
				}
		}
	}
}
