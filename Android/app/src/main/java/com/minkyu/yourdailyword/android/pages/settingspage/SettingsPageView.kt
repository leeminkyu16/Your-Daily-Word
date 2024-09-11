package com.minkyu.yourdailyword.android.pages.settingspage

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.minkyu.yourdailyword.android.R
import com.minkyu.yourdailyword.android.components.button.SecondaryButton
import com.minkyu.yourdailyword.android.components.switch.PrimarySwitch
import com.minkyu.yourdailyword.android.ui.theme.YdwTheme

@Composable
@Suppress("FunctionNaming", "LongMethod")
fun SettingsPageView(
	modifier: Modifier = Modifier,
	viewModel: SettingsPageViewModel = hiltViewModel(),
	uiState: SettingsPageUiState = viewModel.uiStateStream.collectAsState(
		initial = SettingsPageUiState.Loading,
	).value,
) {
	val pickImportQuoteFileLauncher = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.GetContent(),
	) {
		viewModel.readQuotesProtoFromInputStream(
			uri = it,
		)
	}

	if (uiState is SettingsPageUiState.Loading) {
		CircularProgressIndicator()
	}
	else if (uiState is SettingsPageUiState.Loaded) {
		Column(
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally,
			modifier = modifier
				.fillMaxWidth(1.0f)
				.fillMaxHeight(1.0f)
				.padding(
					start = 20.dp,
					top = 0.dp,
					end = 20.dp,
					bottom = 0.dp,
				),
		) {
			Text(
				modifier = Modifier
					.align(
						Alignment.Start
					),
				text = stringResource(R.string.settings),
				style = YdwTheme.typography.settingsHeading,
				color = YdwTheme.palette.primaryText,
			)

			Spacer(modifier = Modifier.height(10.dp))

			Text(
				modifier = Modifier
					.align(
						Alignment.Start
					),
				text = stringResource(id = R.string.dark_mode),
				style = YdwTheme.typography.settingsSubheading,
				color = YdwTheme.palette.primaryText,
			)

			Row(
				modifier = Modifier
					.fillMaxWidth(fraction = 1.0f),
				horizontalArrangement = Arrangement.SpaceBetween,
				verticalAlignment = Alignment.CenterVertically,
			) {
				Text(
					text = stringResource(R.string.use_system_default),
					style = YdwTheme.typography.settingsMain,
					color = YdwTheme.palette.primaryText,
				)
				PrimarySwitch(
					checked = uiState.darkModeUseSystemDefault,
					onCheckedChange = viewModel::setDarkModeUseSystemDefault,
				)
			}

			AnimatedVisibility(
				visible = !uiState.darkModeUseSystemDefault,
				enter = expandVertically(),
				exit = shrinkVertically(),
			) {
				Row(
					modifier = Modifier
						.fillMaxWidth(fraction = 1.0f),
					horizontalArrangement = Arrangement.SpaceBetween,
					verticalAlignment = Alignment.CenterVertically,
				) {
					Text(
						text = stringResource(R.string.dark_mode),
						style = YdwTheme.typography.settingsMain,
						color = YdwTheme.palette.primaryText,
					)
					PrimarySwitch(
						checked = uiState.darkModeUserInput,
						onCheckedChange = viewModel::setDarkModeUserInput,
					)
				}
			}

			Spacer(modifier = Modifier.height(20.dp))
			
			Text(
				modifier = Modifier
					.align(
						Alignment.Start
					),
				text = stringResource(R.string.other_actions),
				style = YdwTheme.typography.settingsHeading,
				color = YdwTheme.palette.primaryText,
			)
			SecondaryButton(
				modifier = Modifier.fillMaxWidth(fraction = 1.0f),
				onClick = {
					pickImportQuoteFileLauncher.launch("application/*")
				}
			) {
				Text(
					text = stringResource(R.string.import_quotes),
					color = YdwTheme.palette.secondaryButtonText,
				)
			}

			SecondaryButton(
				modifier = Modifier.fillMaxWidth(fraction = 1.0f),
				onClick = {
					viewModel.onSyncQuotesButtonClicked()
				}
			) {
				Text(
					text = stringResource(R.string.sync_quotes_with_qr_code),
					color = YdwTheme.palette.secondaryButtonText,
				)
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
@Suppress("FunctionNaming")
fun SettingsPageViewPreview() {
	SettingsPageView()
}
