package com.minkyu.yourdailyword.android.services

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.CoroutineScope

interface ISettingsService {
	val darkModeUseSystemDefault: MutableState<Boolean>
	val darkModeUserInput: MutableState<Boolean>
	fun subscribe(ioCoroutineScope: CoroutineScope)
}
