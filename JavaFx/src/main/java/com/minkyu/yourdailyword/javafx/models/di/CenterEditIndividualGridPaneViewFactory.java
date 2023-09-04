package com.minkyu.yourdailyword.javafx.models.di;

import com.minkyu.yourdailyword.javafx.components.center.editindividual.CenterEditIndividualGridPaneView;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;

public interface CenterEditIndividualGridPaneViewFactory {
    CenterEditIndividualGridPaneView create(YdwObservable<Integer> quoteIndex);
}
