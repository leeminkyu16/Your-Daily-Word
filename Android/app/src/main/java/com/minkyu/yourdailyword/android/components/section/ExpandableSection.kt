package com.minkyu.yourdailyword.android.components.section

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.minkyu.yourdailyword.android.R
import com.minkyu.yourdailyword.android.components.button.TransparentContainerButton
import com.minkyu.yourdailyword.android.ui.theme.YdwTheme

@Composable
@Suppress("FunctionNaming")
fun ExpandableSection(
	modifier: Modifier = Modifier,
	sectionLabel: @Composable () -> Unit,
	expandedMutableState: MutableState<Boolean> = remember{ mutableStateOf(false) },
	content: @Composable () -> Unit,
) {
	var expanded by expandedMutableState
	TransparentContainerButton(
		modifier = modifier,
		onClick = { expanded= !expanded }
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth(fraction = 1.0f),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically,
		) {
			sectionLabel()
			Icon(
				painter = painterResource(
					id =
					if (expanded) R.drawable.baseline_keyboard_arrow_up_24
					else R.drawable.baseline_keyboard_arrow_down_24
				),
				contentDescription =
				if (expanded) stringResource(R.string.up_chevron)
				else stringResource(R.string.down_chevron),
				tint = YdwTheme.palette.primaryText,
			)
		}
	}
	AnimatedVisibility(
		visible = expanded,
		enter = expandVertically(),
		exit = shrinkVertically(),
	) {
		Column(
			modifier = Modifier.fillMaxWidth(fraction = 1.0f),
		) {
			content()
		}
	}
}
