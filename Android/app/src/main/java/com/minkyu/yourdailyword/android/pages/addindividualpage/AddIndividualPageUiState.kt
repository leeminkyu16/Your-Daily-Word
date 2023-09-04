package com.minkyu.yourdailyword.android.pages.addindividualpage

import com.minkyu.yourdailyword.common.protobased.QuoteGregorianCalendarOptionsModel
import com.minkyu.yourdailyword.common.protobased.QuoteHebrewCalendarOptionsModel
import com.minkyu.yourdailyword.common.protobased.QuoteLunarCalendarOptionsModel

sealed interface AddIndividualPageUiState {
	object Loading: AddIndividualPageUiState

	data class Loaded(
		val englishQuoteText: String,
		val associatedMonthText: String,
		val associatedDayOfMonthText: String,
		val gregorianCalendarOptionsModel: QuoteGregorianCalendarOptionsModel?,
		val lunarCalendarOptionsModel: QuoteLunarCalendarOptionsModel?,
		val hebrewCalendarOptionsModel: QuoteHebrewCalendarOptionsModel?,
	): AddIndividualPageUiState
}
