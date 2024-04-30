package com.minkyu.yourdailyword.javafx.components.modals.createquotes;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwScene;

public class CreateQuotesScene extends YdwScene {
	@Inject
	public CreateQuotesScene(
		CreateQuotesBorderPaneView borderPaneView
	) {
		super(borderPaneView);
		this.getStylesheets().add("com/minkyu/yourdailyword/javafx/css/main.css");
	}
}
