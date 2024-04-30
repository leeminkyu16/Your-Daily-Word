package com.minkyu.yourdailyword.javafx.components.center.view;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwTextArea;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;

public class CenterViewQuoteTextAreaView extends YdwTextArea {
	@Inject
	public CenterViewQuoteTextAreaView(
		CenterViewQuoteTextAreaViewModel viewModel
	) {
		super();
		this.connectViewModel(viewModel);

		new YdwStyleBundle()
			.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.PRIMARY_TEXT_AREA)
			.setFontSize(16)
			.apply(this);

		this.setFocused(false);

		this.setEditable(false);
		this.setWrapText(true);

		this.textProperty().bindBidirectional(viewModel.quote);
	}
}
