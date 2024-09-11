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
import com.minkyu.yourdailyword.javafx.components.modals.qrcode.QrCodeBorderPaneView;
import com.minkyu.yourdailyword.javafx.components.modals.qrcode.QrCodeScene;
import com.minkyu.yourdailyword.javafx.components.modals.qrcode.QrCodeStage;
import com.minkyu.yourdailyword.javafx.components.modals.qrcode.center.QrCodeCenterDynamicColumnView;
import com.minkyu.yourdailyword.javafx.models.*;
import com.minkyu.yourdailyword.javafx.models.grpc.SyncQuotesServiceImpl;
import com.minkyu.yourdailyword.javafx.models.ui.ITextFieldManager;
import com.minkyu.yourdailyword.javafx.models.ui.TextFieldManager;

public class ModelModules extends AbstractModule {
    @Override
    protected void configure() {
        bind(IInternationalizationModel.class).to(InternationalizationModel.class);
        bind(IDesktopIoModel.class).to(DesktopIoModel.class).in(Singleton.class);
        bind(IQuotesManager.class).to(QuotesManager.class).in(Singleton.class);
        bind(ITextFieldManager.class).to(TextFieldManager.class);
        bind(IGregorianCalendarModel.class).to(GregorianCalendarModel.class);
        bind(ILunarCalendarModel.class).to(LunarCalendarModel.class);
        bind(IHebrewCalendarModel.class).to(HebrewCalendarModel.class);

        bind(ISharedStateManager.class)
            .to(SharedStateManager.class)
            .in(Scopes.SINGLETON);
        bind(ISettingsManager.class)
            .to(SettingsManager.class)
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
        install(
            new FactoryModuleBuilder()
                .implement(QrCodeBorderPaneView.class, QrCodeBorderPaneView.class)
                .build(QrCodeBorderPaneViewFactory.class)
        );
        install(
            new FactoryModuleBuilder()
                .implement(QrCodeScene.class, QrCodeScene.class)
                .build(QrCodeSceneFactory.class)
        );
        install(
            new FactoryModuleBuilder()
                .implement(QrCodeStage.class, QrCodeStage.class)
                .build(QrCodeStageFactory.class)
        );
        install(
            new FactoryModuleBuilder()
                .implement(SyncQuotesServiceImpl.class, SyncQuotesServiceImpl.class)
                .build(SyncQuotesServiceImplFactory.class)
        );
        install(
            new FactoryModuleBuilder()
                .implement(QrCodeCenterDynamicColumnView.class, QrCodeCenterDynamicColumnView.class)
                .build(QrCodeCenterDynamicColumnViewFactory.class)
        );
    }
}
