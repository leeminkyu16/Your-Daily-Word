package com.minkyu.yourdailyword.android.components.navigationbar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.navOptions
import com.minkyu.yourdailyword.android.models.NavigationDestination

@Suppress("FunctionNaming")
@Composable
fun BottomNavigationBarView(
	modifier: Modifier = Modifier,
	currentDestination: NavigationDestination,
	viewModel: BottomNavigationBarViewModel = hiltViewModel(),
) {
	NavigationBar(
		modifier = modifier,
	) {
		listOf(
			NavigationDestination.VIEW_PAGE,
			NavigationDestination.EDIT_PAGE,
			NavigationDestination.SETTINGS_PAGE,
		).forEach { navigationDestination ->
			if (navigationDestination.bottomBarNavigationItems != null) {
				val selected: Boolean =
					currentDestination.rootNavPath.substringBefore("/") == navigationDestination.rootNavPath

				NavigationBarItem(
					selected = selected,
					icon = {
						Icon(
							painter = painterResource(
								id = if (selected) navigationDestination.bottomBarNavigationItems.selectedIconId
								else navigationDestination.bottomBarNavigationItems.unselectedIconId
							),
							contentDescription = stringResource(
								id = navigationDestination.bottomBarNavigationItems.bottomBarNavigationTextId
							)
						)
					},
					label = {
						Text(text = stringResource(id = navigationDestination.bottomBarNavigationItems.bottomBarNavigationTextId))
					},
					onClick = {
						viewModel.navHostController.navigate(
							navigationDestination.fullNavPath,
							navOptions {
								popUpTo(viewModel.navHostController.graph.findStartDestination().id) {
									saveState = true
								}
								launchSingleTop = true
								restoreState = true
							}
						)
					}
				)
			}
		}
	}
}
