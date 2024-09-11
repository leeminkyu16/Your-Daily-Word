package com.minkyu.yourdailyword.javafx.models.di;

import com.minkyu.yourdailyword.javafx.components.modals.qrcode.QrCodeScene;
import javafx.scene.image.Image;

public interface QrCodeSceneFactory {
	QrCodeScene create(Image image);
}
