package com.minkyu.yourdailyword.javafx.models.infrastructure;

import java.util.ArrayList;
import java.util.List;

public interface YdwView {
	void connectViewModel(YdwViewModel viewModel);

	void setInternalViewModelReference(YdwViewModel viewModel);

	default void connectViewModel(YdwViewModel viewModel, ArrayList<Runnable> beforeDestroyRunnables) {
		setInternalViewModelReference(viewModel);
		if (viewModel != null) {
			YdwWeakReference<YdwViewModel> weakViewModelRef = new YdwWeakReference<>(viewModel);
			YdwWeakReference<YdwView> weakThisRef = new YdwWeakReference<>(this);
			this.addBeforeDestroyRunnable(
				beforeDestroyRunnables,
				() -> weakViewModelRef.doIfNotNull(YdwViewModel::beforeDestroy),
				() -> weakThisRef.doIfNotNull(weakThis -> weakThis.setInternalViewModelReference(null))
			);
		}
	}

	void addBeforeDestroyRunnable(Runnable... runnable);

	default void addBeforeDestroyRunnable(ArrayList<Runnable> beforeDestroyRunnables, Runnable... runnable) {
		beforeDestroyRunnables.addAll(List.of(runnable));
	}

	default void onResume(YdwViewModel viewModel) {
		viewModel.onResume();
	}

	void beforeDestroy();

	default void beforeDestroy(ArrayList<Runnable> beforeDestroyRunnables) {
		beforeDestroyRunnables.forEach(Runnable::run);
	}
}
