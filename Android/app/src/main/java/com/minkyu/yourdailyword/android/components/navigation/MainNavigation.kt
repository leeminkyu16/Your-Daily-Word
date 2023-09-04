package com.minkyu.yourdailyword.android.components.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.minkyu.yourdailyword.android.models.NavigationDestination
import com.minkyu.yourdailyword.android.pages.addindividualpage.AddIndividualPageView
import com.minkyu.yourdailyword.android.pages.editindividualpage.EditIndividualPageView
import com.minkyu.yourdailyword.android.pages.editindividualpage.EditIndividualPageViewModel.Companion.QUOTE_ID_KEY
import com.minkyu.yourdailyword.android.pages.editpage.EditPageView
import com.minkyu.yourdailyword.android.pages.settingspage.SettingsPageView
import com.minkyu.yourdailyword.android.pages.viewpage.ViewPageView

@Composable
@Suppress("FunctionNaming")
fun MainNavigationView(
	modifier: Modifier = Modifier,
	viewModel: MainNavigationViewModel = hiltViewModel(),
) {
	NavHost(
		navController = viewModel.navHostController,
		startDestination = NavigationDestination.VIEW_PAGE.fullNavPath,
	) {
		composable(route = NavigationDestination.VIEW_PAGE.fullNavPath) {
			ViewPageView(
				modifier = modifier,
			)
		}
		composable(route = NavigationDestination.EDIT_PAGE.fullNavPath) {
			EditPageView(
				modifier = modifier,
			)
		}
		composable(
			route = NavigationDestination.EDIT_INDIVIDUAL_PAGE.fullNavPath,
			arguments = listOf(navArgument(QUOTE_ID_KEY) { type = NavType.IntType }),
		) {
			EditIndividualPageView(
				modifier = modifier,
			)
		}
		composable(
			route = NavigationDestination.EDIT_ADD_PAGE.fullNavPath,
		) {
			AddIndividualPageView(
				modifier = modifier,
			)
		}
		composable(route = NavigationDestination.SETTINGS_PAGE.fullNavPath) {
			SettingsPageView(
				modifier = modifier,
			)
		}
	}
}
