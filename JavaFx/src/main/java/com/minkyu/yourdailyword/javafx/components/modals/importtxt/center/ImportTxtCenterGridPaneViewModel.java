package com.minkyu.yourdailyword.javafx.components.modals.importtxt.center;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.common.protobased.MultilingualStringModel;
import com.minkyu.yourdailyword.common.protobased.QuoteModel;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import com.minkyu.yourdailyword.javafx.models.IQuotesManager;
import com.minkyu.yourdailyword.javafx.models.ISharedStateManager;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;
import com.minkyu.yourdailyword.javafx.models.sharedstate.ImportTxtSharedState;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class ImportTxtCenterGridPaneViewModel extends YdwViewModel {
	final ImportTxtSharedState importTxtSharedState;
	private final IQuotesManager quotesManager;
	private final IInternationalizationModel internationalizationModel;

	public final YdwObservable<File> selectedFile = new YdwObservable<>(null);

	@Inject
	public ImportTxtCenterGridPaneViewModel(
		IQuotesManager quotesManager,
		IInternationalizationModel internationalizationModel,
		ISharedStateManager sharedStateManager
	) {
		this.quotesManager = quotesManager;
		this.internationalizationModel = internationalizationModel;
		this.importTxtSharedState = sharedStateManager.getImportTxtSharedState();
	}

	public void importQuotes(int associatedMonth) {
		if (this.selectedFile.get() == null) {
			return;
		}

		try (
			BufferedReader bufferedReader = new BufferedReader(
				new FileReader(this.selectedFile.get())
			)
		) {
			int currentDayOfMonth = 0;
			String line = bufferedReader.readLine();
			while (line != null) {
				quotesManager.addQuote(
					new QuoteModel(
						quotesManager.getNewUid(),
						new MultilingualStringModel(
							line
						),
						associatedMonth,
						currentDayOfMonth,
						null,
						null,
						null
					)
				);

				line = bufferedReader.readLine();
				currentDayOfMonth++;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		this.importTxtSharedState.bottomMessage.set(
			String.format(
				Locale.getDefault(),
				internationalizationModel.getString("import_file_success_message"),
				this.selectedFile.get().getName(),
				associatedMonth + 1
			)
		);
	}
}
