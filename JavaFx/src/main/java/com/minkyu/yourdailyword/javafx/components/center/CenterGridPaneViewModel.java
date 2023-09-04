package com.minkyu.yourdailyword.javafx.components.center;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import com.minkyu.yourdailyword.javafx.models.sharedstate.ApplicationSharedState;
import com.minkyu.yourdailyword.javafx.models.ISharedStateManager;

public class CenterGridPaneViewModel extends YdwViewModel {
	public ApplicationSharedState applicationSharedState;

	@Inject
	public CenterGridPaneViewModel(ISharedStateManager sharedStateManager) {
		applicationSharedState = sharedStateManager.getAppliationSharedState();
	}
}
