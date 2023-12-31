// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: QuoteLunarCalendarOptions.proto

package com.minkyu.yourdailyword.common.protos;

/**
 * Protobuf type {@code yourdailyword.QuoteLunarCalendarOptions}
 */
public final class QuoteLunarCalendarOptions extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:yourdailyword.QuoteLunarCalendarOptions)
    QuoteLunarCalendarOptionsOrBuilder {
private static final long serialVersionUID = 0L;
  // Use QuoteLunarCalendarOptions.newBuilder() to construct.
  private QuoteLunarCalendarOptions(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QuoteLunarCalendarOptions() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new QuoteLunarCalendarOptions();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptionsProto.internal_static_yourdailyword_QuoteLunarCalendarOptions_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptionsProto.internal_static_yourdailyword_QuoteLunarCalendarOptions_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions.class, com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions.Builder.class);
  }

  public static final int SKIPONSHORTYEAR_FIELD_NUMBER = 1;
  private boolean skipOnShortYear_ = false;
  /**
   * <code>bool skipOnShortYear = 1;</code>
   * @return The skipOnShortYear.
   */
  @java.lang.Override
  public boolean getSkipOnShortYear() {
    return skipOnShortYear_;
  }

  public static final int SKIPONSHORTMONTH_FIELD_NUMBER = 2;
  private boolean skipOnShortMonth_ = false;
  /**
   * <code>bool skipOnShortMonth = 2;</code>
   * @return The skipOnShortMonth.
   */
  @java.lang.Override
  public boolean getSkipOnShortMonth() {
    return skipOnShortMonth_;
  }

  public static final int SETTING3_FIELD_NUMBER = 3;
  private boolean setting3_ = false;
  /**
   * <code>bool setting3 = 3;</code>
   * @return The setting3.
   */
  @java.lang.Override
  public boolean getSetting3() {
    return setting3_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (skipOnShortYear_ != false) {
      output.writeBool(1, skipOnShortYear_);
    }
    if (skipOnShortMonth_ != false) {
      output.writeBool(2, skipOnShortMonth_);
    }
    if (setting3_ != false) {
      output.writeBool(3, setting3_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (skipOnShortYear_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(1, skipOnShortYear_);
    }
    if (skipOnShortMonth_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(2, skipOnShortMonth_);
    }
    if (setting3_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(3, setting3_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions)) {
      return super.equals(obj);
    }
    com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions other = (com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions) obj;

    if (getSkipOnShortYear()
        != other.getSkipOnShortYear()) return false;
    if (getSkipOnShortMonth()
        != other.getSkipOnShortMonth()) return false;
    if (getSetting3()
        != other.getSetting3()) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + SKIPONSHORTYEAR_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getSkipOnShortYear());
    hash = (37 * hash) + SKIPONSHORTMONTH_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getSkipOnShortMonth());
    hash = (37 * hash) + SETTING3_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getSetting3());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code yourdailyword.QuoteLunarCalendarOptions}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:yourdailyword.QuoteLunarCalendarOptions)
      com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptionsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptionsProto.internal_static_yourdailyword_QuoteLunarCalendarOptions_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptionsProto.internal_static_yourdailyword_QuoteLunarCalendarOptions_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions.class, com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions.Builder.class);
    }

    // Construct using com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      skipOnShortYear_ = false;
      skipOnShortMonth_ = false;
      setting3_ = false;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptionsProto.internal_static_yourdailyword_QuoteLunarCalendarOptions_descriptor;
    }

    @java.lang.Override
    public com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions getDefaultInstanceForType() {
      return com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions.getDefaultInstance();
    }

    @java.lang.Override
    public com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions build() {
      com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions buildPartial() {
      com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions result = new com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.skipOnShortYear_ = skipOnShortYear_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.skipOnShortMonth_ = skipOnShortMonth_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.setting3_ = setting3_;
      }
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions) {
        return mergeFrom((com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions other) {
      if (other == com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions.getDefaultInstance()) return this;
      if (other.getSkipOnShortYear() != false) {
        setSkipOnShortYear(other.getSkipOnShortYear());
      }
      if (other.getSkipOnShortMonth() != false) {
        setSkipOnShortMonth(other.getSkipOnShortMonth());
      }
      if (other.getSetting3() != false) {
        setSetting3(other.getSetting3());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              skipOnShortYear_ = input.readBool();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 16: {
              skipOnShortMonth_ = input.readBool();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
            case 24: {
              setting3_ = input.readBool();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private boolean skipOnShortYear_ ;
    /**
     * <code>bool skipOnShortYear = 1;</code>
     * @return The skipOnShortYear.
     */
    @java.lang.Override
    public boolean getSkipOnShortYear() {
      return skipOnShortYear_;
    }
    /**
     * <code>bool skipOnShortYear = 1;</code>
     * @param value The skipOnShortYear to set.
     * @return This builder for chaining.
     */
    public Builder setSkipOnShortYear(boolean value) {
      
      skipOnShortYear_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>bool skipOnShortYear = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearSkipOnShortYear() {
      bitField0_ = (bitField0_ & ~0x00000001);
      skipOnShortYear_ = false;
      onChanged();
      return this;
    }

    private boolean skipOnShortMonth_ ;
    /**
     * <code>bool skipOnShortMonth = 2;</code>
     * @return The skipOnShortMonth.
     */
    @java.lang.Override
    public boolean getSkipOnShortMonth() {
      return skipOnShortMonth_;
    }
    /**
     * <code>bool skipOnShortMonth = 2;</code>
     * @param value The skipOnShortMonth to set.
     * @return This builder for chaining.
     */
    public Builder setSkipOnShortMonth(boolean value) {
      
      skipOnShortMonth_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>bool skipOnShortMonth = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearSkipOnShortMonth() {
      bitField0_ = (bitField0_ & ~0x00000002);
      skipOnShortMonth_ = false;
      onChanged();
      return this;
    }

    private boolean setting3_ ;
    /**
     * <code>bool setting3 = 3;</code>
     * @return The setting3.
     */
    @java.lang.Override
    public boolean getSetting3() {
      return setting3_;
    }
    /**
     * <code>bool setting3 = 3;</code>
     * @param value The setting3 to set.
     * @return This builder for chaining.
     */
    public Builder setSetting3(boolean value) {
      
      setting3_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>bool setting3 = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearSetting3() {
      bitField0_ = (bitField0_ & ~0x00000004);
      setting3_ = false;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:yourdailyword.QuoteLunarCalendarOptions)
  }

  // @@protoc_insertion_point(class_scope:yourdailyword.QuoteLunarCalendarOptions)
  private static final com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions();
  }

  public static com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<QuoteLunarCalendarOptions>
      PARSER = new com.google.protobuf.AbstractParser<QuoteLunarCalendarOptions>() {
    @java.lang.Override
    public QuoteLunarCalendarOptions parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<QuoteLunarCalendarOptions> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QuoteLunarCalendarOptions> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.minkyu.yourdailyword.common.protos.QuoteLunarCalendarOptions getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

