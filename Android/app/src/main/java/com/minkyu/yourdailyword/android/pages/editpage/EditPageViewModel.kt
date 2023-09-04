package com.minkyu.yourdailyword.android.pages.editpage

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.minkyu.yourdailyword.android.infrastructure.YdwViewModel
import com.minkyu.yourdailyword.android.models.IQuotesManager
import com.minkyu.yourdailyword.android.services.INavigationService
import com.minkyu.yourdailyword.android.services.ISnackbarService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EditPageViewModel @Inject constructor(
	private val quotesManager: IQuotesManager,
	private val navigationService: INavigationService,
	private val snackbarService: ISnackbarService,
): YdwViewModel<EditPageUiState>() {
	val navHostController: NavHostController get() = navigationService.navHostController

	override val uiStateStream: Flow<EditPageUiState> =
		MutableStateFlow(Unit)
			.map {
				EditPageUiState.Loaded(
					quotes = quotesManager.getQuoteList() ?: mutableListOf()
				)
			}
			.flowOn(Dispatchers.IO)

	fun onDeleteTap(quoteId: Int, successMessage: String, failMessage: String) {
		viewModelScope.launch(Dispatchers.Default) {
			runCatching {
				quotesManager.deleteQuote(quoteId = quoteId)
				withContext(Dispatchers.IO) {
					quotesManager.saveToDefaultFile()
				}
			}
				.onSuccess {
					snackbarService.showMessage(successMessage)
				}
				.onFailure {
					snackbarService.showMessage(failMessage)
				}
		}
	}
}
