package com.minkyu.yourdailyword.android.di

import com.minkyu.yourdailyword.android.services.ISnackbarService
import com.minkyu.yourdailyword.android.services.SnackbarService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SimpleSingletonModule {
	@Singleton
	@Binds
	abstract fun bindsSnackbarService(impl: SnackbarService): ISnackbarService
}
