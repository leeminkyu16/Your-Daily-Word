package com.minkyu.yourdailyword.javafx.components.center.view;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.minkyu.yourdailyword.common.calendar.ICalendarModel;
import com.minkyu.yourdailyword.common.calendar.IGregorianCalendarModel;
import com.minkyu.yourdailyword.common.calendar.IHebrewCalendarModel;
import com.minkyu.yourdailyword.common.calendar.ILunarCalendarModel;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import com.minkyu.yourdailyword.javafx.models.IQuotesManager;
import com.minkyu.yourdailyword.javafx.models.ISharedStateManager;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwWeakReference;
import com.minkyu.yourdailyword.javafx.models.sharedstate.ApplicationSharedState;
import javafx.beans.property.SimpleStringProperty;

public class CenterViewQuoteTextAreaViewModel extends YdwViewModel {
	private final ApplicationSharedState applicationSharedState;
	private final IQuotesManager quotesManager;
	private final ICalendarModel calendarModel;
	final SimpleStringProperty quote = new SimpleStringProperty("");
	private final IInternationalizationModel internationalizationModel;

	@Inject
	public CenterViewQuoteTextAreaViewModel(
		IQuotesManager quotesManager,
		ISharedStateManager sharedStateManager,
		Injector injector,
		IInternationalizationModel internationalizationModel
	) {
		this.quotesManager = quotesManager;
		this.applicationSharedState = sharedStateManager.getAppliationSharedState();
		this.internationalizationModel = internationalizationModel;

		switch (this.quotesManager.getCalendarType()) {
			case GREGORIAN_BASED -> this.calendarModel = injector.getInstance(IGregorianCalendarModel.class);
			case LUNAR_BASED -> this.calendarModel = injector.getInstance(ILunarCalendarModel.class);
			case HEBREW_BASED -> this.calendarModel = injector.getInstance(IHebrewCalendarModel.class);
			default -> this.calendarModel = null;
		}

		this.quote.set(
			quotesManager.getQuote(calendarModel)
		);
		applicationSharedState.bottomMessage.set(
			internationalizationModel.getString("quote_import_success_message")
		);
		YdwWeakReference<CenterViewQuoteTextAreaViewModel> weakThisRef = new YdwWeakReference<>(this);
		this.quotesManager.addAfterQuotesUpdateRunnable(
			() -> {
				weakThisRef.doIfNotNull( weakThis -> {
						weakThis.quote.set(
							weakThis.quotesManager.getQuote(weakThis.calendarModel)
						);
						weakThis.applicationSharedState.bottomMessage.set(
							weakThis.internationalizationModel.getString("quote_import_success_message")
						);
					}
				);
			}
		);

		applicationSharedState.bottomMessage.set(
			internationalizationModel.getString("quote_import_success_message")
		);
	}
}
