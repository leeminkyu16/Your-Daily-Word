package com.minkyu.yourdailyword.javafx.components.top;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.common.protos.Quotes;
import com.minkyu.yourdailyword.javafx.models.IQuotesManager;
import com.minkyu.yourdailyword.javafx.models.ISharedStateManager;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import com.minkyu.yourdailyword.javafx.models.sharedstate.ApplicationSharedState;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TopMenuBarViewModel extends YdwViewModel {

	public ApplicationSharedState applicationSharedState;
	private final IQuotesManager quotesManager;

	@Inject
	public TopMenuBarViewModel(
		ISharedStateManager sharedStateManager,
		IQuotesManager quotesManager
	) {
		this.quotesManager = quotesManager;
		applicationSharedState = sharedStateManager.getAppliationSharedState();
	}

	void saveQuotes() {
		quotesManager.saveCurrentModelToFile();
	}

	void importQuotes(File importLocation) {
		if (importLocation == null) {
			return;
		}

		try (FileInputStream fileInputStream = new FileInputStream(importLocation)) {
			quotesManager.setFromProto(
				Quotes.parseDelimitedFrom(fileInputStream)
			);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	void exportQuotes(File exportLocation) {
		if (exportLocation == null) {
			return;
		}

		try (FileOutputStream fileOutputStream = new FileOutputStream(exportLocation)) {
			quotesManager.getProto().writeDelimitedTo(
				fileOutputStream
			);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
