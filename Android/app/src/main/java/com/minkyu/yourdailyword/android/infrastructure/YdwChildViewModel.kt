package com.minkyu.yourdailyword.android.infrastructure

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class YdwChildViewModel<UiState: Any>(
	vararg childViewModelsParam: YdwChildViewModel<*>,
) {
	private val childViewModels: MutableList<YdwChildViewModel<*>> = mutableListOf()
	open val uiStateStream: Flow<UiState> = emptyFlow()

	init {
		childViewModels.addAll(childViewModelsParam)
	}

	fun onCleared() {
		childViewModels.forEach {
			it.onCleared()
		}
	}
}
