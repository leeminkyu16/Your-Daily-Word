package com.minkyu.yourdailyword.javafx.components.main;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwScene;

public class MainScene extends YdwScene {
	@Inject
	public MainScene(
		MainBorderPane mainBorderPane
	) {
		super(
			mainBorderPane,
			500,
			350
		);

		this.getStylesheets().add("com/minkyu/yourdailyword/javafx/css/main.css");
	}
}
