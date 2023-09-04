package com.minkyu.yourdailyword.javafx.components.center.view;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwTextArea;

public class CenterViewQuoteTextAreaView extends YdwTextArea {
	@Inject
	public CenterViewQuoteTextAreaView(
		CenterViewQuoteTextAreaViewModel viewModel
	) {
		super();
		this.connectViewModel(viewModel);

		this.setEditable(false);
		this.setWrapText(true);

		this.textProperty().bindBidirectional(viewModel.quote);
	}
}
