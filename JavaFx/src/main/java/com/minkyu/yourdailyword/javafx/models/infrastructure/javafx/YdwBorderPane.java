package com.minkyu.yourdailyword.javafx.models.infrastructure.javafx;

import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwView;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class YdwBorderPane extends BorderPane implements YdwView {
	private final AtomicReference<YdwViewModel> viewModel = new AtomicReference<>(null);
	private final ArrayList<Runnable> beforeDestroyRunnables = new ArrayList<>();

	public void safeSetCenter(Node value) {
		checkAndAddBeforeDestroy(value);

		this.setCenter(value);
	}

	public void safeSetBottom(Node value) {
		checkAndAddBeforeDestroy(value);

		this.setBottom(value);
	}

	private void checkAndAddBeforeDestroy(Node value) {
		if (value instanceof YdwView) {
			WeakReference<YdwView> weakReference = new WeakReference<>((YdwView) value);

			this.addBeforeDestroyRunnable(() -> {
				YdwView weakValue = weakReference.get();
				if (weakValue != null) {
					weakValue.beforeDestroy();
				}
			});
		}
	}

	@Override
	public void connectViewModel(YdwViewModel viewModel) {
		this.connectViewModel(viewModel, beforeDestroyRunnables);
	}

	@Override
	public void setInternalViewModelReference(YdwViewModel viewModel) {
		this.viewModel.set(viewModel);
	}

	@Override
	public void addBeforeDestroyRunnable(Runnable... runnable) {
		this.addBeforeDestroyRunnable(beforeDestroyRunnables, runnable);
	}

	@Override
	public void beforeDestroy() {
		this.beforeDestroy(beforeDestroyRunnables);
	}
}
