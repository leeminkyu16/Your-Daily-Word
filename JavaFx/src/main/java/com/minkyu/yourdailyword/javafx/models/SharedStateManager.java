package com.minkyu.yourdailyword.javafx.models;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwWeakSingleton;
import com.minkyu.yourdailyword.javafx.models.sharedstate.ApplicationSharedState;
import com.minkyu.yourdailyword.javafx.models.sharedstate.CenterEditSharedState;
import com.minkyu.yourdailyword.javafx.models.sharedstate.ImportTxtSharedState;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedStateManager implements ISharedStateManager {
	private final YdwWeakSingleton<ApplicationSharedState> applicationSharedState = new YdwWeakSingleton<>(
		ApplicationSharedState::new
	);

	private final YdwWeakSingleton<CenterEditSharedState> centerEditSharedState = new YdwWeakSingleton<>(
		CenterEditSharedState::new
	);

	private final YdwWeakSingleton<ImportTxtSharedState> importTxtSharedState = new YdwWeakSingleton<>(
		ImportTxtSharedState::new
	);

	@Inject
	public SharedStateManager() {
	}

	@Override
	public ApplicationSharedState getAppliationSharedState() {
		return applicationSharedState.get();
	}

	@Override
	public CenterEditSharedState getCenterEditSharedState() {
		return centerEditSharedState.get();
	}

	@Override
	public ImportTxtSharedState getImportTxtSharedState() {
		return importTxtSharedState.get();
	}
}
