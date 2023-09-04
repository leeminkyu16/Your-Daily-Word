package com.minkyu.yourdailyword.android.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import javax.annotation.concurrent.Immutable

private val quoteText = TextStyle(
	fontFamily = FontFamily.Default,
	fontWeight = FontWeight.Normal,
	fontSize = 16.sp,
	lineHeight = 20.sp,
	letterSpacing = 0.5.sp,
)

private val settingsHeadingText = TextStyle(
	fontFamily = FontFamily.Default,
	fontWeight = FontWeight.Bold,
	fontSize = 24.sp,
	lineHeight = 28.sp,
	letterSpacing = 0.5.sp,
)

private val settingsSubheadingText = TextStyle(
	fontFamily = FontFamily.Default,
	fontWeight = FontWeight.Bold,
	fontSize = 20.sp,
	lineHeight = 24.sp,
	letterSpacing = 0.5.sp,
)

private val settingsMainText = TextStyle (
	fontFamily = FontFamily.Default,
	fontWeight = FontWeight.Normal,
	fontSize = 16.sp,
	lineHeight = 20.sp,
	letterSpacing = 0.5.sp,
)

private val editIndividualHeadingText = TextStyle(
	fontFamily = FontFamily.Default,
	fontWeight = FontWeight.Bold,
	fontSize = 16.sp,
	lineHeight = 20.sp,
	letterSpacing = 0.5.sp,
)

@Immutable
data class YdwCustomTypography(
	val quote: TextStyle = TextStyle(),
	val settingsHeading: TextStyle = TextStyle(),
	val settingsSubheading: TextStyle = TextStyle(),
	val settingsMain: TextStyle = TextStyle(),
	val editIndividualHeading: TextStyle = TextStyle(),
)

val LocalYdwCustomTypography: ProvidableCompositionLocal<YdwCustomTypography> =
	staticCompositionLocalOf {
		YdwCustomTypography()
	}

val DefaultYdwCustomTypography = YdwCustomTypography(
	quote = quoteText,
	settingsHeading = settingsHeadingText,
	settingsSubheading = settingsSubheadingText,
	settingsMain = settingsMainText,
	editIndividualHeading = editIndividualHeadingText,
)

val YdwTypography = Typography(
	bodyLarge = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp,
		lineHeight = 24.sp,
		letterSpacing = 0.5.sp
	)
	/* Other default text styles to override
	titleLarge = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 22.sp,
		lineHeight = 28.sp,
		letterSpacing = 0.sp
	),
	labelSmall = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Medium,
		fontSize = 11.sp,
		lineHeight = 16.sp,
		letterSpacing = 0.5.sp
	)
	*/
)
