package com.minkyu.yourdailyword.javafx.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public interface IDesktopIoModel {
	void writeToLocalApplicationDataDirectory(String fileName, String fileContent) throws IOException;

	String readFromLocalApplicationDataDirectory(String fileName) throws IOException;

	void writeToFile(Path filePath, String fileContent) throws IOException;

	String readFromFile(Path filePath) throws IOException;

	String getLocalApplicationDataDirectory(String fileName);

	FileInputStream getLocalApplicationDataDirectoryFileInputStream(String fileName) throws FileNotFoundException;

	FileOutputStream getLocalApplicationDataDirectoryFileOutputStream(String fileName) throws FileNotFoundException;
}
