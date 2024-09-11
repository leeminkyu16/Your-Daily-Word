package com.minkyu.yourdailyword.javafx.components.center.edit;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.IQuotesManager;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwHBox;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.styledcomponent.YdwSecondaryButton;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class CenterEditPaginationBarHBoxView extends YdwHBox {
	final CenterEditPaginationBarHBoxViewModel viewModel;

	@Inject
	public CenterEditPaginationBarHBoxView(
		CenterEditPaginationBarHBoxViewModel viewModel,
		IQuotesManager quotesManager
	) {
		super();
		this.viewModel = viewModel;
		this.connectViewModel(viewModel);

		Runnable updateNumOfPages = () -> viewModel.setNumOfPages(
			Math.max(
				(
					quotesManager.getNumOfQuotes()
						+ CenterEditPaginationBarHBoxViewModel.NUM_OF_ENTRIES_PER_PAGE - 1
				)
					/ CenterEditPaginationBarHBoxViewModel.NUM_OF_ENTRIES_PER_PAGE,
				1
			)
		);

		viewModel.setNumOfPages(10);

		this.setAlignment(Pos.CENTER);

		updateButtons();
		quotesManager.addAfterQuotesUpdateRunnable(
			() -> {
				updateNumOfPages.run();
				updateButtons();
			}
		);
		updateNumOfPages.run();
	}

	void updateButtons() {
		this.getChildren().removeAll(this.getChildren());
		this.viewModel.onUpdateLabels.removeByKey("CenterEditPaginationBarHBox");

		for (int i = 0; i < viewModel.buttonsLabels.size(); i++) {
			String buttonLabel = viewModel.buttonsLabels.get(i);
			Region spacer = new Region();
			HBox.setHgrow(spacer, Priority.ALWAYS);
			this.getChildren().add(spacer);

			YdwSecondaryButton button = new YdwSecondaryButton(buttonLabel);
			switch (buttonLabel) {
				case "←" -> button.setOnMouseClicked(
					event -> viewModel.decrementIndex()
				);
				case "→" -> button.setOnMouseClicked(
					event -> viewModel.incrementIndex()
				);
				default -> {
					// Copy the value of i for Runnable
					final int iCopy = i;

					button.setOnMouseClicked(
						event -> viewModel.setIndex(viewModel.getIndex(iCopy - 1))
					);

					viewModel.onUpdateLabels.addToArrayList(
						"CenterEditPaginationBarHBox",
						() -> {
							if (iCopy < viewModel.buttonsLabels.size()) {
								button.setText(viewModel.buttonsLabels.get(iCopy));
							}
						}
					);
				}
			}
			this.safeAddChildren(button);
		}
		if (!viewModel.buttonsLabels.isEmpty()) {
			Region spacer = new Region();
			HBox.setHgrow(spacer, Priority.ALWAYS);
			this.getChildren().add(spacer);
		}
	}
}
