package com.minkyu.yourdailyword.javafx.components.modals.importdirectory.center;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.common.protobased.MultilingualStringModel;
import com.minkyu.yourdailyword.common.protobased.QuoteModel;
import com.minkyu.yourdailyword.javafx.models.IInternationalizationModel;
import com.minkyu.yourdailyword.javafx.models.IQuotesManager;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwObservable;
import com.minkyu.yourdailyword.javafx.models.infrastructure.YdwViewModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class ImportDirectoryCenterGridPaneViewModel extends YdwViewModel {
	private final IQuotesManager quotesManager;
	private final YdwObservable<File> selectedFile = new YdwObservable<>(null);
	public final YdwObservable<String> selectedDirectoryLabel = new YdwObservable<>("No directory selected.");

	@Inject
	public ImportDirectoryCenterGridPaneViewModel(
		IQuotesManager quotesManager,
		IInternationalizationModel internationalizationModel
	) {
		this.quotesManager = quotesManager;

		this.addBeforeDestroyRunnable(
			selectedDirectoryLabel.subscribe(
				selectedFile,
				newFile -> {
					if (newFile == null) {
						return internationalizationModel.getString("no_directory_selected");
					}
					else {
						return selectedFile.get().getName();
					}
				}
			)
		);
	}

	public void setSelectedDirectory(File newSelectedDirectory) {
		if (newSelectedDirectory != null && newSelectedDirectory.isDirectory()) {
			File[] newSelectedFiles = newSelectedDirectory.listFiles();
			if (newSelectedFiles != null) {
				Set<Integer> fileIntegers = new HashSet<>();
				for (File file : newSelectedFiles) {
					if (file.exists() && file.canRead() && (!file.isDirectory())) {
						String fileName = file.getName();
						fileName = fileName.replaceAll("[^0-9]", "");
						fileIntegers.add(
							Integer.parseInt(fileName) - 1
						);
					}
				}
				if (
					fileIntegers.containsAll(IntStream.range(0, 12).boxed().toList())
				) {
					this.selectedFile.set(newSelectedDirectory);
				}
			}
		}
	}

	public void importQuotes() {
		File currentSelectedDirectory = selectedFile.get();
		if (currentSelectedDirectory != null && currentSelectedDirectory.isDirectory()) {
			File[] currentSelectedFiles = currentSelectedDirectory.listFiles();
			if (currentSelectedFiles != null) {
				for (File file : currentSelectedFiles) {
					if (file.exists() && file.canRead() && (!file.isDirectory())) {
						try {
							String fileName = file.getName();
							fileName = fileName.replaceAll("[^0-9]", "");
							int month = Integer.parseInt(fileName) - 1;

							try(
								BufferedReader bufferedReader = new BufferedReader(
									new FileReader(file)
								)
							) {
								int currentDayOfMonth = 0;
								for (
									String line = bufferedReader.readLine();
									line != null;
									line = bufferedReader.readLine())
								{
									quotesManager.addQuote(
										new QuoteModel(
											quotesManager.getNewUid(),
											new MultilingualStringModel(
												line
											),
											month,
											currentDayOfMonth,
											null,
											null,
											null
										)
									);
									currentDayOfMonth++;
								}
							} catch (IOException ignored) {
							}
						}
						catch (NumberFormatException ignored) {
						}
					}
				}
			}
		}
	}
}
