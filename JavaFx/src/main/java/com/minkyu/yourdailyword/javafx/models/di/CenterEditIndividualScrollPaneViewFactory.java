package com.minkyu.yourdailyword.javafx.models.di;

import com.minkyu.yourdailyword.javafx.components.center.editindividual.CenterEditIndividualScrollPaneView;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;

public interface CenterEditIndividualScrollPaneViewFactory {
	CenterEditIndividualScrollPaneView create(YdwObservable<Integer> quoteIndex);
}
