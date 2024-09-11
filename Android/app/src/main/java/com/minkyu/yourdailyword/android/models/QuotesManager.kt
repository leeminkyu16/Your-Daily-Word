package com.minkyu.yourdailyword.android.models

import com.minkyu.yourdailyword.android.services.IFlagsService
import com.minkyu.yourdailyword.android.services.IIoService
import com.minkyu.yourdailyword.common.protobased.CalendarTypeModel
import com.minkyu.yourdailyword.common.protobased.QuoteModel
import com.minkyu.yourdailyword.common.protobased.QuotesModel
import com.minkyu.yourdailyword.common.protos.Quotes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.FileOutputStream
import javax.inject.Inject

@Suppress("TooManyFunctions")
class QuotesManager @Inject constructor(
	private val ioService: IIoService,
	private val flagsService: IFlagsService,
) : IQuotesManager {
	override var modelFlow: MutableStateFlow<QuotesModel?> = MutableStateFlow(null)
	private var internalModelMap: MutableMap<Int, QuoteModel> = mutableMapOf()

	private val job = Job()
	private val scope = CoroutineScope(Dispatchers.Default + job)

	init {
		modelFlow
			.filterNotNull()
			.drop(1) // Drop the value being loaded from file
			.onEach {
				modelFlow.value?.lastModified = System.currentTimeMillis()
			}
			.launchIn(scope)
	}

	override fun getProto(): Quotes? {
		synchronized(this) {
			return modelFlow.value?.toProto()
		}
	}

	override suspend fun setFromProto(quotesProto: Quotes) {
		val newQuotesModel: QuotesModel = QuotesModel.fromProto(quotesProto)
		modelFlow.emit(newQuotesModel)
		synchronized(this) {
			internalModelMap.clear()
			newQuotesModel.values.forEach {
				internalModelMap[it.uid] = it
			}
		}
	}

	override fun getQuotesCalendarType(): CalendarTypeModel {
		synchronized(this) {
			return this.modelFlow.value?.type ?: CalendarTypeModel.NOT_SPECIFIED
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

	override suspend fun addQuote(quoteModel: QuoteModel) {
		synchronized(this) {
			this.internalModelMap.put(
				key = quoteModel.uid,
				value = quoteModel,
			)
		}

		this.modelFlow.emit(
			this.modelFlow.value?.apply {
				values.add(quoteModel)
			}
		)
	}

	override fun getNewUid(): Int {
		if (this.modelFlow.value?.values?.isEmpty() != false) {
			return 0
		}
		var candidateUid: Int = this.modelFlow.value?.values?.last()?.uid ?: 0
		while (candidateUid in this.internalModelMap.keys) {
			candidateUid++
		}
		return candidateUid
	}

	override suspend fun updateQuote(quoteModel: QuoteModel) {
		synchronized(this) {
			this.internalModelMap.put(key = quoteModel.uid, quoteModel)
		}

		this.modelFlow.emit(
			this.modelFlow.value?.apply {
				values.replaceAll {
					if (it.uid == quoteModel.uid) {
						quoteModel
					} else {
						it
					}
				}
			}
		)
	}

	override suspend fun deleteQuote(quoteId: Int) {
		synchronized(this) {
			this.internalModelMap[quoteId]?.let {
				this.internalModelMap.remove(quoteId)
			}
		}

		this.modelFlow.emit(
			this.modelFlow.value?.apply {
				values.removeIf {
					it.uid == quoteId
				}
			}
		)
	}

	override suspend fun saveToDefaultFile() {
		synchronized(this) {
			ioService.getAppSpecificFileOutputStream(
				if (flagsService.debug.get()) DEBUG_DEFAULT_FILE_PATH
				else DEFAULT_FILE_PATH
			)
				.use { outputStream: FileOutputStream ->
					this.getProto()?.writeDelimitedTo(
						outputStream
					)
				}
		}
	}

	companion object {
		const val DEFAULT_FILE_PATH = "default.ydw"
		const val DEBUG_DEFAULT_FILE_PATH = "debug_default.ydw"
	}
}
