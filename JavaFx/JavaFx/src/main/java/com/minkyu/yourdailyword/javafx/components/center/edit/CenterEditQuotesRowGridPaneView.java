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
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.styledcomponent.YdwPrimaryButton;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
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

		new YdwStyleBundle()
			.setHGap(8)
			.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.STANDARD_GRID_PANE)
			.apply(this);

		double[] columnWeights = {
			50, 10, 10, 15, 15
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
		new YdwStyleBundle()
			.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.ROUNDED_TEXT_BOX)
			.apply(quoteTextField);
		addBeforeDestroyRunnable(
			viewModel.quoteDisable.bind(quoteTextField.disableProperty())
		);
		this.safeAdd(quoteTextField, 0, 0, 1, 1);

		YdwNumberTextField monthTextField = new YdwNumberTextField(
			viewModel.month
		);
		new YdwStyleBundle()
			.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.ROUNDED_TEXT_BOX)
			.apply(monthTextField);
		addBeforeDestroyRunnable(
			viewModel.monthDisable.bind(monthTextField.disableProperty())
		);
		this.safeAdd(monthTextField, 1, 0, 1, 1);

		YdwNumberTextField dayOfMonthTextField = new YdwNumberTextField(
			viewModel.dayOfMonth
		);
		new YdwStyleBundle()
			.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.ROUNDED_TEXT_BOX)
			.apply(dayOfMonthTextField);
		addBeforeDestroyRunnable(
			viewModel.dayOfMonthDisable.bind(dayOfMonthTextField.disableProperty())
		);
		this.safeAdd(dayOfMonthTextField, 2, 0, 1, 1);

		YdwWeakReference<CenterEditQuotesRowGridPaneViewModel> weakViewModelRef = new YdwWeakReference<>(viewModel);
		YdwPrimaryButton editButton = new YdwPrimaryButton(internationalizationModel.getString("edit"));
		editButton.setOnMouseClicked((event) -> {
			weakViewModelRef.doIfNotNull(CenterEditQuotesRowGridPaneViewModel::navigateToIndividualGridPane);
		});
		this.safeAdd(editButton, 3, 0, 1, 1);

		YdwPrimaryButton deleteButton = new YdwPrimaryButton(internationalizationModel.getString("delete"));
		deleteButton.setOnMouseClicked(event -> {
			weakViewModelRef.doIfNotNull(CenterEditQuotesRowGridPaneViewModel::deleteQuote);
		});
		this.safeAdd(deleteButton, 4, 0, 1, 1);
	}
}
