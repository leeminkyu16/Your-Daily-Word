package com.minkyu.yourdailyword.android.pages.editindividualpage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.minkyu.yourdailyword.android.R
import com.minkyu.yourdailyword.android.components.button.PrimaryButton
import com.minkyu.yourdailyword.android.components.button.SecondaryButton
import com.minkyu.yourdailyword.android.components.section.ExpandableSection
import com.minkyu.yourdailyword.android.components.switch.LabeledPrimarySwitch
import com.minkyu.yourdailyword.android.components.textfield.RoundedNumberTextField
import com.minkyu.yourdailyword.android.components.textfield.RoundedTextField
import com.minkyu.yourdailyword.android.ui.theme.YdwTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
@Suppress("FunctionNaming", "LongMethod")
fun EditIndividualPageView(
	modifier: Modifier = Modifier,
	viewModel: EditIndividualPageViewModel = hiltViewModel(),
	uiState: EditIndividualPageUiState = viewModel.uiStateStream
		.collectAsState(initial = EditIndividualPageUiState.Loading).value,
) {
	if (uiState is EditIndividualPageUiState.Loading) {
		Column(
			modifier = modifier
				.fillMaxSize(fraction = 1.0f),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center,
		) {
			CircularProgressIndicator()
		}
	} else if (uiState is EditIndividualPageUiState.Loaded) {
		val coroutineScope: CoroutineScope = rememberCoroutineScope()

		Column(
			modifier = modifier
				.fillMaxSize(fraction = 1.0f)
				.padding(
					start = 20.dp,
					end = 20.dp,
					top = 0.dp,
					bottom = 0.dp,
				)
				.verticalScroll(rememberScrollState()),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center,
		) {
			Spacer(modifier = Modifier.height(20.dp))

			Text(
				modifier = Modifier.align(
					alignment = Alignment.Start,
				),
				text = stringResource(R.string.quote_subheader),
				color = YdwTheme.palette.primaryText,
				style = YdwTheme.typography.editIndividualHeading,
			)

			Spacer(modifier = Modifier.height(20.dp))

			RoundedTextField(
				modifier = Modifier
					.fillMaxWidth(fraction = 1.0f),
				value = uiState.englishQuoteText,
				onValueChange = {
					coroutineScope.launch(Dispatchers.Default) {
						viewModel.englishQuoteTextStream.emit(it)
					}
				},
				singleLine = false,
				label = {
					Text(
						text = stringResource(R.string.english),
						color = YdwTheme.palette.secondaryText,
					)
				},
			)

			Spacer(modifier = Modifier.height(20.dp))

			Text(
				modifier = Modifier.align(
					alignment = Alignment.Start,
				),
				text = stringResource(R.string.date_subheader),
				color = YdwTheme.palette.primaryText,
				style = YdwTheme.typography.editIndividualHeading,
			)

			Spacer(modifier = Modifier.height(20.dp))

			RoundedNumberTextField(
				modifier = Modifier
					.fillMaxWidth(fraction = 1.0f),
				value = uiState.associatedMonthText,
				onValueChange = {
					coroutineScope.launch(Dispatchers.Default) {
						viewModel.associatedMonthTextStream.emit(it)
					}
				},
				label = {
					Text(
						text = stringResource(R.string.associated_month),
						color = YdwTheme.palette.secondaryText,
					)
				},
			)

			Spacer(modifier = Modifier.height(20.dp))


			RoundedNumberTextField(
				modifier = Modifier
					.fillMaxWidth(fraction = 1.0f),
				value = uiState.associatedDayOfMonthText,
				onValueChange = {
					coroutineScope.launch(Dispatchers.Default) {
						viewModel.associatedDayOfMonthTextStream.emit(it)
					}
				},
				label = {
					Text(
						text = stringResource(R.string.associated_day_of_month),
						color = YdwTheme.palette.secondaryText,
					)
				},
			)


			if (uiState.gregorianCalendarOptionsModel != null) {
				ExpandableSection(
					sectionLabel = {
						Text(
							modifier = Modifier,
							text = stringResource(
								id = R.string.gregorian_calendar_options_subheader
							),
							color = YdwTheme.palette.primaryText,
							style = YdwTheme.typography.editIndividualHeading,
						)
					},
				) {
					LabeledPrimarySwitch(
						checked = uiState.gregorianCalendarOptionsModel.skipOnShortYear,
						onCheckedChange = {
							coroutineScope.launch(Dispatchers.Default) {
								viewModel.gregorianCalendarOptionsModelStream.emit(
									uiState.gregorianCalendarOptionsModel.copy().apply {
										this.skipOnShortYear = it
									},
								)
							}
						}
					) {
						Text(
							text = stringResource(R.string.skip_on_short_year),
							color = YdwTheme.palette.primaryText,
						)
					}
				}
			}
			if (uiState.lunarCalendarOptionsModel != null) {
				ExpandableSection(
					sectionLabel = {
						Text(
							modifier = Modifier,
							text = stringResource(
								id = R.string.lunar_calendar_options_subheader
							),
							color = YdwTheme.palette.primaryText,
							style = YdwTheme.typography.editIndividualHeading,
						)
					},
				) {
					LabeledPrimarySwitch(
						checked = uiState.lunarCalendarOptionsModel.skipOnShortYear,
						onCheckedChange = {
							coroutineScope.launch(Dispatchers.Default) {
								viewModel.lunarCalendarOptionsModelStream.emit(
									uiState.lunarCalendarOptionsModel.copy().apply {
										this.skipOnShortYear = it
									},
								)
							}
						}
					) {
						Text(
							text = stringResource(R.string.skip_on_short_year),
							color = YdwTheme.palette.primaryText,
						)
					}

					LabeledPrimarySwitch(
						checked = uiState.lunarCalendarOptionsModel.skipOnShortMonth,
						onCheckedChange = {
							coroutineScope.launch(Dispatchers.Default) {
								viewModel.lunarCalendarOptionsModelStream.emit(
									uiState.lunarCalendarOptionsModel.copy().apply {
										this.skipOnShortMonth = it
									},
								)
							}
						}
					) {
						Text(
							text = stringResource(R.string.skip_on_short_month),
							color = YdwTheme.palette.primaryText,
						)
					}
				}
			}
			if (uiState.hebrewCalendarOptionsModel != null) {
				ExpandableSection(
					sectionLabel = {
						Text(
							modifier = Modifier,
							text = stringResource(
								id = R.string.hebrew_calendar_options_subheader,
							),
							color = YdwTheme.palette.primaryText,
							style = YdwTheme.typography.editIndividualHeading,
						)
					},
				) {
					LabeledPrimarySwitch(
						checked = uiState.hebrewCalendarOptionsModel.skipOnShortYear,
						onCheckedChange = {
							coroutineScope.launch(Dispatchers.Default) {
								viewModel.hebrewCalendarOptionsModelStream.emit(
									uiState.hebrewCalendarOptionsModel.copy().apply {
										this.skipOnShortYear = it
									},
								)
							}
						}
					) {
						Text(
							text = stringResource(R.string.skip_on_short_year),
							color = YdwTheme.palette.primaryText,
						)
					}
					LabeledPrimarySwitch(
						checked = uiState.hebrewCalendarOptionsModel.skipOnShortMonth,
						onCheckedChange = {
							coroutineScope.launch(Dispatchers.Default) {
								viewModel.hebrewCalendarOptionsModelStream.emit(
									uiState.hebrewCalendarOptionsModel.copy().apply {
										this.skipOnShortMonth = it
									},
								)
							}
						}
					) {
						Text(
							text = stringResource(R.string.skip_on_short_month),
							color = YdwTheme.palette.primaryText,
						)
					}
				}
			}

			Spacer(modifier = Modifier.height(20.dp))

			Row(
				modifier = Modifier
					.fillMaxWidth(fraction = 1.0f),
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.SpaceEvenly,
			) {
				SecondaryButton(
					onClick = {
						viewModel.navHostController.popBackStack()
					},
				) {
					Text(
						text = stringResource(R.string.go_back),
						color = YdwTheme.palette.secondaryButtonText,
					)
				}

				val successMessage: String = stringResource(R.string.save_quote_success_message)
				val failureMessage: String = stringResource(R.string.save_quote_failed_message)
				PrimaryButton(
					onClick = {
						viewModel.onSaveTap(
							uiState = uiState,
							successMessage = successMessage,
							failureMessage = failureMessage,
						)
					},
				) {
					Text(
						stringResource(R.string.save),
						color = YdwTheme.palette.primaryButtonText,
					)
				}
			}

			Spacer(modifier = Modifier.height(20.dp))
		}
	}
}
