package com.minkyu.yourdailyword.android.di

import android.content.Context
import com.minkyu.yourdailyword.android.models.IQuotesManager
import com.minkyu.yourdailyword.android.models.QuotesManager
import com.minkyu.yourdailyword.android.services.IIoService
import com.minkyu.yourdailyword.android.services.INavigationService
import com.minkyu.yourdailyword.android.services.ISettingsService
import com.minkyu.yourdailyword.android.services.IoService
import com.minkyu.yourdailyword.android.services.NavigationService
import com.minkyu.yourdailyword.android.services.SettingsService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {
	@Singleton
	@Provides
	fun providesIQuotesManager(ioService: IIoService): IQuotesManager {
		return QuotesManager(
			ioService = ioService,
		)
	}

	@Reusable
	@Provides
	fun providesIAndroidIoModel(@ApplicationContext applicationContext: Context): IIoService {
		return IoService(applicationContext)
	}

	@Singleton
	@Provides
	fun providesNavigationService(@ApplicationContext applicationContext: Context): INavigationService {
		return NavigationService(
			context = applicationContext,
		)
	}

	@Singleton
	@Provides
	fun providesSettingsService(
		@ApplicationContext applicationContext: Context,
		ioService: IIoService,
	): ISettingsService {
		return SettingsService(
			context = applicationContext,
			ioService = ioService,
		)
	}
}
