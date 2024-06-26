package com.minkyu.yourdailyword.javafx.models.infrastructure.javafx;

import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwView;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class YdwLabel extends Label implements YdwView {

	public YdwLabel() {
		super();
	}

	public YdwLabel(String value) {
		super(value);
	}

	public YdwLabel(YdwObservable<String> textObservable) {
		super(textObservable.get());
		this.addBeforeDestroyRunnable(
			textObservable.bind(this.textProperty())
		);
	}

	public YdwLabel setWrapTextWithThis(boolean value) {
		this.setWrapText(value);
		return this;
	}

	public YdwLabel setStyleAndReturnThis(String style) {
		this.setStyle(style);
		return this;
	}

	private final AtomicReference<YdwViewModel> viewModel = new AtomicReference<>(null);
	private final ArrayList<Runnable> beforeDestroyRunnables = new ArrayList<>();

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
