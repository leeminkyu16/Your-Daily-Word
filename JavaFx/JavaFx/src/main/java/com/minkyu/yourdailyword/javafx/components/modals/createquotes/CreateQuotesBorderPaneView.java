package com.minkyu.yourdailyword.javafx.components.modals.createquotes;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.components.modals.createquotes.center.CreateQuotesCenterGridPaneView;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwBorderPane;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;

public class CreateQuotesBorderPaneView extends YdwBorderPane {
	@Inject
	public CreateQuotesBorderPaneView(
		CreateQuotesCenterGridPaneView gridPaneView
	) {
		this.safeSetCenter(gridPaneView);

		new YdwStyleBundle()
			.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.STANDARD_BORDER_PANE)
			.apply(this);
	}
}
