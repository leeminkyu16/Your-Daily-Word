package com.minkyu.yourdailyword.javafx.components.center.setting;

import com.google.inject.Inject;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.zxing.WriterException;
import com.minkyu.yourdailyword.common.protos.PairingInfo;
import com.minkyu.yourdailyword.javafx.components.modals.qrcode.QrCodeStage;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import com.minkyu.yourdailyword.javafx.models.ISettingsManager;
import com.minkyu.yourdailyword.javafx.models.ISharedStateManager;
import com.minkyu.yourdailyword.javafx.models.di.QrCodeStageFactory;
import com.minkyu.yourdailyword.javafx.models.grpc.SyncQuotesServiceImpl;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;

import com.google.protobuf.util.JsonFormat;

import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwWeakReference;
import com.minkyu.yourdailyword.javafx.models.network.NetworkUtils;
import com.minkyu.yourdailyword.javafx.models.ui.JavaFxUtils;
import io.grpc.*;
import javafx.scene.image.Image;
import javafx.stage.Window;

import java.io.IOException;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CenterSettingGridPaneViewModel extends YdwViewModel {
	private static final Logger logger = Logger.getLogger(CenterSettingGridPaneViewModel.class.getName());

	private final ISettingsManager settingsManager;
	private final QrCodeStageFactory qrcodeStageFactory;
	private final SyncQuotesServiceImpl syncQuotesServiceImpl;
	private final ISharedStateManager sharedStateManager;
	private final IInternationalizationModel internationalizationModel;

	@Inject
	public CenterSettingGridPaneViewModel(
		ISettingsManager settingsManager,
		QrCodeStageFactory qrcodeStageFactory,
		SyncQuotesServiceImpl syncQuotesServiceImpl,
		ISharedStateManager sharedStateManager,
		IInternationalizationModel internationalizationModel
	) {
		super();

		this.settingsManager = settingsManager;
		this.qrcodeStageFactory = qrcodeStageFactory;
		this.syncQuotesServiceImpl = syncQuotesServiceImpl;
		this.sharedStateManager = sharedStateManager;
		this.internationalizationModel = internationalizationModel;
	}

	public void toggleDarkMode() {
		settingsManager.getDarkMode().set(
			!settingsManager.getDarkMode().get()
		);
	}

	public void syncQuotesWithQrCode(Window window) {
		String hostAddress = null;
		try {
			hostAddress = NetworkUtils.getHostAddress(true);
		} catch (SocketException e) {
			sharedStateManager.getAppliationSharedState().bottomMessage.set(
				internationalizationModel.getString("failed_to_get_host_address_message")
			);

			logger.log(Level.WARNING, "Failed to get host address due to socket exception", e);

			return;
		}

		Image image;
		try {
			image = JavaFxUtils.stringToQrCodeImage(
					JsonFormat.printer().print(
						PairingInfo.newBuilder()
							.setHostAddress(hostAddress)
							.setPort(PORT_NUMBER)
							.build()
					),
					QR_CODE_WIDTH,
					QR_CODE_HEIGHT
				);
		} catch (WriterException e) {
			sharedStateManager.getAppliationSharedState().bottomMessage.set(
				internationalizationModel.getString("failed_to_create_qr_code_image_message")
			);

			logger.log(Level.WARNING, "Failed to create QR code image due to writer exception", e);
			return;
		} catch (InvalidProtocolBufferException e) {
			sharedStateManager.getAppliationSharedState().bottomMessage.set(
				internationalizationModel.getString("failed_to_create_qr_code_image_message")
			);

			logger.log(Level.WARNING, "Failed to create QR code image due to invalid protocol buffer", e);
			return;
		}

		QrCodeStage qrCodeStage = qrcodeStageFactory.create(image, window);
			qrCodeStage.show();

			Server server = Grpc.newServerBuilderForPort(
					PORT_NUMBER,
					InsecureServerCredentials.create()
				)
				.addService(syncQuotesServiceImpl)
				.build();

			YdwWeakReference<Server> serverWeakReference = new YdwWeakReference<>(server);

			qrCodeStage.onCloseRequestProperty().addListener(
				(observable, oldValue, newValue) -> {
					serverWeakReference.doIfNotNull((weakServer) -> {
						if (!weakServer.isShutdown() || !weakServer.isTerminated()) {
							weakServer.shutdown();
						}
					});
				}
			);
		try {
			server.start();
		} catch (IOException e) {
			sharedStateManager.getAppliationSharedState().bottomMessage.set(
				internationalizationModel.getString("failed_to_start_server_message")
			);

			logger.log(Level.WARNING, "Failed to start server due to IO exception", e);

			return;
		}
	}

	static final int QR_CODE_WIDTH = 512;
	static final int QR_CODE_HEIGHT = 512;

	static final int PORT_NUMBER = 65500;
}
