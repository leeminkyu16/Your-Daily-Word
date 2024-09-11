package com.minkyu.yourdailyword.android.components.mainscaffold

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import com.minkyu.yourdailyword.android.components.navigation.MainNavigationView
import com.minkyu.yourdailyword.android.components.navigation.isTopLevelDestinationInHierarchy
import com.minkyu.yourdailyword.android.components.navigationbar.BottomNavigationBarView
import com.minkyu.yourdailyword.android.models.NavigationDestination
import com.minkyu.yourdailyword.android.ui.theme.YdwTheme

@Composable
@Suppress("FunctionNaming")
fun MainScaffoldView(
    modifier: Modifier = Modifier,
    viewModel: MainScaffoldViewModel = hiltViewModel(),
) {
	val currentBackStackEntry: NavBackStackEntry? by viewModel
		.navHostController
		.currentBackStackEntryAsState()
	var currentDestination: NavigationDestination by viewModel.currentDestination

	LaunchedEffect(key1 = currentBackStackEntry) {
		NavigationDestination.values().forEach {
			if (
				currentBackStackEntry
					?.destination
					.isTopLevelDestinationInHierarchy(it)
			) {
				currentDestination = it
			}
		}
	}

	Scaffold(
		modifier = modifier,
		snackbarHost = {
			SnackbarHost(
				hostState = viewModel.snackbarHostState,
			) { data ->
				Snackbar(
					snackbarData = data,
					containerColor = YdwTheme.palette.snackbarContainer,
					contentColor = YdwTheme.palette.snackbarContent,
					actionColor = YdwTheme.palette.snackbarContent,
					actionContentColor = YdwTheme.palette.snackbarContent,
					dismissActionContentColor = YdwTheme.palette.snackbarContent,
				)
			}
		},
		bottomBar = {
			if (currentDestination.showBottomBar) {
				BottomNavigationBarView(
					currentDestination = currentDestination,
				)
			}
		},
	) { padding: PaddingValues ->
		Column(
			modifier = modifier.padding(padding)
		) {
			HorizontalDivider(
				modifier = Modifier,
			)
			MainNavigationView(
				modifier = Modifier,
			)
		}
	}
}

@Suppress("FunctionNaming", "UnusedPrivateMember")
@Preview
@Composable
private fun MainScaffoldViewPreview() {
	MainScaffoldView()
}
