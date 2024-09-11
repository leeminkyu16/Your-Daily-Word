package com.minkyu.yourdailyword.javafx.models.di;

import com.minkyu.yourdailyword.javafx.components.center.editindividual.CenterEditIndividualVBoxView;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;

public interface CenterEditIndividualVBoxViewFactory {
    CenterEditIndividualVBoxView create(YdwObservable<Integer> quoteIndex);
}
