package com.minkyu.yourdailyword.javafx.components.center.view;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.minkyu.yourdailyword.common.calendar.ICalendarModel;
import com.minkyu.yourdailyword.common.calendar.IGregorianCalendarModel;
import com.minkyu.yourdailyword.common.calendar.IHebrewCalendarModel;
import com.minkyu.yourdailyword.common.calendar.ILunarCalendarModel;
import com.minkyu.yourdailyword.javafx.models.IQuotesManager;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwWeakReference;
import javafx.beans.property.SimpleStringProperty;

public class CenterViewCopyQuoteButtonViewModel extends YdwViewModel {
	SimpleStringProperty quote = new SimpleStringProperty("");
	private final ICalendarModel calendarModel;
	private final IQuotesManager quotesManager;

	@Inject
	CenterViewCopyQuoteButtonViewModel(
		IQuotesManager quotesManager,
		Injector injector
	) {
		switch (quotesManager.getCalendarType()) {
			case GREGORIAN_BASED -> this.calendarModel = injector.getInstance(IGregorianCalendarModel.class);
			case LUNAR_BASED -> this.calendarModel = injector.getInstance(ILunarCalendarModel.class);
			case HEBREW_BASED -> this.calendarModel = injector.getInstance(IHebrewCalendarModel.class);
			default -> this.calendarModel = null;
		}
		this.quotesManager = quotesManager;

		this.quote.set(
			quotesManager.getQuote(this.calendarModel)
		);
		YdwWeakReference<CenterViewCopyQuoteButtonViewModel> weakThisRef = new YdwWeakReference<>(this);
		quotesManager.addAfterQuotesUpdateRunnable(
			() -> {
				weakThisRef.doIfNotNull(weakThis -> {
					weakThis.quote.set(weakThis.quotesManager.getQuote(weakThis.calendarModel));
				});
			}
		);
	}
}
