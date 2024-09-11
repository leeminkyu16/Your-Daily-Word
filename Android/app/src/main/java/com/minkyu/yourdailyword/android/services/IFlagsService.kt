package com.minkyu.yourdailyword.android.services

import java.util.concurrent.atomic.AtomicBoolean

interface IFlagsService {
	var debug: AtomicBoolean
	var generateDebugModel: AtomicBoolean
}