package com.minkyu.yourdailyword.android.services

import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class FlagsService @Inject constructor(): IFlagsService {
	override var debug: AtomicBoolean = AtomicBoolean(true)

	override var generateDebugModel: AtomicBoolean = AtomicBoolean(false)
}
