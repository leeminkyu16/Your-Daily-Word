package com.minkyu.yourdailyword.android.di

import com.minkyu.yourdailyword.common.calendar.GregorianCalendarModel
import com.minkyu.yourdailyword.common.calendar.HebrewCalendarModel
import com.minkyu.yourdailyword.common.calendar.IGregorianCalendarModel
import com.minkyu.yourdailyword.common.calendar.IHebrewCalendarModel
import com.minkyu.yourdailyword.common.calendar.ILunarCalendarModel
import com.minkyu.yourdailyword.common.calendar.LunarCalendarModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Suppress("unused")
@Module
@InstallIn(ViewModelComponent::class)
abstract class SimpleViewModelModule {
	@Binds
	abstract fun bindsIGregorianCalendar(gregorianCalendar: GregorianCalendarModel): IGregorianCalendarModel

	@Binds
	abstract fun bindILunarCalendar(lunarCalendar: LunarCalendarModel): ILunarCalendarModel

	@Binds
	abstract fun bindIHebrewCalendar(hebrewCalendar: HebrewCalendarModel): IHebrewCalendarModel
}
