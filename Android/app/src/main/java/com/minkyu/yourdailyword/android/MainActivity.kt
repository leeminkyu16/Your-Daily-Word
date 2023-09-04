package com.minkyu.yourdailyword.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.minkyu.yourdailyword.android.components.mainscaffold.MainScaffoldView
import com.minkyu.yourdailyword.android.services.ISettingsService
import com.minkyu.yourdailyword.android.ui.theme.YdwAndroidTheme
import com.minkyu.yourdailyword.android.ui.theme.YdwTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	@Inject
	lateinit var settingsService: ISettingsService

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			YdwAndroidTheme(
				darkTheme = if (settingsService.darkModeUseSystemDefault.value) {
					isSystemInDarkTheme()
				}
				else {
					settingsService.darkModeUserInput.value
				}
			) {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = YdwTheme.palette.primaryBackground
				) {
					MainScaffoldView()
				}
			}
		}
	}
}
