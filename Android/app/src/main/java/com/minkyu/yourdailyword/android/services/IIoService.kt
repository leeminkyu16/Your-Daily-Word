package com.minkyu.yourdailyword.android.services

import android.net.Uri
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream

interface IIoService {
	fun getAppSpecificFileOutputStream(filePathString: String): FileOutputStream
	fun getAppSpecificFileInputStream(filePathString: String): FileInputStream
	fun appSpecificFileExists(filePathString: String): Boolean
	fun writeStringToAppSpecificFile(filePathString: String, input: String)
	fun readStringFromAppSpecificFile(filePathString: String): String
	fun getFileName(uri: Uri?): String?
	fun getInputStream(uri: Uri?): InputStream?
}
