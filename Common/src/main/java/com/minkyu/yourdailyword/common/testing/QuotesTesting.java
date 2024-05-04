package com.minkyu.yourdailyword.common.testing;

import com.minkyu.yourdailyword.common.protos.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class QuotesTesting {
    public static Quotes generateTestQuotes() {
        Quotes.Builder currentQuotesBuilder = Quotes
            .newBuilder()
            .setType(CalendarType.LUNAR_BASED);
        for (int month = 0; month < 13; month++) {
            for (int dayOfMonth = 0; dayOfMonth < 32; dayOfMonth++) {
                currentQuotesBuilder
                    .addValues(
                        Quote.newBuilder()
                            .setValue(
                                MultilingualString.newBuilder()
                                    .setEnglish(
                                        generateRandomLoremIpsum()
                                            .concat(" ")
                                            .concat(
                                                String.format(
                                                    "Test Quote String for %d %d", month, dayOfMonth
                                                )
                                            )
                                    )
                                    .build()
                            )
                            .setAssociatedMonth(month)
                            .setAssociatedDayOfMonth(dayOfMonth)
                            .setLunarCalendarOptions(
                                QuoteLunarCalendarOptions
                                    .newBuilder()
                                    .setSkipOnShortYear(month % 2 == 0)
                                    .setSkipOnShortMonth(dayOfMonth % 2 == 0)
                                    .build()
                            )
                            .build()
                    );
            }
        }

        return currentQuotesBuilder.build();
    }

    static private String generateRandomLoremIpsum() {
        String[] loremIpsumText = {};

        String[] quotesFiles = new String[]{
            "AugustusQuote.txt",
            "CiceroQuote.txt",
            "DescartesQuote.txt"
        };
        int quoteFileIndex = new Random().nextInt(quotesFiles.length);

        try {
            InputStream inputStream = Thread
                .currentThread()
                .getContextClassLoader()
                .getResourceAsStream(
                   quotesFiles[quoteFileIndex]
                );

            if (inputStream != null) {
                loremIpsumText = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8).split("\\s+");
            }
        }
        catch(IOException ignored) {
            return "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        }

        int numberOfWords = new Random().nextInt(10, 100);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numberOfWords && i < loremIpsumText.length; i++) {
            stringBuilder.append(loremIpsumText[i]);
            if (i + 1 < numberOfWords) {
                stringBuilder.append(" ");
            }
        }

        return stringBuilder.toString();
    }
}
