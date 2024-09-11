package com.minkyu.yourdailyword.javafx.models.ui;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;

public class JavaFxUtils {
	public static Image stringToQrCodeImage(String string, int width, int height) throws WriterException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();

		BitMatrix bitMatrix = qrCodeWriter.encode(
			string,
			com.google.zxing.BarcodeFormat.QR_CODE,
			width,
			height
		);
		BufferedImage bufferedImage = new BufferedImage(
			width,
			height,
			BufferedImage.TYPE_INT_RGB
		);

		for (int i = 0; i < bitMatrix.getHeight(); i++) {
			for (int j = 0; j < bitMatrix.getWidth(); j++) {
				bufferedImage.setRGB(
					j,
					i,
					bitMatrix.get(j, i) ? 0xFF000000 : 0xFFFFFFFF
				);
			}
		}

		return SwingFXUtils.toFXImage(bufferedImage, null);
	}
}
