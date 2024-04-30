package com.minkyu.yourdailyword.javafx.models.infrastructure.javafx;

import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwView;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class YdwTextField extends TextField implements YdwView {
	private final AtomicReference<YdwViewModel> viewModel = new AtomicReference<>(null);
	private final ArrayList<Runnable> beforeDestroyRunnables = new ArrayList<>();

	public YdwTextField() {
	}

	public YdwTextField(YdwObservable<String> stringYdwObservable) {
		this.setText(stringYdwObservable.get());
		this.addBeforeDestroyRunnable(
			stringYdwObservable.bind(this.textProperty())
		);
	}

	public YdwTextField applyStyleBundleAndReturnThis(YdwStyleBundle styleBundle) {
		styleBundle.apply(this);
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
