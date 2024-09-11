package com.minkyu.yourdailyword.javafx.components.modals.qrcode;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.minkyu.yourdailyword.javafx.models.di.QrCodeSceneFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class QrCodeStage extends Stage {
	@Inject
	public QrCodeStage(
		QrCodeSceneFactory qrcodeSceneFactory,
		@Assisted Image image,
		@Assisted Window parent
	) {
		super();

		this.setTitle("QR Code");
		this.setScene(qrcodeSceneFactory.create(image));
		this.initModality(Modality.WINDOW_MODAL);
		this.initOwner(parent);
		this.setResizable(false);
		this.centerOnScreen();
	}
}
