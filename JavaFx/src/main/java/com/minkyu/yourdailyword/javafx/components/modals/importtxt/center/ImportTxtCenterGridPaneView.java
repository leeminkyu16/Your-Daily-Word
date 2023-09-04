package com.minkyu.yourdailyword.javafx.components.modals.importtxt.center;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwGridPane;
import com.minkyu.yourdailyword.javafx.models.ui.ITextFieldManager;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

        this.add(new Label(internationalizationModel.getString("month_header")), 0, 0 ,1 ,1);

        TextField associatedMonthTextField = new TextField();
        associatedMonthTextField.setTextFormatter(
            textFieldManager.getIntegerTextFormatter(
                1,
                13
            )
        );
        this.add(associatedMonthTextField, 1, 0, 1, 1);

        Button selectFileButton = new Button(
            internationalizationModel.getString("select_file")
        );
        selectFileButton.setOnMouseClicked((event) -> {
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
        this.add(selectFileButton, 0, 1, 1, 1);

        Label selectedFileLabel = new Label(
            internationalizationModel.getString("no_file_selected")
        );
        viewModel.selectedFile.addAfterChange(
            (oldValue, newValue) -> {
                if (newValue == null) {
                    selectedFileLabel.setText(
                        internationalizationModel.getString("no_file_selected")
                    );
                }
                else {
                    selectedFileLabel.setText(newValue.getName());
                }
            }
        );
        this.add(selectedFileLabel, 1, 1, 1, 1);

        this.add(
            new Label(internationalizationModel.getString("close_on_import")),
            0,
            2,
            1,
            1
        );

        CheckBox closeOnImportCheckbox = new CheckBox();
        this.add(closeOnImportCheckbox, 1, 2, 1, 1);

        Button importQuotesButton = new Button(internationalizationModel.getString("import_quotes"));
        importQuotesButton.setOnMouseClicked(
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
