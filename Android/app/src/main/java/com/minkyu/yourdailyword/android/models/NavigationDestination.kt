package com.minkyu.yourdailyword.android.models

import com.minkyu.yourdailyword.android.R
import com.minkyu.yourdailyword.android.components.navigationbar.BottomNavigationBarItems
import com.minkyu.yourdailyword.android.pages.editindividualpage.EditIndividualPageViewModel.Companion.QUOTE_ID_KEY

enum class NavigationDestination(
	val rootNavPath: String,
	val fullNavPath: String = rootNavPath,
	val bottomBarNavigationItems: BottomNavigationBarItems? = null,
	val showBottomBar: Boolean = false,
) {
	VIEW_PAGE(
		rootNavPath = "view",
		bottomBarNavigationItems = BottomNavigationBarItems(
			selectedIconId = R.drawable.baseline_image_24,
			unselectedIconId = R.drawable.outline_image_24,
			bottomBarNavigationTextId = R.string.view_page,
		),
		showBottomBar = true,
	),
	EDIT_PAGE(
		rootNavPath = "edit",
		bottomBarNavigationItems = BottomNavigationBarItems(
			selectedIconId = R.drawable.baseline_book_24,
			unselectedIconId = R.drawable.outline_book_24,
			bottomBarNavigationTextId = R.string.edit_page,
		),
		showBottomBar = true,
	),
	EDIT_INDIVIDUAL_PAGE(
		rootNavPath = "edit/individual",
		fullNavPath = "edit/individual/{${QUOTE_ID_KEY}}",
		showBottomBar = true,
	),
	EDIT_ADD_PAGE(
		rootNavPath = "edit/add",
		showBottomBar = true,
	),
	SETTINGS_PAGE(
		rootNavPath = "settings",
		bottomBarNavigationItems = BottomNavigationBarItems(
			selectedIconId = R.drawable.baseline_settings_24,
			unselectedIconId = R.drawable.outline_settings_24,
			bottomBarNavigationTextId = R.string.settings_page,
		),
		showBottomBar = true,
	),
}
