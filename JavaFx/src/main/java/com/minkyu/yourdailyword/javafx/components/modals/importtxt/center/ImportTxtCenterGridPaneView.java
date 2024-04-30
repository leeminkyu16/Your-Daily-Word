package com.minkyu.yourdailyword.javafx.components.modals.importtxt.center;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.*;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;
import com.minkyu.yourdailyword.javafx.models.ui.ITextFieldManager;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImportTxtCenterGridPaneView extends YdwGridPane {
	@Inject
	public ImportTxtCenterGridPaneView(
		ImportTxtCenterGridPaneViewModel viewModel,
		IInternationalizationModel internationalizationModel,
		ITextFieldManager textFieldManager
	) {
		super();
		this.connectViewModel(viewModel);

		double[] widthPercentages = {
			50,
			50
		};
		double[] heightPercentages = {
			25,
			25,
			25,
			25
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

		new YdwStyleBundle()
			.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.STANDARD_GRID_PANE)
			.apply(this);


		this.safeAdd(new YdwLabel(internationalizationModel.getString("month_header")), 0, 0, 1, 1);

		YdwTextField associatedMonthTextField = new YdwTextField()
			.applyStyleBundleAndReturnThis(
				new YdwStyleBundle()
					.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.ROUNDED_TEXT_BOX)
			);
		associatedMonthTextField.setTextFormatter(
			textFieldManager.getIntegerTextFormatter(
				1,
				13
			)
		);
		this.safeAdd(associatedMonthTextField, 1, 0, 1, 1);

		YdwButton selectFileButton = new YdwButton(
			internationalizationModel.getString("select_file")
		)
			.applyStyleBundleAndReturnThis(
				new YdwStyleBundle()
					.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.PRIMARY_BUTTON)
			)
			.setOnMouseClickedAndReturnThis((event) -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle(
				internationalizationModel.getString("select_txt_file_to_import_title")
			);
			fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter(internationalizationModel.getString("text_files"), "*.txt")
			);
			viewModel.selectedFile.set(
				fileChooser.showOpenDialog(this.getScene().getWindow())
			);
		});
		this.safeAdd(selectFileButton, 0, 1, 1, 1);

		Label selectedFileLabel = new Label(
			internationalizationModel.getString("no_file_selected")
		);
		viewModel.selectedFile.addAfterChange(
			(oldValue, newValue) -> {
				if (newValue == null) {
					selectedFileLabel.setText(
						internationalizationModel.getString("no_file_selected")
					);
				} else {
					selectedFileLabel.setText(newValue.getName());
				}
			}
		);
		this.safeAdd(selectedFileLabel, 1, 1, 1, 1);

		this.add(
			new Label(internationalizationModel.getString("close_on_import")),
			0,
			2,
			1,
			1
		);

		YdwCheckBox closeOnImportCheckbox = new YdwCheckBox()
			.applyStyleBundleAndReturnThis(
				new YdwStyleBundle().addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.PRIMARY_CHECKBOX)
			);
		this.safeAdd(closeOnImportCheckbox, 1, 2, 1, 1);

		YdwButton importQuotesButton = new YdwButton(internationalizationModel.getString("import_quotes"))
			.applyStyleBundleAndReturnThis(
				new YdwStyleBundle()
					.addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.PRIMARY_BUTTON)
			)
			.setOnMouseClickedAndReturnThis(
				(event) -> {
					viewModel.importQuotes(
						Integer.parseInt(associatedMonthTextField.getText()) - 1
					);

					if (closeOnImportCheckbox.isSelected()) {
						Stage stage = (Stage) this.getScene().getWindow();
						stage.close();
					}
				}
			);
		this.add(importQuotesButton, 0, 3, 2, 1);
	}
}
