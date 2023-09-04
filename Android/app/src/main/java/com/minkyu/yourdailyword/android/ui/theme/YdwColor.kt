@file:Suppress("MagicNumber", "TooManyFunctions")

package com.minkyu.yourdailyword.android.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import javax.annotation.concurrent.Immutable

val Blue900: Color = Color(0xFF0D47A1)
val BlueGrey900: Color = Color(0xFF263238)
val Indigo900: Color = Color(0xFF1A237E)
val Purple900: Color = Color(0xFF4A148C)

val Blue500: Color = Color(0xFF2196F3)
val BlueGrey500: Color = Color(0xFF607D8B)
val Indigo500: Color = Color(0xFF3F51B5)
val Purple500: Color = Color(0xFF9C27B0)

private val primaryIndicatorColorDark: Color = Color(0xFFB9D1F8)
private val primaryIndicatorColorLight: Color = Color(0xFF071F46)

private val secondaryIndicatorColorDark: Color = Color(0xFF1F292E)
private val secondaryIndicatorColorLight: Color = Color(0xFFD1DBE0)

private val primaryTextColorDark: Color = Color(0xFFFFFFFF)
private val primaryTextColorLight: Color = Color(0xFF000000)

private val secondaryTextDark: Color = Color(0xFFCDCDCD)
private val secondaryTextLight: Color = Color(0xFF323232)

private val primaryBackgroundColorDark: Color = Color(0xFF000000)
private val primaryBackgroundColorLight: Color = Color(0xFFFFFFFF)

private val secondaryBackgroundColorDark: Color = Color(0xFF1E1E1E)
private val secondaryBackgroundColorLight: Color = Color(0xFFE1E1E1)

private val primaryShadowColorDark: Color = Color(0xFFFFFFFF)
private val primaryShadowColorLight: Color = Color(0xFF000000)

private val primaryButtonBackgroundColorDark: Color = primaryIndicatorColorDark
private val primaryButtonBackgroundColorLight: Color = primaryIndicatorColorLight

private val primaryButtonTextColorDark: Color = Color(0xFF000000)
private val primaryButtonTextColorLight: Color = Color(0xFFFFFFFF)

private val secondaryButtonBackgroundColorDark: Color = Color(0xFF1F292E)
private val secondaryButtonBackgroundColorLight: Color = Color(0xFFD1DBE0)

private val secondaryButtonTextColorDark: Color = Color(0xFFBFBFBF)
private val secondaryButtonTextColorLight: Color = Color(0xFF404040)

private val textFieldBackgroundColorDark: Color = Color(0xFF2B2930)
private val textFieldBackgroundColorLight: Color = Color(0xFFF0F0F0)

private val textFieldIndicatorColorDark: Color = primaryIndicatorColorDark
private val textFieldIndicatorColorLight: Color = primaryIndicatorColorLight

private val snackbarContainerColorDark: Color = secondaryIndicatorColorDark
private val snackbarContainerColorLight: Color = secondaryIndicatorColorLight

private val snackbarContentColorDark: Color = secondaryTextDark
private val snackbarContentColorLight: Color = secondaryTextLight

	/*
	private fun getREPLACE_MEColor(darkTheme: Boolean): Color {
		return if (darkTheme) {
			REPLACE_MEDark
		} else {
			REPLACE_MELight
		}
	}
	private val REPLACE_MEDark: Color =
	private val REPLACE_MELight: Color =
	 */

@Immutable
data class YdwCustomColorPalette(
	val primaryIndicator: Color = Color.Unspecified,
	val secondaryIndicator: Color = Color.Unspecified,
	val primaryText: Color = Color.Unspecified,
	val secondaryText: Color = Color.Unspecified,
	val primaryBackground: Color = Color.Unspecified,
	val secondaryBackground: Color = Color.Unspecified,
	val primaryShadow: Color = Color.Unspecified,
	val primaryButtonBackground: Color = Color.Unspecified,
	val primaryButtonText: Color = Color.Unspecified,
	val secondaryButtonBackground: Color = Color.Unspecified,
	val secondaryButtonText: Color = Color.Unspecified,
	val textFieldBackground: Color = Color.Unspecified,
	val textFieldIndicator: Color = Color.Unspecified,
	val snackbarContainer: Color = Color.Unspecified,
	val snackbarContent: Color = Color.Unspecified,
)

val LocalYdwCustomColorPalette: ProvidableCompositionLocal<YdwCustomColorPalette> =
	staticCompositionLocalOf {
		YdwCustomColorPalette()
	}

val OnLightYdwCustomColorPalette = YdwCustomColorPalette(
	primaryIndicator = primaryIndicatorColorLight,
	secondaryIndicator = secondaryIndicatorColorLight,
	primaryText = primaryTextColorLight,
	secondaryText = secondaryTextLight,
	primaryBackground = primaryBackgroundColorLight,
	secondaryBackground = secondaryBackgroundColorLight,
	primaryShadow = primaryShadowColorLight,
	primaryButtonBackground = primaryButtonBackgroundColorLight,
	primaryButtonText = primaryButtonTextColorLight,
	secondaryButtonBackground = secondaryButtonBackgroundColorLight,
	secondaryButtonText = secondaryButtonTextColorLight,
	textFieldBackground = textFieldBackgroundColorLight,
	textFieldIndicator = textFieldIndicatorColorLight,
	snackbarContainer = snackbarContainerColorLight,
	snackbarContent = snackbarContentColorLight,
)

val OnDarkYdwCustomColorPalette = YdwCustomColorPalette(
	primaryIndicator = primaryIndicatorColorDark,
	secondaryIndicator = secondaryIndicatorColorDark,
	primaryText = primaryTextColorDark,
	secondaryText = secondaryTextDark,
	primaryBackground = primaryBackgroundColorDark,
	secondaryBackground = secondaryBackgroundColorDark,
	primaryShadow = primaryShadowColorDark,
	primaryButtonBackground = primaryButtonBackgroundColorDark,
	primaryButtonText = primaryButtonTextColorDark,
	secondaryButtonBackground = secondaryButtonBackgroundColorDark,
	secondaryButtonText = secondaryButtonTextColorDark,
	textFieldBackground = textFieldBackgroundColorDark,
	textFieldIndicator = textFieldIndicatorColorDark,
	snackbarContainer = snackbarContainerColorDark,
	snackbarContent = snackbarContentColorDark,
)

internal val YdwDarkColorScheme: ColorScheme = darkColorScheme(
	primary = Blue900,
	secondary = BlueGrey900,
	tertiary = Purple900,
	background = primaryBackgroundColorDark,
	surface = primaryBackgroundColorDark,
)

internal val YdwLightColorScheme: ColorScheme = lightColorScheme(
	primary = Blue500,
	secondary = BlueGrey500,
	tertiary = Purple500,
	background = primaryBackgroundColorLight,
	surface = primaryBackgroundColorLight,
)
