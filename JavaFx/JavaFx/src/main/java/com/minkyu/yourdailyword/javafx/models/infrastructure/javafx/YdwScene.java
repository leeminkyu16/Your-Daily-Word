package com.minkyu.yourdailyword.javafx.models.infrastructure.javafx;

import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwView;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwWeakReference;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class YdwScene extends Scene implements YdwView {
	private final AtomicReference<YdwViewModel> viewModel = new AtomicReference<>(null);
	private final ArrayList<Runnable> beforeDestroyRunnables = new ArrayList<>();

	public YdwScene(Parent root, double width, double height) {
		super(root, width, height);

		if (root instanceof YdwView) {
			YdwWeakReference<YdwView> weakReference = new YdwWeakReference<>((YdwView) root);

			this.addBeforeDestroyRunnable(() -> {
				weakReference.doIfNotNull(YdwView::beforeDestroy);
			});
		}
	}

	public YdwScene(Parent root) {
		super(root);

		if (root instanceof YdwView) {
			YdwWeakReference<YdwView> weakReference = new YdwWeakReference<>((YdwView) root);

			this.addBeforeDestroyRunnable(() -> {
				weakReference.doIfNotNull(YdwView::beforeDestroy);
			});
		}
	}

	public void addDarkModeAwareStyleSheets(YdwObservable<Boolean> darkModeObservable, String lightStyleSheet, String darkStyleSheet) {
		if (darkModeObservable.get()) {
			this.getStylesheets().add(darkStyleSheet);
		} else {
			this.getStylesheets().add(lightStyleSheet);
		}

		YdwWeakReference<YdwScene> weakThisRef = new YdwWeakReference<>(this);

		this.addBeforeDestroyRunnable(
			darkModeObservable.addAfterChange((oldValue, newValue) -> {
				weakThisRef.doIfNotNull(weakThis -> {
					weakThis.getStylesheets().remove(lightStyleSheet);
					weakThis.getStylesheets().remove(darkStyleSheet);

					if (newValue) {
						weakThis.getStylesheets().add(darkStyleSheet);
					} else {
						weakThis.getStylesheets().add(lightStyleSheet);
					}
				});
			})
		);
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
