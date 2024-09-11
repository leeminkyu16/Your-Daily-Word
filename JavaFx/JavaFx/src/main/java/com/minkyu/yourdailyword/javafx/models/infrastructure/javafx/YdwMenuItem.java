package com.minkyu.yourdailyword.javafx.models.infrastructure.javafx;

import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwView;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class YdwMenuItem extends MenuItem implements YdwView {
	private final AtomicReference<YdwViewModel> viewModel = new AtomicReference<>(null);
	private final ArrayList<Runnable> beforeDestroyRunnables = new ArrayList<>();

	public YdwMenuItem() {
	}

	public YdwMenuItem(String var1) {
		super(var1);
	}

	public YdwMenuItem applyStyleBundleAndReturnThis(YdwStyleBundle styleBundle) {
		this.setStyle(styleBundle.buildStyleString());
		this.getStyleClass().addAll(styleBundle.getStyleClasses());

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
