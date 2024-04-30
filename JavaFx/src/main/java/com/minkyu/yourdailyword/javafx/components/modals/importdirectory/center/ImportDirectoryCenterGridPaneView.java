package com.minkyu.yourdailyword.javafx.components.modals.importdirectory.center;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwDynamicColumn;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.YdwLabel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.style.YdwStyleBundle;
import com.minkyu.yourdailyword.javafx.models.infrastructure.javafx.styledcomponent.YdwPrimaryButton;
import javafx.stage.DirectoryChooser;

public class ImportDirectoryCenterGridPaneView extends YdwDynamicColumn {
    private final ImportDirectoryCenterGridPaneViewModel viewModel;
    private final IInternationalizationModel internationalizationModel;

    @Inject
    public ImportDirectoryCenterGridPaneView(
        IInternationalizationModel internationalizationModel,
        ImportDirectoryCenterGridPaneViewModel viewModel
    ) {
        super();
        this.viewModel = viewModel;
        this.internationalizationModel = internationalizationModel;
        this.connectViewModel(viewModel);

        new YdwStyleBundle()
            .addStyleClassAndReturnThis(YdwStyleBundle.StyleClasses.STANDARD_GRID_PANE)
            .setPadding(8, 8, 8, 8)
            .setVGap(8)
            .apply(this);

        this.dynamicColumnWeights.set(new double[]{1, 1});
        this.render();
    }

    @Override
    public void renderBody() {
        this.renderAddRow(
            new YdwPrimaryButton(
                internationalizationModel.getString("select_directory")
            ).setOnMouseClickedAndReturnThis((event) -> {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle(
                    internationalizationModel.getString("select_directory_to_import")
                );
                viewModel.setSelectedDirectory(
                    directoryChooser.showDialog(this.getScene().getWindow())
                );
            }),
            new YdwLabel(this.viewModel.selectedDirectoryLabel),
            1
        );
        this.renderAddRow(
            new YdwPrimaryButton(internationalizationModel.getString("import_quotes")).setOnMouseClickedAndReturnThis(event -> {
                viewModel.importQuotes();
            }),
            1
        );
    }
}
