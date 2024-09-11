package com.minkyu.yourdailyword.android.extension

import com.minkyu.yourdailyword.common.protobased.QuoteModel
import com.minkyu.yourdailyword.common.protobased.QuotesModel
import java.util.Locale

fun QuotesModel.getQuoteBasedOnDayOfMonth(
	month: Int,
	dayOfMonth: Int,
	locale: Locale,
): String? {
	val quote = this.values.firstOrNull { quote: QuoteModel ->
		quote.associatedMonth == month && quote.associatedDayOfMonth == dayOfMonth
	}
	return when (locale.language) {
		"en" -> quote?.value?.english
		"fr" -> ""
		else -> null
	}
}
