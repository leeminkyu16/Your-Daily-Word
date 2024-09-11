package com.minkyu.yourdailyword.javafx.models.di;

import com.minkyu.yourdailyword.javafx.components.center.editindividual.CenterEditIndividualVBoxViewModel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;

public interface CenterEditIndividualGridPaneViewModelFactory {
    CenterEditIndividualVBoxViewModel create(YdwObservable<Integer> quoteIndex);
}
