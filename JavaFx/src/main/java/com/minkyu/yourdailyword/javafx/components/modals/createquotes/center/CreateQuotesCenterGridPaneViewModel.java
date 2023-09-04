package com.minkyu.yourdailyword.javafx.components.modals.createquotes.center;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.common.protos.HebrewCalendarOptions;
import com.minkyu.yourdailyword.common.protos.Quotes;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import com.minkyu.yourdailyword.javafx.models.IQuotesManager;
import com.minkyu.yourdailyword.common.protobased.CalendarTypeModel;

public class CreateQuotesCenterGridPaneViewModel extends YdwViewModel {
	IQuotesManager quotesManager;

	@Inject
	public CreateQuotesCenterGridPaneViewModel(
		IQuotesManager quotesManager
	) {
		this.quotesManager = quotesManager;
	}

	void createQuote(
		CalendarTypeModel calendarType,
		HebrewCalendarOptions hebrewCalendarOptions
	) {
		quotesManager.setFromProto(
			Quotes.newBuilder()
				.setType(calendarType.toProto())
				.setHebrewCalendarOptions(hebrewCalendarOptions)
				.build()
		);
	}
}
