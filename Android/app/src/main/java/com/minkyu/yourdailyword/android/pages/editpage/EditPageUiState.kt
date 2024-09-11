package com.minkyu.yourdailyword.android.pages.editpage

sealed interface EditPageUiState {
	object Loading: EditPageUiState

	data class EditPreviewQuoteItem(
		val quoteUId: Int,
		val quoteText: String,
		val dayOfMonthText: String,
		val monthText: String,
	)

	data class Loaded(
		val searchQuery: String,
		val editPreviewQuoteItems: List<EditPreviewQuoteItem>,
	): EditPageUiState
}
