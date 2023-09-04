package com.minkyu.yourdailyword.javafx.components.center.editindividual;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import com.minkyu.yourdailyword.javafx.models.di.CenterEditIndividualGridPaneViewModelFactory;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwWeakReference;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.*;
import com.minkyu.yourdailyword.javafx.models.sharedstate.ApplicationSharedState;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.Priority;

public class CenterEditIndividualGridPaneView extends YdwGridPane {
	CenterEditIndividualGridPaneViewModel viewModel;
	@Inject
	public CenterEditIndividualGridPaneView(
		CenterEditIndividualGridPaneViewModelFactory viewModelFactory,
		IInternationalizationModel internationalizationModel,
		@Assisted YdwObservable<Integer> quoteIndex
	) {
		super();
		this.viewModel = viewModelFactory.create(quoteIndex);
		this.connectViewModel(this.viewModel);

		YdwObservable<double[]> columnWeights = new YdwObservable<>(new double[]{
			3,
			7
		});
		YdwObservable<double[]> rowWeights = new YdwObservable<>(new double[]{
			1, // Quote Header
			2, // English Quote
			1, // Associated Date Header
			2, // Associated Month
			2, // Associated Day of Month
			2 // Buttons
			// 1, // Options Header
			// 1, // options subheader
			// 2, // option 1
			// 2, // option 2
			// 2, // option 3
		});

		setColumnConstraints(
			columnWeights,
			HPos.CENTER
		);
		setRowConstraints(
			rowWeights,
			VPos.CENTER,
			Priority.ALWAYS,
			true
		);

		YdwLabel quoteHeader = new YdwLabel(viewModel.quoteHeader);
		this.safeAdd(quoteHeader, 0, 0, 2, 1);

		YdwLabel englishLabel = new YdwLabel(viewModel.englishLabel);
		this.safeAdd(englishLabel, 0, 1, 1, 1);
		YdwTextField englishTextField = new YdwTextField(viewModel.englishValue);
		this.safeAdd(englishTextField, 1, 1, 1, 1);

		YdwLabel associatedDateHeader = new YdwLabel(viewModel.dateHeader);
		this.safeAdd(associatedDateHeader, 0, 2, 2, 1);

		YdwLabel monthLabel = new YdwLabel(viewModel.monthLabel);
		this.safeAdd(monthLabel, 0, 3, 1, 1);
		YdwNumberTextField monthTextField = new YdwNumberTextField(viewModel.monthValue);
		this.safeAdd(monthTextField, 1, 3, 1, 1);

		YdwLabel dayOfMonthLabel = new YdwLabel(viewModel.dayOfMonthLabel);
		this.safeAdd(dayOfMonthLabel, 0, 4, 1, 1);
		YdwNumberTextField dayOfMonthTextField = new YdwNumberTextField(viewModel.dayOfMonthValue);
		this.safeAdd(dayOfMonthTextField, 1, 4, 1, 1);

		YdwHBox buttonsContainer = new YdwHBox();
		buttonsContainer.setAlignment(Pos.CENTER);
		this.safeAdd(buttonsContainer, 0, 5, 2, 1);

		YdwWeakReference<CenterEditIndividualGridPaneViewModel> weakViewModelRef = new YdwWeakReference<>(viewModel);
		buttonsContainer.safeAddChildren(
			new YdwButton(internationalizationModel.getString("go_back"))
				.setOnMouseClickedWithThis(event -> {
					weakViewModelRef.doIfNotNull(weakViewModel -> {
						weakViewModel.applicationSharedState.currentCenterState.set(
							new ApplicationSharedState.CenterState.Edit()
						);
					});
				}),
			new YdwButton(internationalizationModel.getString("save_to_disk"))
				.setOnMouseClickedWithThis(event -> {
					weakViewModelRef.doIfNotNull(CenterEditIndividualGridPaneViewModel::saveModelToFile);
				})
		);
	}
}
