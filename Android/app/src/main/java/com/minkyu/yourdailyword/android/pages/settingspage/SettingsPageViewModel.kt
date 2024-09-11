package com.minkyu.yourdailyword.android.pages.settingspage

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import com.google.protobuf.util.JsonFormat
import com.minkyu.yourdailyword.android.R
import com.minkyu.yourdailyword.android.infrastructure.YdwViewModel
import com.minkyu.yourdailyword.android.models.IQuotesManager
import com.minkyu.yourdailyword.android.services.IIoService
import com.minkyu.yourdailyword.android.services.ISettingsService
import com.minkyu.yourdailyword.android.services.ISnackbarService
import com.minkyu.yourdailyword.common.protos.PairingInfo
import com.minkyu.yourdailyword.common.protos.Quotes
import com.minkyu.yourdailyword.common.protos.SyncQuotesRequest
import com.minkyu.yourdailyword.common.protos.SyncQuotesServiceGrpcKt
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.grpc.android.AndroidChannelBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsPageViewModel @Inject constructor(
	private val quotesManager: IQuotesManager,
	private val ioService: IIoService,
	private val settingsService: ISettingsService,
	private val snackbarService: ISnackbarService,
	@ApplicationContext
	private val context: Context,
): YdwViewModel<SettingsPageUiState>() {
	override val uiStateStream: Flow<SettingsPageUiState> = snapshotFlow {
		settingsService.darkModeUseSystemDefault.value
	}.combine(
		snapshotFlow {
			settingsService.darkModeUserInput.value
		}
	) { darkModeUseSystemDefault: Boolean, darkModeUserInput: Boolean ->
		SettingsPageUiState.Loaded(
			darkModeUseSystemDefault = darkModeUseSystemDefault,
			darkModeUserInput = darkModeUserInput,
		)
	}

	fun setDarkModeUseSystemDefault(newValue: Boolean) {
		viewModelScope.launch(Dispatchers.Default) {
			settingsService.darkModeUseSystemDefault.value = newValue
		}
	}

	fun setDarkModeUserInput(newValue: Boolean) {
		viewModelScope.launch(Dispatchers.Default) {
			settingsService.darkModeUserInput.value = newValue
		}
	}

	fun readQuotesProtoFromInputStream(
		uri: Uri?,
	) {
		viewModelScope.launch(Dispatchers.IO) {
			val fileName: String? = ioService.getFileName(uri = uri)
			ioService.getInputStream(uri = uri)?.use {
				if (
					fileName
						?.substringAfterLast('.', "")
					== "ydw"
				) {
					quotesManager.setFromProto(
						Quotes.parseDelimitedFrom(
							it,
						)
					)

					quotesManager.saveToDefaultFile()

					snackbarService.showMessage(
						messageStringRes = R.string.import_quote_success_message
					)
					return@launch
				}
			}
			snackbarService.showMessage(
				messageStringRes = R.string.import_quote_failure_message
			)
		}
	}

	fun onSyncQuotesButtonClicked() {
		GmsBarcodeScanning.getClient(
			context,
			GmsBarcodeScannerOptions.Builder()
				.setBarcodeFormats(Barcode.FORMAT_QR_CODE)
				.enableAutoZoom()
				.build()
		).startScan()
			.addOnSuccessListener { barcode ->
				barcode.rawValue?.let {
					val builder = PairingInfo.newBuilder()

					JsonFormat.parser()
						.ignoringUnknownFields()
						.merge(
							it,
							builder
						)

					val pairingInfo = builder.build()

					viewModelScope.launch(Dispatchers.IO) {
						val response = kotlin.runCatching {
							SyncQuotesServiceGrpcKt.SyncQuotesServiceCoroutineStub(
								channel = AndroidChannelBuilder
									.forAddress(pairingInfo.hostAddress, pairingInfo.port)
									.usePlaintext()
									.context(context)
									.build(),
							).syncQuotes(
								SyncQuotesRequest.newBuilder()
									.setQuotes(quotesManager.getProto() ?: Quotes.getDefaultInstance())
									.build()
							)
						}

						response.onSuccess { successfulResponse ->
							successfulResponse.quotes?.let { quotes ->
								if (successfulResponse.update) {
									quotesManager.setFromProto(quotes)
									quotesManager.saveToDefaultFile()
								}
							}
						}
						response.onFailure {
							snackbarService.showMessage(
								context.getString(R.string.quote_sync_server_failure_message)
							)
						}
					}
				}
			}
	}
}
