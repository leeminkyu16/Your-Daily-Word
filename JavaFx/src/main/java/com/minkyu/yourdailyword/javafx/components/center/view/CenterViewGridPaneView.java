package com.minkyu.yourdailyword.javafx.components.center.view;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwGridPane;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Priority;

public class CenterViewGridPaneView extends YdwGridPane {
	@Inject
	public CenterViewGridPaneView(
		CenterViewDateLabelView centerViewDateLabelView,
		CenterViewQuoteTextAreaView centerViewQuoteTextAreaView,
		CenterViewCopyQuoteButtonView centerViewQuoteCopyButtonView
	) {
		super();
		double[] widthPercentages = {
			100,
		};
		double[] heightPercentages = {
			10,
			80,
			10
		};

		this.setColumnConstraints(
			widthPercentages,
			HPos.CENTER
		);
		this.setRowConstraints(
			heightPercentages,
			VPos.CENTER,
			Priority.ALWAYS,
			true
		);

		this.safeAdd(centerViewDateLabelView, 0, 0, 1, 1);
		this.safeAdd(centerViewQuoteTextAreaView, 0, 1, 1, 1);
		this.safeAdd(centerViewQuoteCopyButtonView, 0, 2, 1, 1);
	}
}
