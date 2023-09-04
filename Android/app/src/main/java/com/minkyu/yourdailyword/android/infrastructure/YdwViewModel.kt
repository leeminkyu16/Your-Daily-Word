package com.minkyu.yourdailyword.android.infrastructure

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

open class YdwViewModel<UiState: Any>(
	vararg childViewModelsParam: YdwChildViewModel<*>
) : ViewModel() {
	open val uiStateStream: Flow<UiState> = emptyFlow()
	private val childViewModels: MutableList<YdwChildViewModel<*>> = mutableListOf()

	init {
		childViewModels.addAll(childViewModelsParam)
	}

	override fun onCleared() {
		childViewModels.forEach {
			it.onCleared()
		}

		super.onCleared()
	}
}
