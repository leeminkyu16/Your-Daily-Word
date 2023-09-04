package com.minkyu.yourdailyword.android.services

import android.content.Context
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import javax.inject.Inject

class NavigationService @Inject constructor(
	context: Context,
): INavigationService {
	override val navHostController: NavHostController = NavHostController(context = context)

	init {
		listOf(
			ComposeNavigator(),
			DialogNavigator(),
		).forEach {
			navHostController.navigatorProvider.addNavigator(it)
		}
	}
}
