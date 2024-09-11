package com.minkyu.yourdailyword.javafx.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwWeakReference;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SettingsManager implements ISettingsManager {
	private static final Logger logger = Logger.getLogger(SettingsManager.class.getName());

	@Inject
	public SettingsManager(
		IDesktopIoModel desktopIoModel
	) {
		YdwWeakReference<IDesktopIoModel> desktopIoModelWeakRef = new YdwWeakReference<>(desktopIoModel);

		try {
			SettingsRecord settingsRecord = new ObjectMapper().readValue(
				desktopIoModel.getLocalApplicationDataDirectoryFileInputStream(SETTINGS_FILE_NAME),
				SettingsRecord.class
			);
			darkMode.set(settingsRecord.darkMode());
		} catch (IOException e) {
			logger.log(Level.WARNING, "Failed to read settings file", e);
		}

		this.darkMode.addAfterChange(
			(before, after) -> {
				new Thread(() -> {
					desktopIoModelWeakRef.doIfNotNull(
						ioModel -> {
							try {
								new ObjectMapper().writeValue(
									ioModel.getLocalApplicationDataDirectoryFileOutputStream(SETTINGS_FILE_NAME),
									new SettingsRecord(after)
								);
							}
							catch (IOException e) {
								throw new RuntimeException(e);
							}
						}
					);
				}).start();
			}
		);
	}

	private final YdwObservable<Boolean> darkMode = new YdwObservable<>(false);

	@Override
	public YdwObservable<Boolean> getDarkMode() {
		return darkMode;
	}

	public static String SETTINGS_FILE_NAME = "settings.json";
}
