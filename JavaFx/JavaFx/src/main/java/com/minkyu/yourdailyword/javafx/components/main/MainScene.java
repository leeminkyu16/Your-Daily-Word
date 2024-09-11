package com.minkyu.yourdailyword.javafx.components.main;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.ISettingsManager;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwScene;

public class MainScene extends YdwScene {
	@Inject
	public MainScene(
		MainBorderPane mainBorderPane,
		ISettingsManager settingsManager
	) {
		super(
			mainBorderPane,
			500,
			350
		);

		this.getStylesheets().add("com/minkyu/yourdailyword/javafx/css/main.css");

		this.addDarkModeAwareStyleSheets(
			settingsManager.getDarkMode(),
			"com/minkyu/yourdailyword/javafx/css/light-mode-colors.css",
			"com/minkyu/yourdailyword/javafx/css/dark-mode-colors.css"
		);
	}
}
