package com.minkyu.yourdailyword.javafx.components.center.edit;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import com.minkyu.yourdailyword.javafx.models.di.CenterEditQuotesRowGridPaneViewFactory;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwGridPane;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwLabel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwColorConstants;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.styledcomponent.YdwPrimaryButton;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Priority;

public class CenterEditQuotesGridPaneView extends YdwGridPane {
	final CenterEditQuotesGridPaneViewModel viewModel;

	@Inject
	public CenterEditQuotesGridPaneView(
		CenterEditQuotesGridPaneViewModel viewModel,
		CenterEditPaginationBarHBoxView centerEditPaginationBarHBoxView,
		CenterEditQuotesRowGridPaneViewFactory rowViewFactory,
		IInternationalizationModel internationalizationModel
	) {
		super();
		this.viewModel = viewModel;
		this.connectViewModel(viewModel);
		new YdwStyleBundle()
			.setBackgroundColor(YdwColorConstants.primaryBackgroundColorLight)
			.apply(this);

		int numOfColumns = 5;
		int numOfRows = this.viewModel.sharedState.pageSize.get() + 3;
		String[] columnHeaders = {
			internationalizationModel.getString("quote"),
			internationalizationModel.getString("month"),
			internationalizationModel.getString("day"),
			"",
			""
		};
		double[] widthPercentages = {
			50,
			10,
			10,
			15,
			15
		};
		double[] heightPercentages = {
			21,
			10,
			10,
			10,
			10,
			10,
			12,
			12
		};

		setColumnConstraints(
			widthPercentages,
			HPos.CENTER
		);
		setRowConstraints(
			heightPercentages,
			VPos.CENTER,
			Priority.ALWAYS,
			true
		);

		for (int i = 0; i < numOfColumns; i++) {
			this.add(
				new YdwLabel(columnHeaders[i])
					.setWrapTextWithThis(true),
				i,
				0,
				1,
				1
			);
		}

		for (int i = 0; i < viewModel.quoteIndices.size(); i++) {
			int adjustedRowIndex = i + 1;

			this.add(rowViewFactory.create(viewModel.quoteIndices.get(i)), 0, adjustedRowIndex, 5, 1);
		}

		this.add(
			new YdwPrimaryButton(internationalizationModel.getString("add_quote_title"))
				.setOnMouseClickedAndReturnThis(event -> viewModel.addQuote()),
			0,
			numOfRows - 2,
			numOfColumns,
			1
		);
		this.add(centerEditPaginationBarHBoxView, 0, numOfRows - 1, numOfColumns, 1);
	}
}
