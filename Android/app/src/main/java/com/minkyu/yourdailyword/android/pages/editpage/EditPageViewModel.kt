package com.minkyu.yourdailyword.android.pages.editpage

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.minkyu.yourdailyword.android.infrastructure.YdwViewModel
import com.minkyu.yourdailyword.android.models.IQuotesManager
import com.minkyu.yourdailyword.android.services.INavigationService
import com.minkyu.yourdailyword.android.services.ISnackbarService
import com.minkyu.yourdailyword.common.calendar.ICalendarModel
import com.minkyu.yourdailyword.common.calendar.IGregorianCalendarModel
import com.minkyu.yourdailyword.common.calendar.IHebrewCalendarModel
import com.minkyu.yourdailyword.common.calendar.ILunarCalendarModel
import com.minkyu.yourdailyword.common.protobased.CalendarTypeModel
import com.minkyu.yourdailyword.common.protobased.QuoteModel
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class EditPageViewModel @Inject constructor(
	private val quotesManager: IQuotesManager,
	private val navigationService: INavigationService,
	private val snackbarService: ISnackbarService,
	lazyGregorianCalendarModel: Lazy<IGregorianCalendarModel>,
	lazyLunarCalendarModel: Lazy<ILunarCalendarModel>,
	lazyHebrewCalendarModel: Lazy<IHebrewCalendarModel>,
): YdwViewModel<EditPageUiState>() {
	val navHostController: NavHostController get() = navigationService.navHostController

	private val calendarModel: ICalendarModel = when(quotesManager.getQuotesCalendarType()) {
		CalendarTypeModel.NOT_SPECIFIED -> lazyGregorianCalendarModel.get()
		CalendarTypeModel.GREGORIAN_BASED -> lazyGregorianCalendarModel.get()
		CalendarTypeModel.LUNAR_BASED -> lazyLunarCalendarModel.get()
		CalendarTypeModel.HEBREW_BASED -> lazyHebrewCalendarModel.get()
	}

	val searchQueryStream: MutableStateFlow<String> = MutableStateFlow("")

	private val quotesListStream: Flow<List<QuoteModel>> =
		quotesManager.modelFlow
			.map {
				it?.values ?: mutableListOf<QuoteModel>()
			}
			.combine(
				flow = searchQueryStream.debounce(SEARCH_DEBOUNCE_TIME),
			) { list: MutableList<QuoteModel>, searchQuery: String ->
				if (searchQuery.isBlank()) {
					return@combine list
				}

				list.filter {
					it.value.english.contains(searchQuery, ignoreCase = true)
				}
			}
			.flowOn(Dispatchers.Default)

	override val uiStateStream: Flow<EditPageUiState> =
		combine(
			quotesListStream,
			searchQueryStream,
		) { quotes: List<QuoteModel>, searchQuery: String ->
			EditPageUiState.Loaded(
				editPreviewQuoteItems = quotes.map {
					EditPageUiState.EditPreviewQuoteItem(
						quoteUId = it.uid,
						quoteText = it.value.english,
						dayOfMonthText = (it.associatedDayOfMonth + 1).toString(),
						monthText = calendarModel.monthIntToMonthText(it.associatedMonth)
							?: (it.associatedMonth + 1).toString(),
					)
				},
				searchQuery = searchQuery,
			)
		}
			.flowOn(Dispatchers.Default)

	fun onDeleteTap(
		quoteId: Int,
	) {
		viewModelScope.launch(Dispatchers.Default) {
			runCatching {
				quotesManager.deleteQuote(quoteId = quoteId)
				withContext(Dispatchers.IO) {
					quotesManager.saveToDefaultFile()
				}
			}
				.onSuccess {
					snackbarService.showMessage(
						messageStringRes = com.minkyu.yourdailyword.android.R.string.delete_quote_success_message,
					)
				}
				.onFailure {
					snackbarService.showMessage(
						messageStringRes = com.minkyu.yourdailyword.android.R.string.delete_quote_failure_message,
					)
				}
		}
	}

	companion object {
		const val SEARCH_DEBOUNCE_TIME = 500L
	}
}
