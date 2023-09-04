package com.minkyu.yourdailyword.android.pages.viewpage

import androidx.lifecycle.viewModelScope
import com.minkyu.yourdailyword.android.infrastructure.YdwViewModel
import com.minkyu.yourdailyword.android.models.IQuotesManager
import com.minkyu.yourdailyword.common.calendar.ICalendarModel
import com.minkyu.yourdailyword.common.calendar.IGregorianCalendarModel
import com.minkyu.yourdailyword.common.calendar.IHebrewCalendarModel
import com.minkyu.yourdailyword.common.calendar.ILunarCalendarModel
import com.minkyu.yourdailyword.common.protobased.CalendarTypeModel
import com.minkyu.yourdailyword.common.protobased.HebrewCalendarOptionsModel
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ViewPageViewModel @Inject constructor(
	private val quotesManager: IQuotesManager,
	lazyGregorianCalendarModel: Lazy<IGregorianCalendarModel>,
	lazyLunarCalendarModel: Lazy<ILunarCalendarModel>,
	lazyHebrewCalendarModel: Lazy<IHebrewCalendarModel>,
) : YdwViewModel<ViewPageUiState>() {
	private val calendarModel: ICalendarModel = when(quotesManager.getQuotesCalendarType()) {
		CalendarTypeModel.NOT_SPECIFIED -> lazyGregorianCalendarModel.get()
		CalendarTypeModel.GREGORIAN_BASED -> lazyGregorianCalendarModel.get()
		CalendarTypeModel.LUNAR_BASED -> lazyLunarCalendarModel.get()
		CalendarTypeModel.HEBREW_BASED -> lazyHebrewCalendarModel.get()
	}

	private val quoteUpdateStream: MutableStateFlow<Unit> = MutableStateFlow(Unit)

	override val uiStateStream: MutableStateFlow<ViewPageUiState> =
		MutableStateFlow(ViewPageUiState.Loading)

	init {
		quoteUpdateStream.map { _: Unit ->
			uiStateStream.emit(
				ViewPageUiState.Loaded(
					quote = getQuote(),
					dayText = calendarModel.dayOfMonth.toString(),
					monthText = calendarModel.monthText,
					yearText = calendarModel.year.toString(),
				)
			)
		}
			.flowOn(Dispatchers.IO)
			.launchIn(viewModelScope)
	}

	private fun getQuote(): String? {
		val hebrewCalendarOptionModel: HebrewCalendarOptionsModel? =
			quotesManager.getHebrewCalendarOptions()

		return quotesManager.getQuoteBasedOnDayOfMonth(
			month = calendarModel.getMonthNumber(
				hebrewCalendarOptionModel?.adarIsAdar1 == true
			),
			dayOfMonth = calendarModel.dayOfMonth - 1,
			locale = Locale.getDefault(),
		)
	}
}
