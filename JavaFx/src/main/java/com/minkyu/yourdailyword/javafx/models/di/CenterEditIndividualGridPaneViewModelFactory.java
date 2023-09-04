package com.minkyu.yourdailyword.javafx.models.di;

import com.minkyu.yourdailyword.javafx.components.center.editindividual.CenterEditIndividualGridPaneViewModel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;

public interface CenterEditIndividualGridPaneViewModelFactory {
    CenterEditIndividualGridPaneViewModel create(YdwObservable<Integer> quoteIndex);
}
