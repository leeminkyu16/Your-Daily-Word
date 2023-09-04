package com.minkyu.yourdailyword.javafx.components.center.view;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwLabel;

public class CenterViewDateLabelView extends YdwLabel {
	@Inject
	public CenterViewDateLabelView(CenterViewDateLabelViewModel viewModel) {
		super();
		this.connectViewModel(viewModel);

		this.textProperty().bindBidirectional(viewModel.dateString);
	}
}
