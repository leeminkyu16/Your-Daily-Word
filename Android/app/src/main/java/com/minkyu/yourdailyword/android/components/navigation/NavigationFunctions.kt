package com.minkyu.yourdailyword.android.components.navigation

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.minkyu.yourdailyword.android.models.NavigationDestination

fun NavDestination?.isTopLevelDestinationInHierarchy(destination: NavigationDestination) =
	this?.hierarchy?.any {
		it.route?.equals(destination.fullNavPath, true) ?: false
	} ?: false
