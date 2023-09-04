package com.minkyu.yourdailyword.android.models

import com.minkyu.yourdailyword.common.protobased.CalendarTypeModel
import com.minkyu.yourdailyword.common.protobased.GregorianCalendarOptionsModel
import com.minkyu.yourdailyword.common.protobased.HebrewCalendarOptionsModel
import com.minkyu.yourdailyword.common.protobased.LunarCalendarOptionsModel
import com.minkyu.yourdailyword.common.protobased.QuoteModel
import com.minkyu.yourdailyword.common.protos.Quotes
import java.util.Locale

@Suppress("TooManyFunctions")
interface IQuotesManager {
	fun setFromProto(quotesProto: Quotes)
	fun getQuotesCalendarType(): CalendarTypeModel
	fun getQuote(quoteId: Int): QuoteModel?
	fun getQuoteBasedOnDayOfMonth(month: Int, dayOfMonth: Int, locale: Locale): String?
	fun getGregorianCalendarOptions(): GregorianCalendarOptionsModel?
	fun getLunarCalendarOptions(): LunarCalendarOptionsModel?
	fun getHebrewCalendarOptions(): HebrewCalendarOptionsModel?
	fun getQuoteList(): MutableList<QuoteModel>?
	fun getProto(): Quotes?
	fun deleteQuote(quoteId: Int)
	fun updateQuote(quoteModel: QuoteModel)
	suspend fun saveToDefaultFile()
	fun addQuote(quoteModel: QuoteModel)
	fun getNewUid(): Int
}
