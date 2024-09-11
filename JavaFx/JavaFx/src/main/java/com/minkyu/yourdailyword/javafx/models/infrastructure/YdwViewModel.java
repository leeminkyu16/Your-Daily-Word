package com.minkyu.yourdailyword.javafx.models.infrastructure;

import java.util.ArrayList;
import java.util.List;

public class YdwViewModel {
	private final ArrayList<Runnable> beforeDestroyRunnables = new ArrayList<>();

	public void addBeforeDestroyRunnable(Runnable... runnable) {
		beforeDestroyRunnables.addAll(List.of(runnable));
	}

	public void onResume() {

	}

	public void beforeDestroy() {
		this.beforeDestroyRunnables.forEach(Runnable::run);
	}
}
