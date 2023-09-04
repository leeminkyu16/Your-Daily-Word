package com.minkyu.yourdailyword.android.components.viewswap

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Suppress("FunctionNaming", "LongParameterList")
@Composable
fun VerticalViewSwap(
	modifier: Modifier = Modifier,
	boolean: Boolean,
	contentOnTrue: @Composable () -> Unit,
	contentOnFalse: @Composable () -> Unit,
	enterTransition: EnterTransition = fadeIn() + expandVertically (),
	exitTransition: ExitTransition = fadeOut() + shrinkVertically(),
) {
	AnimatedVisibility(
		visible = boolean,
		enter = enterTransition,
		exit = exitTransition,
	) {
		Column(
			modifier = modifier,
		) {
			contentOnTrue.invoke()
		}
	}
	AnimatedVisibility(
		visible = !boolean,
		enter = enterTransition,
		exit = exitTransition,
	) {
		Column(
			modifier = modifier,
		) {
			contentOnFalse.invoke()
		}
	}
}
