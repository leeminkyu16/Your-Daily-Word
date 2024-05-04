package com.minkyu.yourdailyword.javafx.models;

import com.google.inject.Inject;
import com.minkyu.yourdailyword.common.calendar.ICalendarModel;
import com.minkyu.yourdailyword.common.calendar.IGregorianCalendarModel;
import com.minkyu.yourdailyword.common.calendar.IHebrewCalendarModel;
import com.minkyu.yourdailyword.common.calendar.ILunarCalendarModel;
import com.minkyu.yourdailyword.common.protobased.*;
import com.minkyu.yourdailyword.common.protos.Quotes;
import com.minkyu.yourdailyword.common.testing.QuotesTesting;
import com.minkyu.yourdailyword.javafx.models.settings.FlagsSingleton;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

public class QuotesManager implements IQuotesManager {
	private final IDesktopIoModel ioModel;

	private QuotesModel internalModel;
	private final Map<Integer, QuoteModel> internalModelMap = new HashMap<>();

	final ArrayList<Runnable> afterQuotesUpdateRunnables = new ArrayList<>();

	@Inject
	public QuotesManager(IDesktopIoModel ioModel) {
		this.ioModel = ioModel;
		File quoteFile = new File(ioModel.getLocalApplicationDataDirectory("default.ydw"));

		if (quoteFile.exists()) {
			try (FileInputStream fileInputStream = new FileInputStream(quoteFile)) {
				this.internalModelMap.clear();
				this.internalModel = QuotesModel.fromProto(
					Quotes.parseDelimitedFrom(
						fileInputStream
					)
				);
				this.internalModel.values.forEach(quoteModel -> internalModelMap.put(quoteModel.getUid(), quoteModel));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			this.internalModel = new QuotesModel(
				CalendarTypeModel.GREGORIAN_BASED,
				new ArrayList<>(),
				null,
				null,
				null
			);
		}

		if (FlagsSingleton.debug && FlagsSingleton.generateDebugModel) {
			this.internalModel = QuotesModel.fromProto(QuotesTesting.generateTestQuotes());
		}
	}

	@Override
	synchronized public int getNewUid() {
		if (this.internalModel.values.isEmpty()) {
			return 0;
		}

		Set<Integer> currentUids = internalModelMap.keySet();

		int returnValue = this.internalModel.values.get(this.internalModel.values.size() - 1).getUid() + 1;

		while (currentUids.contains(returnValue)) {
			returnValue += 1;
		}
		return returnValue;
	}

	@Override
	synchronized public void addQuote(QuoteModel quoteModel) {
		this.internalModel.values.add(quoteModel);
		this.internalModelMap.put(quoteModel.getUid(), quoteModel);
		this.afterQuotesUpdateRunnables.forEach(Runnable::run);
	}

	@Override
	synchronized public void setFromProto(Quotes quotesProto) {
		this.internalModel = QuotesModel.fromProto(quotesProto);
		this.internalModelMap.clear();
		this.internalModel.values.forEach(quoteModel -> internalModelMap.put(quoteModel.getUid(), quoteModel));
		this.afterQuotesUpdateRunnables.forEach(Runnable::run);
	}

	@Override
	synchronized public Quotes getProto() {
		return this.internalModel.toProto();
	}

	@Override
	synchronized public int getNumOfQuotes() {
		return internalModel.values.size();
	}

	@Override
	synchronized public CalendarTypeModel getCalendarType() {
		return internalModel.type;
	}

	@Override
	synchronized public QuoteModel getQuoteModelByIndex(int index) {
		if (0 <= index && index < this.internalModel.values.size()) {
			return this.internalModel.values.get(index);
		} else {
			return null;
		}
	}

	@Override
	public void getQuoteModelByIndexAndDoIfNotNull(int index, Consumer<QuoteModel> action) {
		QuoteModel retrievedValue = this.getQuoteModelByIndex(index);
		if (retrievedValue != null) {
			action.accept(retrievedValue);
		}
	}

	@Override
	synchronized public QuoteModel getQuoteModelByUid(int uid) {
		return internalModelMap.get(uid);
	}

	@Override
	public void getQuoteModelByUidAndDoIfNotNull(int uid, Consumer<QuoteModel> action) {
		QuoteModel retrievedValue = this.getQuoteModelByUid(uid);
		if (retrievedValue != null) {
			action.accept(retrievedValue);
		}
	}

	@Override
	synchronized public void deleteQuoteModelByUid(int uid) {
		QuoteModel candidateModel = this.internalModelMap.remove(uid);
		if (candidateModel != null) {
			this.internalModel.values.remove(candidateModel);
		}
		this.afterQuotesUpdateRunnables.forEach(Runnable::run);
	}

	@Override
	@Nullable
	synchronized public String getQuoteBasedOnDayOfMonth(int month, int dayOfMonth, Locale locale) {
		QuoteModel quote;
		quote = this.internalModel.values.stream().filter(
			(value) -> value.getAssociatedMonth() == month && value.getAssociatedDayOfMonth() == dayOfMonth
		).findFirst().orElse(null);

		return switch (locale.getLanguage()) {
			case "en" -> {
				if (quote != null) {
					yield quote.getValue().getEnglish();
				}
				yield null;
			}
			case "fr" -> "";
			default -> null;
		};
	}

	@Override
	@Nullable
	synchronized public GregorianCalendarOptionsModel getGregorianCalendarOptions() {
		return internalModel.gregorianCalendarOptionsModel;
	}

	@Override
	@Nullable
	synchronized public LunarCalendarOptionsModel getLunarCalendarOptionsModel() {
		return this.internalModel.lunarCalendarOptionsModel;
	}

	@Override
	@Nullable
	synchronized public HebrewCalendarOptionsModel getHebrewCalendarOptions() {
		return internalModel.hebrewCalendarOptionsModel;
	}

	@Override
	synchronized public Runnable addAfterQuotesUpdateRunnable(Runnable runnable) {
		this.afterQuotesUpdateRunnables.add(runnable);
		return () -> {
			this.afterQuotesUpdateRunnables.remove(runnable);
		};
	}

	@Override
	@Nullable
	public synchronized String getQuote(ICalendarModel calendarModel) {
		String quote = null;

		if (calendarModel instanceof IGregorianCalendarModel) {
			@SuppressWarnings("unused")
			GregorianCalendarOptionsModel options = this.getGregorianCalendarOptions();

			quote = this.getQuoteBasedOnDayOfMonth(
				calendarModel.getMonthNumber(),
				calendarModel.getDayOfMonth() - 1,
				Locale.getDefault()
			);
		} else if (calendarModel instanceof ILunarCalendarModel) {
			@SuppressWarnings("unused")
			LunarCalendarOptionsModel options = this.getLunarCalendarOptionsModel();

			quote = this.getQuoteBasedOnDayOfMonth(
				calendarModel.getMonthNumber(),
				calendarModel.getDayOfMonth() - 1,
				Locale.getDefault()
			);
		} else if (calendarModel instanceof IHebrewCalendarModel) {
			HebrewCalendarOptionsModel hebrewCalendarOptions = this.getHebrewCalendarOptions();

			quote = this.getQuoteBasedOnDayOfMonth(
				calendarModel.getMonthNumber(
					hebrewCalendarOptions != null && hebrewCalendarOptions.getAdarIsAdar1()
				),
				calendarModel.getDayOfMonth() - 1,
				Locale.getDefault()
			);
		}

		return quote;
	}

	@Override
	public void saveCurrentModelToFile() {
		try (
			FileOutputStream fileOutputStream =
				ioModel.getLocalApplicationDataDirectoryFileOutputStream("default.ydw")
		) {
			this.getProto().writeDelimitedTo(
				fileOutputStream
			);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
