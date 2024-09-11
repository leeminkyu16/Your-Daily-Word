package com.minkyu.yourdailyword.android.pages.viewpage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.minkyu.yourdailyword.android.R
import com.minkyu.yourdailyword.android.components.button.PrimaryButton
import com.minkyu.yourdailyword.android.models.QuotesManager
import com.minkyu.yourdailyword.android.services.FlagsService
import com.minkyu.yourdailyword.android.services.IoService
import com.minkyu.yourdailyword.android.ui.theme.YdwAndroidTheme
import com.minkyu.yourdailyword.android.ui.theme.YdwTheme
import com.minkyu.yourdailyword.common.calendar.GregorianCalendarModel
import com.minkyu.yourdailyword.common.calendar.HebrewCalendarModel
import com.minkyu.yourdailyword.common.calendar.LunarCalendarModel

@Suppress("FunctionNaming", "LongMethod")
@Composable
fun ViewPageView(
	modifier: Modifier = Modifier,
	viewModel: ViewPageViewModel = hiltViewModel(),
	uiState: ViewPageUiState = viewModel.uiStateStream.collectAsState().value,
) {
	val clipboardManager: ClipboardManager = LocalClipboardManager.current

	if (uiState is ViewPageUiState.Loading) {
		Column(
			modifier = modifier
				.fillMaxSize(fraction = 1.0f),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			CircularProgressIndicator()
		}
	}
	else if (uiState is ViewPageUiState.Loaded) {
		Column(
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally,
			modifier = modifier
				.fillMaxSize(1.0f)
				.padding(
					start = 20.dp,
					top = 0.dp,
					end = 20.dp,
					bottom = 0.dp,
				)
		) {
			Text(
				modifier = Modifier
					.fillMaxWidth(fraction = 1.0f),
				text = stringResource(
					id = R.string.today_is_date,
					uiState.dayText,
					uiState.monthText,
					uiState.yearText,
				),
				style = YdwTheme.typography.secondaryText,
				textAlign = TextAlign.Center,
				color = YdwTheme.palette.primaryText,
			)
			Spacer(
				modifier = Modifier
					.height(10.dp),
			)
			Text(
				modifier = Modifier
					.fillMaxWidth(fraction = 1.0f),
				text = uiState.quote ?: stringResource(R.string.no_quote_found),
				style = YdwTheme.typography.quote,
				textAlign = TextAlign.Center,
				color = YdwTheme.palette.primaryText,
			)

			Spacer(
				modifier = Modifier
					.height(20.dp),
			)

			PrimaryButton(
				modifier = Modifier
					.fillMaxWidth(fraction = 1.0f),
				onClick = {
					uiState.quote?.let {
						clipboardManager.setText(
							AnnotatedString(it)
						)
					}
				}
			) {
				Text(
					text = stringResource(R.string.copy_quote),
					fontSize = 15.sp,
					color = YdwTheme.palette.primaryButtonText,
				)
			}

			PrimaryButton(
				modifier = Modifier
					.fillMaxWidth(fraction = 1.0f),
				onClick = {
					uiState.quote?.let { quote ->
						viewModel.onShareQuoteButtonTapped(quote=quote)
					}
				}
			) {
				Text(
					text = stringResource(R.string.share_quote),
					fontSize = 15.sp,
					color = YdwTheme.palette.primaryButtonText,
				)
			}
		}
	}
}

@Suppress("FunctionNaming")
@Preview(
	showBackground = true,
)
@Composable
fun ViewPageViewPreview() {
	YdwAndroidTheme {
		ViewPageView(
			viewModel = ViewPageViewModel(
				quotesManager = QuotesManager(
					ioService = IoService(context = LocalContext.current),
					flagsService = FlagsService(),
				),
				lazyGregorianCalendarModel = {
					GregorianCalendarModel()
				},
				lazyLunarCalendarModel = {
					LunarCalendarModel()
				},
				lazyHebrewCalendarModel = {
					HebrewCalendarModel()
				},
				context = LocalContext.current,
			),
			uiState = ViewPageUiState.Loading,
		)
	}
}
