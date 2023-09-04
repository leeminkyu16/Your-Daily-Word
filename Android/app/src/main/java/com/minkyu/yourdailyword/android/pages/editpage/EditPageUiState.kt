package com.minkyu.yourdailyword.android.pages.editpage

import com.minkyu.yourdailyword.common.protobased.QuoteModel

sealed interface EditPageUiState {
	object Loading: EditPageUiState

	data class Loaded(
		val quotes: MutableList<QuoteModel>,
	): EditPageUiState
}
