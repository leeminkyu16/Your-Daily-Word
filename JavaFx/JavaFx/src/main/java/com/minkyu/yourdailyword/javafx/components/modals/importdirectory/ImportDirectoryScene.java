package com.minkyu.yourdailyword.javafx.components.modals.importdirectory;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.ISettingsManager;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwScene;

public class ImportDirectoryScene extends YdwScene {
	@Inject
	public ImportDirectoryScene(
		ImportDirectoryBorderPaneView importTxtBorderPaneView,
		ISettingsManager settingsManager
	) {
		super(importTxtBorderPaneView);

		this.getStylesheets().add("com/minkyu/yourdailyword/javafx/css/main.css");

		this.addDarkModeAwareStyleSheets(
			settingsManager.getDarkMode(),
			"com/minkyu/yourdailyword/javafx/css/light-mode-colors.css",
			"com/minkyu/yourdailyword/javafx/css/dark-mode-colors.css"
		);
	}
}
