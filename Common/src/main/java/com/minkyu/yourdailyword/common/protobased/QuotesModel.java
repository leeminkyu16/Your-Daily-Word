package com.minkyu.yourdailyword.common.protobased;

import com.minkyu.yourdailyword.common.protos.Quotes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class QuotesModel implements ProtoBasedModel<Quotes> {
	@NotNull
	public final CalendarTypeModel type;
	@NotNull
	public final ArrayList<QuoteModel> values;
	@Nullable
	public final GregorianCalendarOptionsModel gregorianCalendarOptionsModel;
	@Nullable
	public final LunarCalendarOptionsModel lunarCalendarOptionsModel;
	@Nullable
	public final HebrewCalendarOptionsModel hebrewCalendarOptionsModel;

	public QuotesModel(
		@NotNull CalendarTypeModel type,
		@NotNull ArrayList<QuoteModel> values,
		@Nullable GregorianCalendarOptionsModel gregorianCalendarOptionsModel,
		@Nullable LunarCalendarOptionsModel lunarCalendarOptionsModel,
		@Nullable HebrewCalendarOptionsModel hebrewCalendarOptionsModel
	) {
		this.type = type;
		this.values = values;
		this.gregorianCalendarOptionsModel = gregorianCalendarOptionsModel;
		this.lunarCalendarOptionsModel = lunarCalendarOptionsModel;
		this.hebrewCalendarOptionsModel = hebrewCalendarOptionsModel;
	}

	synchronized public Quotes toProto() {
		Quotes.Builder builder = Quotes.newBuilder();
		builder
			.setType(this.type.toProto());
		builder
			.addAllValues(
				this.values.stream().map(
					QuoteModel::toProto
				).collect(Collectors.toList())
			);
		if (this.gregorianCalendarOptionsModel != null) {
			builder
				.setGregorianCalendarOptions(this.gregorianCalendarOptionsModel.toProto());
		}
		if (this.lunarCalendarOptionsModel != null) {
			builder
				.setLunarCalendarOptions(this.lunarCalendarOptionsModel.toProto());
		}
		if (this.hebrewCalendarOptionsModel != null) {
			builder
				.setHebrewCalendarOptions(this.hebrewCalendarOptionsModel.toProto());
		}
		return builder.build();
	}

	public static QuotesModel fromProto(Quotes proto) {
		if (proto == null) {
			return null;
		}

		return new QuotesModel(
			CalendarTypeModel.fromProto(proto.getType()),
			proto.getValuesList().stream().map(
				QuoteModel::fromProto
			).collect(Collectors.toCollection(ArrayList::new)),
			GregorianCalendarOptionsModel.fromProto(proto.getGregorianCalendarOptions()),
			LunarCalendarOptionsModel.fromProto(proto.getLunarCalendarOptions()),
			HebrewCalendarOptionsModel.fromProto(proto.getHebrewCalendarOptions())
		);
	}
}
