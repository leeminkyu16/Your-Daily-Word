package com.minkyu.yourdailyword.android.services

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SnackbarService @Inject constructor(): ISnackbarService {
	override val snackbarHostState = SnackbarHostState()
	private var coroutineScope: CoroutineScope? = null

	override fun setCoroutineScope(coroutineScope: CoroutineScope) {
		this.coroutineScope = coroutineScope
	}

	override fun showMessage(
		message: String,
		actionLabel: String?,
		duration: SnackbarDuration,
		withDismissAction: Boolean,
	) {
		coroutineScope?.launch(Dispatchers.Default) {
			snackbarHostState.showSnackbar(
				message = message,
				actionLabel = actionLabel,
				withDismissAction = withDismissAction,
				duration = duration,
			)
		}
	}
}
