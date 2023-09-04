package com.minkyu.yourdailyword.android.services

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

class IoService @Inject constructor(
	private val context: Context,
): IIoService {
	override fun appSpecificFileExists(
		filePathString: String,
	): Boolean {
		val file = File(context.filesDir, filePathString)
		return file.exists()
	}

	override fun getAppSpecificFileInputStream(
		filePathString: String,
	): FileInputStream {
		val file = File(context.filesDir, filePathString)
		return file.inputStream()
	}

	override fun getAppSpecificFileOutputStream(
		filePathString: String,
	): FileOutputStream {
		val file = File(context.filesDir, filePathString)
		return file.outputStream()
	}

	override fun writeStringToAppSpecificFile(filePathString: String, input: String) {
		val targetFile = File(context.filesDir, filePathString)
		targetFile.bufferedWriter().use {
			it.write(input)
		}
	}

	override fun readStringFromAppSpecificFile(filePathString: String): String {
		val targetFile = File(context.filesDir, filePathString)
		var outputString: String
		targetFile.bufferedReader().use {
			outputString = it.readLines().joinToString("\n")
		}
		return outputString
	}

	override fun getFileName(uri: Uri?): String? {
		return uri?.let {
			var fileName: String? = null

			context.contentResolver.query(
				uri,
				null,
				null,
				null,
				null
			)?.use { cursor: Cursor ->
				if (cursor.moveToFirst()) {
					val displayNameIndex: Int = cursor.getColumnIndex(
						OpenableColumns.DISPLAY_NAME
					)
					if (displayNameIndex > -1) {
						fileName = cursor.getString(displayNameIndex)
					}
				}
			}

			fileName
		}
	}

	override fun getInputStream(uri: Uri?): InputStream? {
		return runCatching {
			uri?.let {
				context.contentResolver.openInputStream(uri)
			}
		}.getOrNull()
	}
}
