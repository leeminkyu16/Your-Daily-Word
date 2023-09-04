package com.minkyu.yourdailyword.javafx.components.center;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.minkyu.yourdailyword.javafx.components.center.edit.CenterEditQuotesGridPaneView;
import com.minkyu.yourdailyword.javafx.components.center.editindividual.CenterEditIndividualGridPaneView;
import com.minkyu.yourdailyword.javafx.components.center.view.CenterViewGridPaneView;
import com.minkyu.yourdailyword.javafx.models.di.CenterEditIndividualGridPaneViewFactory;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwGridPane;
import com.minkyu.yourdailyword.javafx.models.sharedstate.ApplicationSharedState;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Priority;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.util.function.Consumer;

public class CenterGridPaneView extends YdwGridPane {
	private final Injector injector;
	private final CenterEditIndividualGridPaneViewFactory centerEditIndividualGridPaneViewFactory;
	@Nullable
	private CenterViewGridPaneView centerViewGridPaneView;
	@Nullable
	private CenterEditQuotesGridPaneView centerEditQuotesGridPaneView;
	@Nullable
	private CenterEditIndividualGridPaneView centerEditIndividualGridPaneView;

	@Inject
	public CenterGridPaneView(
		Injector injector,
		CenterEditIndividualGridPaneViewFactory centerEditIndividualGridPaneViewFactory,
		CenterGridPaneViewModel viewModel
	) {
		super();
		this.connectViewModel(viewModel);
		this.injector = injector;
		this.centerEditIndividualGridPaneViewFactory = centerEditIndividualGridPaneViewFactory;

		double[] widthPercentages = {
			100,
		};
		double[] heightPercentages = {
			100,
		};

		setColumnConstraints(
			widthPercentages,
			HPos.CENTER
		);
		setRowConstraints(
			heightPercentages,
			VPos.CENTER,
			Priority.ALWAYS,
			true
		);

		WeakReference<CenterGridPaneView> weakThisRef = new WeakReference<>(this);

		Consumer<ApplicationSharedState.CenterState> updateCenterView = (newCenterState) -> {
			CenterGridPaneView weakThis = weakThisRef.get();
			if (weakThis != null) {
				if (weakThis.centerViewGridPaneView != null) {
					weakThis.centerViewGridPaneView.beforeDestroy();
				}
				weakThis.getChildren().remove(weakThis.centerViewGridPaneView);
				weakThis.centerViewGridPaneView = null;

				if (weakThis.centerEditQuotesGridPaneView != null) {
					weakThis.centerEditQuotesGridPaneView.beforeDestroy();
				}
				weakThis.getChildren().remove(weakThis.centerEditQuotesGridPaneView);
				weakThis.centerEditQuotesGridPaneView = null;

				if (weakThis.centerEditIndividualGridPaneView != null) {
					weakThis.centerEditIndividualGridPaneView.beforeDestroy();
				}
				weakThis.getChildren().remove(weakThis.centerEditIndividualGridPaneView);
				weakThis.centerEditIndividualGridPaneView = null;


				if (newCenterState instanceof ApplicationSharedState.CenterState.View) {
					weakThis.centerViewGridPaneView = weakThis.injector.getInstance(CenterViewGridPaneView.class);
					weakThis.add(weakThis.centerViewGridPaneView, 0, 0, 1, 1);
				} else if (newCenterState instanceof ApplicationSharedState.CenterState.Edit) {
					weakThis.centerEditQuotesGridPaneView = weakThis.injector.getInstance(CenterEditQuotesGridPaneView.class);
					weakThis.add(weakThis.centerEditQuotesGridPaneView, 0, 0, 1, 1);
				} else if (newCenterState instanceof ApplicationSharedState.CenterState.EditIndividual) {
					weakThis.centerEditIndividualGridPaneView = weakThis.centerEditIndividualGridPaneViewFactory.create(
						((ApplicationSharedState.CenterState.EditIndividual) newCenterState).quoteId()
					);
					weakThis.add(weakThis.centerEditIndividualGridPaneView, 0, 0, 1, 1);
				}
			}
		};

		this.addBeforeDestroyRunnable(
			viewModel.applicationSharedState.currentCenterState.addAfterChange((oldValue, newValue) -> {
				updateCenterView.accept(newValue);
			})
		);

		updateCenterView.accept(viewModel.applicationSharedState.currentCenterState.get());
	}
}
