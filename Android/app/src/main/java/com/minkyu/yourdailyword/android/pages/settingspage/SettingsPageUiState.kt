package com.minkyu.yourdailyword.android.pages.settingspage

sealed interface SettingsPageUiState {
	object Loading: SettingsPageUiState

	data class Loaded(
		val darkModeUseSystemDefault: Boolean,
		val darkModeUserInput: Boolean,
	) : SettingsPageUiState
}
