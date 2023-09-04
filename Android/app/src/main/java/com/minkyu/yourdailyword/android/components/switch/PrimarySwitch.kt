package com.minkyu.yourdailyword.android.components.switch

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.minkyu.yourdailyword.android.ui.theme.YdwTheme

@Composable
@Suppress("LongParameterList", "FunctionNaming")
fun PrimarySwitch(
	checked: Boolean,
	onCheckedChange: ((Boolean) -> Unit)?,
	modifier: Modifier = Modifier,
	thumbContent: (@Composable () -> Unit)? = null,
	enabled: Boolean? = null,
	colors: SwitchColors? = null,
	interactionSource: MutableInteractionSource? = null,
) {
	val internalInteractionSource = interactionSource ?: remember { MutableInteractionSource() }

	Switch(
		checked = checked,
		onCheckedChange = onCheckedChange,
		modifier = modifier,
		thumbContent = thumbContent,
		enabled = enabled ?: true,
		colors = colors ?: SwitchDefaults.colors(
			checkedTrackColor = YdwTheme.palette.primaryButtonBackground,
			checkedBorderColor = YdwTheme.palette.primaryButtonBackground,
			uncheckedTrackColor = YdwTheme.palette.secondaryButtonBackground,
			uncheckedBorderColor = YdwTheme.palette.secondaryButtonBackground,
			checkedThumbColor = YdwTheme.palette.primaryButtonText,
			uncheckedThumbColor = YdwTheme.palette.primaryButtonText,
			disabledUncheckedTrackColor = YdwTheme.palette.secondaryButtonBackground,
			disabledUncheckedThumbColor = YdwTheme.palette.primaryButtonText,
			disabledUncheckedBorderColor = YdwTheme.palette.secondaryButtonBackground,
		),
		interactionSource = internalInteractionSource,
	)
}

@Preview(showBackground = true)
@Composable
@Suppress("UnusedPrivateMember", "FunctionNaming")
private fun PrimarySwitchCheckedPreview() {
	PrimarySwitch(checked = true, onCheckedChange = {})
}

@Preview(showBackground = true)
@Composable
@Suppress("UnusedPrivateMember", "FunctionNaming")
private fun PrimarySwitchUncheckedPreview() {
	PrimarySwitch(checked = false, onCheckedChange = {})
}
