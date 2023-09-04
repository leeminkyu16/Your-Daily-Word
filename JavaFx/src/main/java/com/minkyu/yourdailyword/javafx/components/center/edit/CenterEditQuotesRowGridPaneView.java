package com.minkyu.yourdailyword.javafx.components.center.edit;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import com.minkyu.yourdailyword.javafx.models.di.CenterEditQuotesRowGridPaneViewModelFactory;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwWeakReference;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwGridPane;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwNumberTextField;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwTextField;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;

public class CenterEditQuotesRowGridPaneView extends YdwGridPane {
	@Inject
	public CenterEditQuotesRowGridPaneView(
		CenterEditQuotesRowGridPaneViewModelFactory viewModelFactory,
		IInternationalizationModel internationalizationModel,
		@Assisted YdwObservable<Integer> quoteIndex
	) {
		super();
		CenterEditQuotesRowGridPaneViewModel viewModel = viewModelFactory.create(quoteIndex);
		this.connectViewModel(viewModel);

		double[] columnWeights = {
			50, 15, 15, 10, 10
		};
		double[] rowWeights = {
			1,
		};
		setColumnConstraints(
			columnWeights,
			HPos.CENTER
		);
		setRowConstraints(
			rowWeights,
			VPos.CENTER,
			Priority.ALWAYS,
			true
		);

		YdwTextField quoteTextField = new YdwTextField(viewModel.quote);
		addBeforeDestroyRunnable(
			viewModel.quoteDisable.bind(quoteTextField.disableProperty())
		);
		this.safeAdd(quoteTextField, 0, 0, 1, 1);

		YdwNumberTextField monthTextField = new YdwNumberTextField(
			viewModel.month
		);
		addBeforeDestroyRunnable(
			viewModel.monthDisable.bind(monthTextField.disableProperty())
		);
		this.safeAdd(monthTextField, 1, 0, 1, 1);

		YdwNumberTextField dayOfMonthTextField = new YdwNumberTextField(
			viewModel.dayOfMonth
		);
		addBeforeDestroyRunnable(
			viewModel.dayOfMonthDisable.bind(dayOfMonthTextField.disableProperty())
		);
		this.safeAdd(dayOfMonthTextField, 2, 0, 1, 1);

		YdwWeakReference<CenterEditQuotesRowGridPaneViewModel> weakViewModelRef = new YdwWeakReference<>(viewModel);
		Button editButton = new Button(internationalizationModel.getString("edit"));
		editButton.setOnMouseClicked((event) -> {
			weakViewModelRef.doIfNotNull(CenterEditQuotesRowGridPaneViewModel::navigateToIndividualGridPane);
		});
		this.add(editButton, 3, 0, 1, 1);

		Button deleteButton = new Button(internationalizationModel.getString("delete"));
		deleteButton.setOnMouseClicked(event -> {
			weakViewModelRef.doIfNotNull(CenterEditQuotesRowGridPaneViewModel::deleteQuote);
		});
		this.add(deleteButton, 4, 0, 1, 1);
	}
}
