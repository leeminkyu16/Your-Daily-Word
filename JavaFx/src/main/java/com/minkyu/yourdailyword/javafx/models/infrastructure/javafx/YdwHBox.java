package com.minkyu.yourdailyword.javafx.models.infrastructure.javafx;

import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwView;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class YdwHBox extends HBox implements YdwView {
	private final AtomicReference<YdwViewModel> viewModel = new AtomicReference<>(null);
	private final ArrayList<Runnable> beforeDestroyRunnables = new ArrayList<>();

	public void safeAddChildren(Node... child) {
		List<Node> children = List.of(child);

		children.forEach((childElement) -> {
			if (childElement instanceof YdwView) {
				WeakReference<YdwView> weakReference = new WeakReference<>((YdwView) childElement);

				addBeforeDestroyRunnable(() -> {
					YdwView weakThis = weakReference.get();
					if (weakThis != null) {
						weakThis.beforeDestroy();
					}
				});
			}
		});

		this.getChildren().addAll(child);
	}

	public YdwHBox setStyleAndReturnThis(String newStyle) {
		this.setStyle(newStyle);
		return this;
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
