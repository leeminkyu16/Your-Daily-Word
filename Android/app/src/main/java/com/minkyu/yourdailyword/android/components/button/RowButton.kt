package com.minkyu.yourdailyword.android.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.minkyu.yourdailyword.android.ui.theme.YdwAndroidTheme
import com.minkyu.yourdailyword.android.ui.theme.YdwTheme

@Composable
@Suppress("LongParameterList", "FunctionNaming")
fun TransparentContainerButton(
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	shape: Shape? = null,
	colors: ButtonColors = ButtonDefaults.buttonColors(
		containerColor = Color.Transparent,
	),
	elevation: ButtonElevation? = null,
	border: BorderStroke? = null,
	contentPadding: PaddingValues? = null,
	interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
	content: @Composable RowScope.() -> Unit,
) {
	Button(
		onClick = onClick,
		modifier = modifier,
		enabled = enabled,
		shape = shape ?: RectangleShape,
		colors = colors,
		elevation = elevation,
		border = border,
		contentPadding = contentPadding ?: PaddingValues(0.dp),
		interactionSource = interactionSource,
		content = content,
	)
}

@Preview(showBackground = true)
@Composable
@Suppress("UnusedPrivateMember", "FunctionNaming")
private fun PrimaryButtonPreview() {
	YdwAndroidTheme {
		TransparentContainerButton(onClick = {}) {
			Text(
				text = "Test Button",
				color = YdwTheme.palette.primaryButtonText,
			)
		}
	}
}
