package com.minkyu.yourdailyword.javafx.models.di;

import com.minkyu.yourdailyword.javafx.models.grpc.SyncQuotesServiceImpl;

import java.util.concurrent.atomic.AtomicReference;

public interface SyncQuotesServiceImplFactory {
	SyncQuotesServiceImpl create(AtomicReference<Runnable> afterSync);
}
