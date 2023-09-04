package com.minkyu.yourdailyword.android.pages.addindividualpage

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
import com.minkyu.yourdailyword.android.components.viewswap.VerticalViewSwap
import com.minkyu.yourdailyword.android.ui.theme.YdwTheme
import com.minkyu.yourdailyword.common.protobased.QuoteGregorianCalendarOptionsModel
import com.minkyu.yourdailyword.common.protobased.QuoteHebrewCalendarOptionsModel
import com.minkyu.yourdailyword.common.protobased.QuoteLunarCalendarOptionsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("FunctionNaming", "LongMethod")
@Composable
fun AddIndividualPageView(
	modifier: Modifier = Modifier,
	viewModel: AddIndividualPageViewModel = hiltViewModel(),
	uiState: AddIndividualPageUiState = viewModel.uiStateStream.collectAsState(
		initial = AddIndividualPageUiState.Loading,
	).value,
) {
	if (uiState is AddIndividualPageUiState.Loading) {
		Column(
			modifier = modifier.fillMaxSize(fraction = 1.0f),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			CircularProgressIndicator()
		}
	}
	else if (uiState is AddIndividualPageUiState.Loaded) {
		val coroutineScope: CoroutineScope = rememberCoroutineScope()

		Column(
			modifier = modifier
				.fillMaxSize(fraction = 1.0f)
				.verticalScroll(rememberScrollState())
				.padding(
					start = 20.dp,
					end = 20.dp,
					top = 0.dp,
					bottom = 0.dp,
				),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally,
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

			VerticalViewSwap(
				boolean = uiState.gregorianCalendarOptionsModel != null,
				contentOnTrue = {
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
				},
				contentOnFalse = {
					Spacer(modifier = Modifier.height(20.dp))
					SecondaryButton(
						modifier = Modifier.fillMaxWidth(fraction = 1.0f),
						onClick = {
							coroutineScope.launch {
								viewModel.gregorianCalendarOptionsModelStream.emit(
									QuoteGregorianCalendarOptionsModel()
								)
							}
						}
					) {
						Text(
							text = stringResource(R.string.add_gregorian_options),
							color = YdwTheme.palette.secondaryButtonText,
						)
					}
				}
			)

			VerticalViewSwap(
				boolean = uiState.lunarCalendarOptionsModel != null,
				contentOnTrue = {
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
				},
				contentOnFalse = {
					Spacer(modifier = Modifier.height(20.dp))
					SecondaryButton(
						modifier = Modifier.fillMaxWidth(fraction = 1.0f),
						onClick = {
							coroutineScope.launch {
								viewModel.lunarCalendarOptionsModelStream.emit(
									QuoteLunarCalendarOptionsModel()
								)
							}
						}
					) {
						Text(
							text = stringResource(R.string.add_lunar_calendar_options),
							color = YdwTheme.palette.secondaryButtonText,
						)
					}
				}
			)

			VerticalViewSwap(
				boolean = uiState.hebrewCalendarOptionsModel != null,
				contentOnTrue = {
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
				},
				contentOnFalse = {
					Spacer(modifier = Modifier.height(20.dp))
					SecondaryButton(
						modifier = Modifier.fillMaxWidth(fraction = 1.0f),
						onClick = {
							coroutineScope.launch {
								viewModel.hebrewCalendarOptionsModelStream.emit(
									QuoteHebrewCalendarOptionsModel()
								)
							}
						}
					) {
						Text(
							text = stringResource(R.string.add_hebrew_calendar_options),
							color = YdwTheme.palette.secondaryButtonText,
						)
					}
				},
			)

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

				val onSuccessMessage: String = stringResource(R.string.add_quote_success_message)
				val onFailureMessage: String = stringResource(R.string.add_quote_failure_message)
				PrimaryButton(
					onClick = {
						viewModel.onAddAndSave(
							uiState = uiState,
							onSuccessMessage = onSuccessMessage,
							onFailureMessage = onFailureMessage,
						)
					},
				) {
					Text(
						text = stringResource(
							id = R.string.add_and_save,
						),
						color = YdwTheme.palette.primaryButtonText,
					)
				}
			}
		}
	}
}
