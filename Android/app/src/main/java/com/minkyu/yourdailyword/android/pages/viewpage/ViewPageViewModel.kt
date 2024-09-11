package com.minkyu.yourdailyword.android.pages.viewpage

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewModelScope
import com.minkyu.yourdailyword.android.extension.getQuoteBasedOnDayOfMonth
import com.minkyu.yourdailyword.android.infrastructure.YdwViewModel
import com.minkyu.yourdailyword.android.models.IQuotesManager
import com.minkyu.yourdailyword.common.calendar.ICalendarModel
import com.minkyu.yourdailyword.common.calendar.IGregorianCalendarModel
import com.minkyu.yourdailyword.common.calendar.IHebrewCalendarModel
import com.minkyu.yourdailyword.common.calendar.ILunarCalendarModel
import com.minkyu.yourdailyword.common.protobased.CalendarTypeModel
import com.minkyu.yourdailyword.common.protobased.HebrewCalendarOptionsModel
import com.minkyu.yourdailyword.common.protobased.QuotesModel
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ViewPageViewModel @Inject constructor(
	quotesManager: IQuotesManager,
	lazyGregorianCalendarModel: Lazy<IGregorianCalendarModel>,
	lazyLunarCalendarModel: Lazy<ILunarCalendarModel>,
	lazyHebrewCalendarModel: Lazy<IHebrewCalendarModel>,
	@ApplicationContext private val context: Context,
) : YdwViewModel<ViewPageUiState>() {
	private val calendarModel: ICalendarModel = when(quotesManager.getQuotesCalendarType()) {
		CalendarTypeModel.NOT_SPECIFIED -> lazyGregorianCalendarModel.get()
		CalendarTypeModel.GREGORIAN_BASED -> lazyGregorianCalendarModel.get()
		CalendarTypeModel.LUNAR_BASED -> lazyLunarCalendarModel.get()
		CalendarTypeModel.HEBREW_BASED -> lazyHebrewCalendarModel.get()
	}

	override val uiStateStream: MutableStateFlow<ViewPageUiState> =
		MutableStateFlow(ViewPageUiState.Loading)

	init {
		quotesManager.modelFlow.map { quotes: QuotesModel? ->
			uiStateStream.emit(
				ViewPageUiState.Loaded(
					quote = quotes?.getQuote(),
					dayText = calendarModel.dayOfMonth.toString(),
					monthText = calendarModel.monthText,
					yearText = calendarModel.year.toString(),
				)
			)
		}
			.flowOn(Dispatchers.IO)
			.launchIn(viewModelScope)
	}

	fun onShareQuoteButtonTapped(quote: String) {
		quote.let {
			val intent = Intent().apply {
				action = Intent.ACTION_SEND
				flags = Intent.FLAG_ACTIVITY_NEW_TASK
				putExtra(Intent.EXTRA_TEXT, it)
				type = "text/plain"
			}

			startActivity(context, intent, null)
		}
	}

	private fun QuotesModel.getQuote(): String? {
		val hebrewCalendarOptionModel: HebrewCalendarOptionsModel? =
			this.hebrewCalendarOptionsModel

		return this.getQuoteBasedOnDayOfMonth(
			month = calendarModel.getMonthNumber(
				hebrewCalendarOptionModel?.adarIsAdar1 == true
			),
			dayOfMonth = calendarModel.dayOfMonth - 1,
			locale = Locale.getDefault(),
		)
	}
}
