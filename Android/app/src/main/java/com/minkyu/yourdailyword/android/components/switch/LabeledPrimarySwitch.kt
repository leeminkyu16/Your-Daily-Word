package com.minkyu.yourdailyword.android.components.switch

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SwitchColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
@Suppress("LongParameterList", "FunctionNaming")
fun LabeledPrimarySwitch(
	modifier: Modifier = Modifier,
	checked: Boolean,
	onCheckedChange: ((Boolean) -> Unit)?,
	enabled: Boolean = true,
	colors: SwitchColors? = null,
	interactionSource: MutableInteractionSource? = null,
	labelContent: @Composable (() -> Unit)? = null,
) {
	Row(
		modifier = modifier
			.fillMaxWidth(fraction = 1.0f),
		horizontalArrangement = Arrangement.SpaceBetween,
		verticalAlignment = Alignment.CenterVertically,
	) {
		labelContent?.invoke()

		PrimarySwitch(
			modifier = Modifier,
			checked = checked,
			onCheckedChange = onCheckedChange,
			enabled = enabled,
			colors = colors,
			interactionSource = interactionSource,
		)
	}
}
