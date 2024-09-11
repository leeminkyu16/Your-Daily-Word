package com.minkyu.yourdailyword.javafx.models;

import com.minkyu.yourdailyword.javafx.models.sharedstate.ApplicationSharedState;
import com.minkyu.yourdailyword.javafx.models.sharedstate.CenterEditSharedState;
import com.minkyu.yourdailyword.javafx.models.sharedstate.ImportTxtSharedState;

public interface ISharedStateManager {
	ApplicationSharedState getAppliationSharedState();

	CenterEditSharedState getCenterEditSharedState();

	ImportTxtSharedState getImportTxtSharedState();
}
