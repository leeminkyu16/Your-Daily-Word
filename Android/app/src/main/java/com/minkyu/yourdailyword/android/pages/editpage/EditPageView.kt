package com.minkyu.yourdailyword.android.pages.editpage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.minkyu.yourdailyword.android.R
import com.minkyu.yourdailyword.android.components.button.PrimaryButton
import com.minkyu.yourdailyword.android.components.button.SecondaryButton
import com.minkyu.yourdailyword.android.models.NavigationDestination
import com.minkyu.yourdailyword.android.ui.theme.YdwTheme
import com.minkyu.yourdailyword.common.protobased.QuoteModel

@Composable
@Suppress("FunctionNaming", "LongMethod")
fun EditPageView(
	modifier: Modifier = Modifier,
	viewModel: EditPageViewModel = hiltViewModel(),
	uiState: EditPageUiState = viewModel.uiStateStream.collectAsState(
		initial = EditPageUiState.Loading
	).value,
) {
	if (uiState is EditPageUiState.Loading) {
		Column(
			modifier = modifier
				.fillMaxSize(fraction = 1.0f),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			CircularProgressIndicator()
		}
	} else if (uiState is EditPageUiState.Loaded) {
		Scaffold(
			modifier = modifier,
			floatingActionButton = {
				FloatingActionButton(onClick = {
					viewModel.navHostController.navigate(
						route = NavigationDestination.EDIT_ADD_PAGE.rootNavPath,
					)
				}) {
					Icon(
						painter = painterResource(
							id = R.drawable.baseline_add_24
						),
						contentDescription = stringResource(
							id = R.string.add,
						),
					)
				}
			}
		) { padding: PaddingValues ->
			LazyColumn(
				modifier = Modifier
					.padding(padding)
					.padding(
						start = 5.dp,
						end = 5.dp,
						top = 0.dp,
						bottom = 0.dp,
					),
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.spacedBy(10.dp),
			) {
				items(uiState.quotes) { item: QuoteModel ->
					Box(
						modifier = Modifier
							.shadow(
								elevation = 2.dp,
								shape = RoundedCornerShape(size = 20.dp),
								ambientColor = YdwTheme.palette.primaryShadow,
								spotColor = YdwTheme.palette.primaryShadow,
							),
					) {
						Box(
							modifier = Modifier
								.background(
									color = YdwTheme.palette.primaryBackground,
								)
								.clip(
									RoundedCornerShape(size = 20.dp)
								),
							contentAlignment = Alignment.Center,
						) {
							Column(
								modifier = Modifier.padding(
									top = 20.dp,
									start = 20.dp,
									end = 20.dp,
									bottom = 10.dp,
								),
							) {
								Row(
									modifier = Modifier
										.fillMaxWidth(fraction = 1.0f),
									horizontalArrangement = Arrangement.SpaceEvenly,
								) {
									Text(
										modifier = Modifier,
										text = stringResource(
											id = R.string.month_colon_number,
											item.associatedMonth,
										),
										style = YdwTheme.typography.smallerSecondaryText,
									)
									Text(
										modifier = Modifier,
										text = stringResource(
											id = R.string.day_of_month_colon_number,
											item.associatedDayOfMonth,
										),
										style = YdwTheme.typography.smallerSecondaryText,
									)
								}
								Text(
									modifier = Modifier
										.padding(
											horizontal = 0.dp,
											vertical = 8.dp,
										),
									text = item.value.english,
									maxLines = 2,
									style = YdwTheme.typography.smallerPrimaryText,
									overflow = TextOverflow.Ellipsis,
								)
								Row(
									modifier = Modifier
										.fillMaxWidth(fraction = 1.0f),
									horizontalArrangement = Arrangement.SpaceEvenly,
								) {
									PrimaryButton(onClick = {
										viewModel.navHostController.navigate(
											route = "${NavigationDestination.EDIT_INDIVIDUAL_PAGE.rootNavPath}/${item.uid}"
										)
									}) {
										Text(
											text = stringResource(R.string.edit),
											color = YdwTheme.palette.primaryButtonText,
										)
									}
									val successMessage: String =
										stringResource(R.string.delete_quote_success_message)
									val failMessage: String =
										stringResource(R.string.delete_quote_fail_message)
									SecondaryButton(
										onClick = {
											viewModel.onDeleteTap(
												quoteId = item.uid,
												successMessage = successMessage,
												failMessage = failMessage
											)
										}
									) {
										Text(
											text = stringResource(R.string.delete),
											color = YdwTheme.palette.secondaryButtonText,
										)
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
