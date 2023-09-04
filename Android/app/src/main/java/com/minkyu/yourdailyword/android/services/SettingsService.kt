package com.minkyu.yourdailyword.android.services

import android.content.Context
import android.content.res.Configuration
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SettingsService(
	private val context: Context,
	private val ioService: IIoService,
): ISettingsService {
	override val darkModeUseSystemDefault: MutableState<Boolean> =
		if (
			ioService.appSpecificFileExists(
				filePathString = DARK_MODE_USE_SYSTEM_DEFAULT_FILE_PATH,
			)
		) {
			mutableStateOf(
				value = ioService.readStringFromAppSpecificFile(
					filePathString = DARK_MODE_USE_SYSTEM_DEFAULT_FILE_PATH,
				).toBoolean(),
			)
		}
		else {
			mutableStateOf(
				value = true,
			)
		}
	override val darkModeUserInput: MutableState<Boolean> =
		if (ioService.appSpecificFileExists(filePathString = DARK_MODE_USE_INPUT_FILE_PATH)) {
			mutableStateOf(
				value = ioService.readStringFromAppSpecificFile(
					filePathString = DARK_MODE_USE_INPUT_FILE_PATH,
				).toBoolean(),
			)
		}
	else {
		mutableStateOf(
			value = (
				context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
			) == Configuration.UI_MODE_NIGHT_YES,
		)
	}

	override fun subscribe(ioCoroutineScope: CoroutineScope) {
		snapshotFlow { darkModeUseSystemDefault.value }
			.onEach {
				if (it) {
					darkModeUserInput.value = (
						context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
					) == Configuration.UI_MODE_NIGHT_YES
				}

				ioService.writeStringToAppSpecificFile(
					filePathString = DARK_MODE_USE_SYSTEM_DEFAULT_FILE_PATH,
					input = it.toString(),
				)
			}
			.launchIn(scope = ioCoroutineScope)

		snapshotFlow { darkModeUserInput.value }
			.onEach {
				ioService.writeStringToAppSpecificFile(
					filePathString = DARK_MODE_USE_INPUT_FILE_PATH,
					input = it.toString(),
				)
			}
			.launchIn(ioCoroutineScope)
	}

	companion object {
		private const val DARK_MODE_USE_SYSTEM_DEFAULT_FILE_PATH = "darkmodeusesystemdefault.ydw.boolean"
		private const val DARK_MODE_USE_INPUT_FILE_PATH = "darkmodeuseinputfile.ydw.boolean"
	}
}
