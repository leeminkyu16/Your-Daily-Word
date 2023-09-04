package com.minkyu.yourdailyword.javafx.models.sharedstate;

import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;

public class CenterEditSharedState {
	public YdwObservable<Integer> currentPage = new YdwObservable<>(0);

	public YdwObservable<Integer> pageSize = new YdwObservable<>(5);

	public CenterEditSharedState() {
	}
}
