package com.minkyu.yourdailyword.android.models

import com.minkyu.yourdailyword.common.protobased.CalendarTypeModel
import com.minkyu.yourdailyword.common.protobased.QuoteModel
import com.minkyu.yourdailyword.common.protobased.QuotesModel
import com.minkyu.yourdailyword.common.protos.Quotes
import kotlinx.coroutines.flow.MutableStateFlow

@Suppress("TooManyFunctions")
interface IQuotesManager { val modelFlow: MutableStateFlow<QuotesModel?>
	suspend fun setFromProto(quotesProto: Quotes)
	fun getQuotesCalendarType(): CalendarTypeModel
	fun getQuote(quoteId: Int): QuoteModel?
	fun getProto(): Quotes?
	suspend fun deleteQuote(quoteId: Int)
	suspend fun updateQuote(quoteModel: QuoteModel)
	suspend fun saveToDefaultFile()
	suspend fun addQuote(quoteModel: QuoteModel)
	fun getNewUid(): Int
}
