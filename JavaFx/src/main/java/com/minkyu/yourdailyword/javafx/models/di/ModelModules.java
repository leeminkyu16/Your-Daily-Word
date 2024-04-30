package com.minkyu.yourdailyword.javafx.models.di;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.minkyu.yourdailyword.common.calendar.*;
import com.minkyu.yourdailyword.javafx.components.center.editindividual.CenterEditIndividualScrollPaneView;
import com.minkyu.yourdailyword.javafx.components.center.editindividual.CenterEditIndividualVBoxView;
import com.minkyu.yourdailyword.javafx.components.center.editindividual.CenterEditIndividualVBoxViewModel;
import com.minkyu.yourdailyword.javafx.components.center.edit.CenterEditQuotesRowGridPaneView;
import com.minkyu.yourdailyword.javafx.components.center.edit.CenterEditQuotesRowGridPaneViewModel;
import com.minkyu.yourdailyword.javafx.components.modals.createquotes.CreateQuotesStage;
import com.minkyu.yourdailyword.javafx.components.modals.importdirectory.ImportDirectoryStage;
import com.minkyu.yourdailyword.javafx.components.modals.importtxt.ImportTxtStage;
import com.minkyu.yourdailyword.javafx.models.*;
import com.minkyu.yourdailyword.javafx.models.ui.ITextFieldManager;
import com.minkyu.yourdailyword.javafx.models.ui.TextFieldManager;

public class ModelModules extends AbstractModule {
    @Override
    protected void configure() {
        bind(IInternationalizationModel.class).to(InternationalizationModel.class);
        bind(IDesktopIoModel.class).to(DesktopIoModel.class);
        bind(IQuotesManager.class).to(QuotesManager.class).in(Singleton.class);
        bind(ITextFieldManager.class).to(TextFieldManager.class);
        bind(IGregorianCalendarModel.class).to(GregorianCalendarModel.class);
        bind(ILunarCalendarModel.class).to(LunarCalendarModel.class);
        bind(IHebrewCalendarModel.class).to(HebrewCalendarModel.class);

        bind(ISharedStateManager.class).to(SharedStateManager.class)
            .in(Scopes.SINGLETON);

        install(
            new FactoryModuleBuilder()
                .implement(CreateQuotesStage.class, CreateQuotesStage.class)
                .build(CreateQuotesStageFactory.class)
        );
        install(
            new FactoryModuleBuilder()
                .implement(ImportTxtStage.class, ImportTxtStage.class)
                .build(ImportTxtStageFactory.class)
        );
        install(
            new FactoryModuleBuilder()
                .implement(CenterEditQuotesRowGridPaneView.class, CenterEditQuotesRowGridPaneView.class)
                .build(CenterEditQuotesRowGridPaneViewFactory.class)
        );
        install(
            new FactoryModuleBuilder()
                .implement(CenterEditQuotesRowGridPaneViewModel.class, CenterEditQuotesRowGridPaneViewModel.class)
                .build(CenterEditQuotesRowGridPaneViewModelFactory.class)
        );
        install(
            new FactoryModuleBuilder()
                .implement(CenterEditIndividualVBoxView.class, CenterEditIndividualVBoxView.class)
                .build(CenterEditIndividualVBoxViewFactory.class)
        );
        install(
            new FactoryModuleBuilder()
                .implement(CenterEditIndividualVBoxViewModel.class, CenterEditIndividualVBoxViewModel.class)
                .build(CenterEditIndividualGridPaneViewModelFactory.class)
        );
        install(
            new FactoryModuleBuilder()
                .implement(CenterEditIndividualScrollPaneView.class, CenterEditIndividualScrollPaneView.class)
                .build(CenterEditIndividualScrollPaneViewFactory.class)
        );
        install(
            new FactoryModuleBuilder()
                .implement(ImportDirectoryStage.class, ImportDirectoryStage.class)
                .build(ImportDirectoryStageFactory.class)
        );
    }
}
