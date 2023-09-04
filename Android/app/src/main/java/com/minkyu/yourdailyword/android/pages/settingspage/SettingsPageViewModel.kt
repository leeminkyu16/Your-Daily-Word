package com.minkyu.yourdailyword.android.pages.settingspage

import android.net.Uri
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.viewModelScope
import com.minkyu.yourdailyword.android.infrastructure.YdwViewModel
import com.minkyu.yourdailyword.android.models.IQuotesManager
import com.minkyu.yourdailyword.android.services.IIoService
import com.minkyu.yourdailyword.android.services.ISettingsService
import com.minkyu.yourdailyword.android.services.ISnackbarService
import com.minkyu.yourdailyword.common.protos.Quotes
import dagger.hilt.android.lifecycle.HiltViewModel
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
		onSuccessText: String,
		onFailureText: String,
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

					snackbarService.showMessage(onSuccessText)
					return@launch
				}
			}
			snackbarService.showMessage(onFailureText)
		}
	}
}
