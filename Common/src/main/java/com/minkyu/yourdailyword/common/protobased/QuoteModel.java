package com.minkyu.yourdailyword.common.protobased;

import com.minkyu.yourdailyword.common.protos.Quote;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class QuoteModel implements ProtoBasedModel<Quote> {
    private int uid;
    @NotNull
    private MultilingualStringModel value;
    private int associatedMonth;
    private int associatedDayOfMonth;
    @Nullable
    private QuoteGregorianCalendarOptionsModel quoteGregorianCalendarOptionsModel;
    @Nullable
    private QuoteLunarCalendarOptionsModel quoteLunarCalendarOptionsModel;
    @Nullable
    private QuoteHebrewCalendarOptionsModel quoteHebrewCalendarOptionsModel;

    synchronized public int getUid() {
        return uid;
    }

    synchronized public void setUid(int uid) {
        this.uid = uid;
    }

    synchronized public @NotNull MultilingualStringModel getValue() {
        return value;
    }

    synchronized public void setValue(@NotNull MultilingualStringModel value) {
        this.value = value;
    }

    synchronized public int getAssociatedMonth() {
        return associatedMonth;
    }

    synchronized public void setAssociatedMonth(int associatedMonth) {
        this.associatedMonth = associatedMonth;
    }

    synchronized public int getAssociatedDayOfMonth() {
        return associatedDayOfMonth;
    }

    synchronized public void setAssociatedDayOfMonth(int associatedDayOfMonth) {
        this.associatedDayOfMonth = associatedDayOfMonth;
    }

    synchronized public @Nullable QuoteGregorianCalendarOptionsModel getQuoteGregorianCalendarOptionsModel() {
        return quoteGregorianCalendarOptionsModel;
    }

    synchronized public void setQuoteGregorianCalendarOptionsModel(@Nullable QuoteGregorianCalendarOptionsModel quoteGregorianCalendarOptionsModel) {
        this.quoteGregorianCalendarOptionsModel = quoteGregorianCalendarOptionsModel;
    }

    synchronized public @Nullable QuoteLunarCalendarOptionsModel getQuoteLunarCalendarOptionsModel() {
        return quoteLunarCalendarOptionsModel;
    }

    synchronized public void setQuoteLunarCalendarOptionsModel(@Nullable QuoteLunarCalendarOptionsModel quoteLunarCalendarOptionsModel) {
        this.quoteLunarCalendarOptionsModel = quoteLunarCalendarOptionsModel;
    }

    synchronized public @Nullable QuoteHebrewCalendarOptionsModel getQuoteHebrewCalendarOptionsModel() {
        return quoteHebrewCalendarOptionsModel;
    }

    synchronized public void setQuoteHebrewCalendarOptionsModel(@Nullable QuoteHebrewCalendarOptionsModel quoteHebrewCalendarOptionsModel) {
        this.quoteHebrewCalendarOptionsModel = quoteHebrewCalendarOptionsModel;
    }

    public QuoteModel(
        int uid,
        @NotNull MultilingualStringModel value,
        int associatedMonth,
        int associatedDayOfMonth,
        @Nullable QuoteGregorianCalendarOptionsModel quoteGregorianCalendarOptionsModel,
        @Nullable QuoteLunarCalendarOptionsModel quoteLunarCalendarOptionsModel,
        @Nullable QuoteHebrewCalendarOptionsModel quoteHebrewCalendarOptionsModel
    ) {
        this.uid = uid;
        this.value = value;
        this.associatedMonth = associatedMonth;
        this.associatedDayOfMonth = associatedDayOfMonth;
        this.quoteGregorianCalendarOptionsModel = quoteGregorianCalendarOptionsModel;
        this.quoteLunarCalendarOptionsModel = quoteLunarCalendarOptionsModel;
        this.quoteHebrewCalendarOptionsModel = quoteHebrewCalendarOptionsModel;
    }

    synchronized public Quote toProto() {
        Quote.Builder builder = Quote.newBuilder();

        builder
            .setUid(this.uid);
        builder
            .setValue(this.value.toProto());
        builder
            .setAssociatedMonth(this.associatedMonth);
        builder
            .setAssociatedDayOfMonth(this.associatedDayOfMonth);
        if (this.quoteGregorianCalendarOptionsModel != null) {
            builder
                .setGregorianCalendarOptions(this.quoteGregorianCalendarOptionsModel.toProto());
        }
        if (this.quoteLunarCalendarOptionsModel != null) {
            builder
                .setLunarCalendarOptions(this.quoteLunarCalendarOptionsModel.toProto());
        }
        if (this.quoteHebrewCalendarOptionsModel != null) {
            builder
                .setHebrewCalendarOptions(this.quoteHebrewCalendarOptionsModel.toProto());
        }

        return builder.build();
    }

    public static QuoteModel fromProto(Quote proto) {
        if (proto == null) {
            return null;
        }

        return new QuoteModel(
            proto.getUid(),
            MultilingualStringModel.fromProto(proto.getValue()),
            proto.getAssociatedMonth(),
            proto.getAssociatedDayOfMonth(),
            QuoteGregorianCalendarOptionsModel.fromProto(proto.getGregorianCalendarOptions()),
            QuoteLunarCalendarOptionsModel.fromProto(proto.getLunarCalendarOptions()),
            QuoteHebrewCalendarOptionsModel.fromProto(proto.getHebrewCalendarOptions())
        );
    }
}
