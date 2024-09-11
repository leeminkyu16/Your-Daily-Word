package com.minkyu.yourdailyword.android.services

import android.content.Context
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SnackbarService @Inject constructor(
	@ApplicationContext
	private val context: Context,
): ISnackbarService {
	override val snackbarHostState = SnackbarHostState()
	private var coroutineScope: CoroutineScope? = null

	override fun setCoroutineScope(coroutineScope: CoroutineScope) {
		this.coroutineScope = coroutineScope
	}

	override fun showMessage(
		messageStringRes: Int,
		actionLabel: String?,
		duration: SnackbarDuration,
		withDismissAction: Boolean,
	) {
		showMessage(
			context.getString(messageStringRes),
			actionLabel,
			duration,
			withDismissAction,
		)
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
