package com.minkyu.yourdailyword.javafx.components.modals.qrcode;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.minkyu.yourdailyword.javafx.models.ISettingsManager;
import com.minkyu.yourdailyword.javafx.models.di.QrCodeBorderPaneViewFactory;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwScene;
import javafx.scene.image.Image;

public class QrCodeScene extends YdwScene {
	@Inject
	public QrCodeScene(
		ISettingsManager settingsManager,
		QrCodeBorderPaneViewFactory qrcodeBorderPaneViewFactory,
		@Assisted Image image
	) {
		super(qrcodeBorderPaneViewFactory.create(image));

		this.getStylesheets().add("com/minkyu/yourdailyword/javafx/css/main.css");

		this.addDarkModeAwareStyleSheets(
			settingsManager.getDarkMode(),
			"com/minkyu/yourdailyword/javafx/css/light-mode-colors.css",
			"com/minkyu/yourdailyword/javafx/css/dark-mode-colors.css"
		);
	}
}
