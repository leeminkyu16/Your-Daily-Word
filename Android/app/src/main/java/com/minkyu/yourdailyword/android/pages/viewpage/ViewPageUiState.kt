package com.minkyu.yourdailyword.android.pages.viewpage

sealed interface ViewPageUiState {
	object Loading: ViewPageUiState

	data class Loaded(
		val quote: String?,
		val dayText: String,
		val monthText: String,
		val yearText: String,
	): ViewPageUiState
}
