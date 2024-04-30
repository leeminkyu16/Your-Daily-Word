package com.minkyu.yourdailyword.javafx.models.infrastructure.javafx;

import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwView;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;
import javafx.scene.control.CheckBox;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class YdwCheckBox extends CheckBox implements YdwView {
	private final AtomicReference<YdwViewModel> viewModel = new AtomicReference<>(null);
	private final ArrayList<Runnable> beforeDestroyRunnables = new ArrayList<>();

	public YdwCheckBox() {
		super();
	}

	public YdwCheckBox(YdwObservable<Boolean> booleanYdwObservable) {
		super();

		this.setSelected(booleanYdwObservable.get());
		this.addBeforeDestroyRunnable(
			booleanYdwObservable.bind(this.selectedProperty())
		);
	}

	public YdwCheckBox applyStyleBundleAndReturnThis(YdwStyleBundle styleBundle) {
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
