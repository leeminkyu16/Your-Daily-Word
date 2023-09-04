package com.minkyu.yourdailyword.android

import android.app.Application
import com.minkyu.yourdailyword.android.services.ISettingsService
import com.minkyu.yourdailyword.android.services.ISnackbarService
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application() {
	@Inject
	lateinit var settingsService: ISettingsService
	@Inject
	lateinit var snackbarService: ISnackbarService

	private val job: CompletableJob = SupervisorJob()
	private val ioCoroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + job)
	private val defaultCoroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default + job)

	override fun onCreate() {
		super.onCreate()
		settingsService.subscribe(ioCoroutineScope = ioCoroutineScope)
		snackbarService.setCoroutineScope(coroutineScope = defaultCoroutineScope)
	}

	/*
	 * Only runs on emulated devices
	 */
	override fun onTerminate() {
		job.cancel()
		super.onTerminate()
	}
}
