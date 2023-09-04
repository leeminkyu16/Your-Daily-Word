package com.minkyu.yourdailyword.android.pages.editindividualpage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
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
import com.minkyu.yourdailyword.common.protos.Quote
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
class EditIndividualPageViewModel @Inject constructor(
	private val navigationService: INavigationService,
	private val quotesManager: IQuotesManager,
	private val snackbarService: ISnackbarService,
	savedStateHandle: SavedStateHandle,
): YdwViewModel<EditIndividualPageUiState>() {
	val navHostController: NavHostController get() = navigationService.navHostController
	private val quoteId: Int = checkNotNull(savedStateHandle[QUOTE_ID_KEY])
	private val quoteModel: QuoteModel = quotesManager.getQuote(quoteId) ?: QuoteModel.fromProto(
		Quote.newBuilder().build(),
	)

	val englishQuoteTextStream: MutableStateFlow<String> = MutableStateFlow(
		value = quoteModel.value.english
	)
	val associatedMonthTextStream: MutableStateFlow<String> = MutableStateFlow(
		value = quoteModel.associatedMonth.toString(),
	)
	val associatedDayOfMonthTextStream: MutableStateFlow<String> = MutableStateFlow(
		value = quoteModel.associatedDayOfMonth.toString(),
	)
	val gregorianCalendarOptionsModelStream: MutableStateFlow<QuoteGregorianCalendarOptionsModel?> = MutableStateFlow(
		value = quoteModel.quoteGregorianCalendarOptionsModel,
	)
	val lunarCalendarOptionsModelStream: MutableStateFlow<QuoteLunarCalendarOptionsModel?> = MutableStateFlow(
		value = quoteModel.quoteLunarCalendarOptionsModel,
	)
	val hebrewCalendarOptionsModelStream: MutableStateFlow<QuoteHebrewCalendarOptionsModel?> = MutableStateFlow(
		value = quoteModel.quoteHebrewCalendarOptionsModel,
	)

	override val uiStateStream: Flow<EditIndividualPageUiState> = combine(
		englishQuoteTextStream,
		associatedMonthTextStream
			.map {
				it.replace(
					regex = Regex("[^0-9., ]"),
					replacement = "",
				)
			}
			.flowOn(Dispatchers.Default),
		associatedDayOfMonthTextStream
			.map {
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
		EditIndividualPageUiState.Loaded(
			englishQuoteText = quoteText,
			associatedMonthText = associatedMonth,
			associatedDayOfMonthText = associatedDayOfMonth,
			gregorianCalendarOptionsModel = gregorianOptionsModel,
			lunarCalendarOptionsModel = lunarOptionsModel,
			hebrewCalendarOptionsModel = hebrewOptionsModel,
		)
	}

	fun onSaveTap(
		uiState: EditIndividualPageUiState.Loaded,
		successMessage: String,
		failureMessage: String,
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
					quotesManager.updateQuote(
						QuoteModel(
							quoteId,
							MultilingualStringModel(
								uiState.englishQuoteText
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
						successMessage
					)
				}
				.onFailure {
					snackbarService.showMessage(
						failureMessage
					)
				}
		}
	}

	companion object {
		const val QUOTE_ID_KEY = "quoteId"
	}
}
