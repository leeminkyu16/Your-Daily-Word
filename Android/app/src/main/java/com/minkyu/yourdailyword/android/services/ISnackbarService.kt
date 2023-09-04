package com.minkyu.yourdailyword.android.services

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope

interface ISnackbarService {
	val snackbarHostState: SnackbarHostState
	fun showMessage(
		message: String = "",
		actionLabel: String? = null,
		duration: SnackbarDuration = SnackbarDuration.Long,
		withDismissAction: Boolean = duration != SnackbarDuration.Short,
	)

	fun setCoroutineScope(coroutineScope: CoroutineScope)
}
