package com.minkyu.yourdailyword.javafx.components.center.editindividual;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.minkyu.yourdailyword.javafx.models.di.CenterEditIndividualVBoxViewFactory;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwScrollPane;
import org.jetbrains.annotations.NotNull;

public class CenterEditIndividualScrollPaneView extends YdwScrollPane {

	@Inject
	public CenterEditIndividualScrollPaneView(
		@NotNull CenterEditIndividualVBoxViewFactory centerEditIndividualVBoxViewFactory,
		@Assisted YdwObservable<Integer> quoteIndex
	) {
		super(null);
		this.setFitToWidth(true);
		this.setFitToHeight(true);

		this.setSafeContent(centerEditIndividualVBoxViewFactory.create(quoteIndex));
	}
}
