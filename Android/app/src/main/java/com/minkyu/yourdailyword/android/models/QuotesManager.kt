package com.minkyu.yourdailyword.android.models

import com.minkyu.yourdailyword.android.services.IIoService
import com.minkyu.yourdailyword.common.protobased.CalendarTypeModel
import com.minkyu.yourdailyword.common.protobased.GregorianCalendarOptionsModel
import com.minkyu.yourdailyword.common.protobased.HebrewCalendarOptionsModel
import com.minkyu.yourdailyword.common.protobased.LunarCalendarOptionsModel
import com.minkyu.yourdailyword.common.protobased.QuoteModel
import com.minkyu.yourdailyword.common.protobased.QuotesModel
import com.minkyu.yourdailyword.common.protos.Quotes
import java.io.FileOutputStream
import java.util.Locale
import javax.inject.Inject

@Suppress("TooManyFunctions")
class QuotesManager @Inject constructor(
	private val ioService: IIoService,
) : IQuotesManager {
	private var internalModel: QuotesModel? = null
	private var internalModelMap: MutableMap<Int, QuoteModel> = mutableMapOf()

	override fun getProto(): Quotes? {
		synchronized(this) {
			return internalModel?.toProto()
		}
	}

	override fun setFromProto(quotesProto: Quotes) {
		synchronized(this) {
			internalModel = QuotesModel.fromProto(quotesProto)
			internalModelMap.clear()
			internalModel?.values?.forEach {
				internalModelMap[it.uid] = it
			}
		}
	}

	override fun getQuotesCalendarType(): CalendarTypeModel {
		synchronized(this) {
			return this.internalModel?.type ?: CalendarTypeModel.NOT_SPECIFIED
		}
	}

	override fun getQuote(quoteId: Int): QuoteModel? {
		synchronized(this) {
			return this.internalModelMap.getOrDefault(
				key = quoteId,
				defaultValue = null,
			)
		}
	}

	override fun addQuote(quoteModel: QuoteModel) {
		synchronized(this) {
			this.internalModel?.values?.add(quoteModel)
			this.internalModelMap.put(
				key = quoteModel.uid,
				value = quoteModel,
			)
		}
	}

	override fun getNewUid(): Int {
		if (this.internalModel?.values?.isEmpty() != false) {
			return 0
		}
		var candidateUid: Int = this.internalModel?.values?.last()?.uid ?: 0
		while(candidateUid in this.internalModelMap.keys) {
			candidateUid++
		}
		return candidateUid
	}

	override fun updateQuote(quoteModel: QuoteModel) {
		synchronized(this) {
			this.internalModel?.values?.replaceAll {
				if (it.uid == quoteModel.uid) {
					quoteModel
				} else {
					it
				}
			}
			this.internalModelMap.put(key = quoteModel.uid, quoteModel)
		}
	}

	override fun deleteQuote(quoteId: Int) {
		synchronized(this) {
			this.internalModelMap[quoteId]?.let {
				this.internalModel?.values?.remove(it)
				this.internalModelMap.remove(quoteId)
			}
		}
	}

	override fun getQuoteBasedOnDayOfMonth(month: Int, dayOfMonth: Int, locale: Locale): String? {
		synchronized(this) {
			val quote: QuoteModel? = runCatching {
				this.internalModel?.values?.first { value: QuoteModel ->
					value.associatedMonth == month && value.associatedDayOfMonth == dayOfMonth
				}
			}.getOrNull()
			return when (locale.language) {
				"en" -> quote?.value?.english
				"fr" -> ""
				else -> null
			}
		}
	}

	override fun getGregorianCalendarOptions(): GregorianCalendarOptionsModel? {
		synchronized(this) {
			return this.internalModel?.gregorianCalendarOptionsModel
		}
	}

	override fun getLunarCalendarOptions(): LunarCalendarOptionsModel? {
		synchronized(this) {
			return this.internalModel?.lunarCalendarOptionsModel
		}
	}

	override fun getHebrewCalendarOptions(): HebrewCalendarOptionsModel? {
		synchronized(this) {
			return internalModel?.hebrewCalendarOptionsModel
		}
	}

	override fun getQuoteList(): MutableList<QuoteModel>? {
		synchronized(this) {
			return internalModel?.values
		}
	}

	override suspend fun saveToDefaultFile() {
		synchronized(this) {
			ioService.getAppSpecificFileOutputStream(DEFAULT_FILE_PATH)
				.use { outputStream: FileOutputStream ->
					this.getProto()?.writeDelimitedTo(
						outputStream
					)
				}
		}
	}

	companion object {
		const val DEFAULT_FILE_PATH = "default.ydw"
	}
}
