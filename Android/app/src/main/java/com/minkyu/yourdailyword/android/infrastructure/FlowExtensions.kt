package com.minkyu.yourdailyword.android.infrastructure

import kotlinx.coroutines.flow.Flow

@Suppress("LongParameterList", "MagicNumber")
inline fun <reified T1, reified T2, reified T3, reified T4, reified T5, reified T6, R> combine(
	flow: Flow<T1>,
	flow2: Flow<T2>,
	flow3: Flow<T3>,
	flow4: Flow<T4>,
	flow5: Flow<T5>,
	flow6: Flow<T6>,
	crossinline transform: suspend (T1, T2, T3, T4, T5, T6) -> R
): Flow<R> = kotlinx.coroutines.flow.combine(listOf(flow, flow2, flow3, flow4, flow5 ,flow6)) { args ->
	assert(args[0] is T1)
	assert(args[1] is T2)
	assert(args[2] is T3)
	assert(args[3] is T4)
	assert(args[4] is T5)
	assert(args[5] is T6)

	transform(
		args[0] as T1,
		args[1] as T2,
		args[2] as T3,
		args[3] as T4,
		args[4] as T5,
		args[5] as T6,
	)
}
