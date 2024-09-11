package com.minkyu.yourdailyword.javafx.components.center.view;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwLabel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwColorConstants;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;

public class CenterViewDateLabelView extends YdwLabel {
	@Inject
	public CenterViewDateLabelView(CenterViewDateLabelViewModel viewModel) {
		super();
		this.connectViewModel(viewModel);
		this.setStyle(
			new YdwStyleBundle()
				.setBackgroundColor(YdwColorConstants.primaryBackgroundColor)
				.setTextFill(YdwColorConstants.secondaryTextColor)
				.buildStyleString()
		);

		this.textProperty().bindBidirectional(viewModel.dateString);
	}
}
