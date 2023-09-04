package com.minkyu.yourdailyword.android.components.navigation

import androidx.navigation.NavHostController
import com.minkyu.yourdailyword.android.infrastructure.YdwViewModel
import com.minkyu.yourdailyword.android.services.INavigationService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainNavigationViewModel @Inject constructor(
	private val navigationService: INavigationService,
): YdwViewModel<Unit>() {
	val navHostController: NavHostController get() = navigationService.navHostController
}
