package com.minkyu.yourdailyword.android.components.mainscaffold

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.minkyu.yourdailyword.android.services.IIoService
import com.minkyu.yourdailyword.android.models.IQuotesManager
import com.minkyu.yourdailyword.android.models.NavigationDestination
import com.minkyu.yourdailyword.android.models.QuotesManager
import com.minkyu.yourdailyword.android.services.IFlagsService
import com.minkyu.yourdailyword.android.services.INavigationService
import com.minkyu.yourdailyword.android.services.ISnackbarService
import com.minkyu.yourdailyword.common.protos.Quotes
import com.minkyu.yourdailyword.common.testing.QuotesTesting
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScaffoldViewModel @Inject constructor(
	quotesManager: IQuotesManager,
	ioService: IIoService,
	flagsService: IFlagsService,
	private val navigationService: INavigationService,
	private val snackbarService: ISnackbarService,
) : ViewModel() {
	val navHostController: NavHostController get() = navigationService.navHostController
	val snackbarHostState: SnackbarHostState get() = snackbarService.snackbarHostState

	val currentDestination: MutableState<NavigationDestination> =
		mutableStateOf(NavigationDestination.VIEW_PAGE)
	init {
		viewModelScope.launch(Dispatchers.IO) {
			if (
				!flagsService.debug.get() &&
				ioService.appSpecificFileExists(QuotesManager.DEFAULT_FILE_PATH)
			) {
				ioService.getAppSpecificFileInputStream(QuotesManager.DEFAULT_FILE_PATH).use {
					val quotesProto = Quotes.parseDelimitedFrom(
						it
					)
					if (quotesProto != null) {
						quotesManager.setFromProto(
							quotesProto
						)
					}
				}
			}
			else if (
				flagsService.debug.get() &&
				ioService.appSpecificFileExists(QuotesManager.DEBUG_DEFAULT_FILE_PATH)
			) {
				ioService.getAppSpecificFileInputStream(QuotesManager.DEBUG_DEFAULT_FILE_PATH).use {
					val quotesProto = Quotes.parseDelimitedFrom(
						it
					)
					if (quotesProto != null) {
						quotesManager.setFromProto(
							quotesProto
						)
					}
				}
			}
			else if (flagsService.debug.get() && flagsService.generateDebugModel.get()) {
				quotesManager.setFromProto(QuotesTesting.generateTestQuotes())
				quotesManager.saveToDefaultFile()
			}
		}
	}
}
