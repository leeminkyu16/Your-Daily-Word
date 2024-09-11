package com.minkyu.yourdailyword.javafx.models.di;

import com.minkyu.yourdailyword.javafx.components.modals.qrcode.QrCodeStage;
import javafx.scene.image.Image;
import javafx.stage.Window;

public interface QrCodeStageFactory {
	QrCodeStage create(Image image, Window parent);
}
