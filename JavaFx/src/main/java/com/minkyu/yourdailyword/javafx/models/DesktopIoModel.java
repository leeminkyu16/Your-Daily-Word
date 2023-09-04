package com.minkyu.yourdailyword.javafx.models;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class DesktopIoModel implements IDesktopIoModel {
	private static final String APP_NAME = "Your Daily Word";

	@Override
	public void writeToLocalApplicationDataDirectory(String fileName, String fileContent) throws IOException {
		writeToFile(Path.of(getLocalApplicationDataDirectoryBase(), APP_NAME, fileName), fileContent);
	}

	@Override
	public String readFromLocalApplicationDataDirectory(String fileName) throws IOException {
		return readFromFile(Path.of(getLocalApplicationDataDirectoryBase(), APP_NAME, fileName));
	}

	@Override
	public void writeToFile(Path filePath, String fileContent) throws IOException {
		//noinspection ResultOfMethodCallIgnored
		filePath.getParent().toFile().mkdirs();

		Files.writeString(
			filePath,
			fileContent,
			StandardOpenOption.CREATE
		);
	}

	@Override
	public String readFromFile(Path filePath) throws IOException {
		return Files.readString(
			filePath
		);
	}

	@Override
	public String getLocalApplicationDataDirectory(String fileName) {
		return String.format(
			"%s/%s/%s",
			getLocalApplicationDataDirectoryBase(),
			APP_NAME,
			fileName
		);
	}

	@Override
	public FileInputStream getLocalApplicationDataDirectoryFileInputStream(String fileName) throws FileNotFoundException {
		return new FileInputStream(getLocalApplicationDataDirectory(fileName));
	}

	@Override
	public FileOutputStream getLocalApplicationDataDirectoryFileOutputStream(String fileName) throws FileNotFoundException {
		File file = new File(getLocalApplicationDataDirectory(fileName));
		//noinspection ResultOfMethodCallIgnored
		file.getParentFile().mkdirs();

		return new FileOutputStream(file);
	}

	private String getLocalApplicationDataDirectoryBase() {
		String osName = (System.getProperty("os.name")).toLowerCase();

		if (osName.contains("win")) {
			return System.getenv("LOCALAPPDATA");
		} else if (osName.contains("mac")) {
			return getHome() + "/Library/Application Support";
		} else {
			return getHome() + "/.local/share";
		}
	}

	private String getHome() {
		return System.getProperty("user.home");
	}
}
