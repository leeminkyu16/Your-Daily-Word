package com.minkyu.yourdailyword.javafx.models.infrastructure.javafx;

import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwView;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class YdwScrollPane extends ScrollPane implements YdwView {
	private final AtomicReference<YdwViewModel> viewModel = new AtomicReference<>(null);
	private final ArrayList<Runnable> beforeDestroyRunnables = new ArrayList<>();

	public YdwScrollPane(final YdwViewModel viewModel) {
		this.connectViewModel(viewModel);
	}

	public void setSafeContent(Node node) {
		this.setContent(node);

		if (node instanceof YdwView) {
			WeakReference<YdwView> weakReference = new WeakReference<>((YdwView) node);

			this.addBeforeDestroyRunnable(
				() -> {
					YdwView weakView = weakReference.get();
					if (weakView != null) {
						weakView.beforeDestroy();
					}
				}
			);
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
