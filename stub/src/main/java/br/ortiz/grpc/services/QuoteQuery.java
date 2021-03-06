// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: stocks.proto

package br.ortiz.grpc.services;

/**
 * Protobuf type {@code services.QuoteQuery}
 */
public final class QuoteQuery extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:services.QuoteQuery)
    QuoteQueryOrBuilder {
private static final long serialVersionUID = 0L;
  // Use QuoteQuery.newBuilder() to construct.
  private QuoteQuery(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private QuoteQuery() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new QuoteQuery();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private QuoteQuery(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            com.google.protobuf.StringValue.Builder subBuilder = null;
            if (symbol_ != null) {
              subBuilder = symbol_.toBuilder();
            }
            symbol_ = input.readMessage(com.google.protobuf.StringValue.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(symbol_);
              symbol_ = subBuilder.buildPartial();
            }

            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return br.ortiz.grpc.services.Stocks.internal_static_services_QuoteQuery_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return br.ortiz.grpc.services.Stocks.internal_static_services_QuoteQuery_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            br.ortiz.grpc.services.QuoteQuery.class, br.ortiz.grpc.services.QuoteQuery.Builder.class);
  }

  public static final int SYMBOL_FIELD_NUMBER = 1;
  private com.google.protobuf.StringValue symbol_;
  /**
   * <code>.google.protobuf.StringValue symbol = 1;</code>
   * @return Whether the symbol field is set.
   */
  @java.lang.Override
  public boolean hasSymbol() {
    return symbol_ != null;
  }
  /**
   * <code>.google.protobuf.StringValue symbol = 1;</code>
   * @return The symbol.
   */
  @java.lang.Override
  public com.google.protobuf.StringValue getSymbol() {
    return symbol_ == null ? com.google.protobuf.StringValue.getDefaultInstance() : symbol_;
  }
  /**
   * <code>.google.protobuf.StringValue symbol = 1;</code>
   */
  @java.lang.Override
  public com.google.protobuf.StringValueOrBuilder getSymbolOrBuilder() {
    return getSymbol();
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
    if (symbol_ != null) {
      output.writeMessage(1, getSymbol());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (symbol_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getSymbol());
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof br.ortiz.grpc.services.QuoteQuery)) {
      return super.equals(obj);
    }
    br.ortiz.grpc.services.QuoteQuery other = (br.ortiz.grpc.services.QuoteQuery) obj;

    if (hasSymbol() != other.hasSymbol()) return false;
    if (hasSymbol()) {
      if (!getSymbol()
          .equals(other.getSymbol())) return false;
    }
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasSymbol()) {
      hash = (37 * hash) + SYMBOL_FIELD_NUMBER;
      hash = (53 * hash) + getSymbol().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static br.ortiz.grpc.services.QuoteQuery parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static br.ortiz.grpc.services.QuoteQuery parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static br.ortiz.grpc.services.QuoteQuery parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static br.ortiz.grpc.services.QuoteQuery parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static br.ortiz.grpc.services.QuoteQuery parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static br.ortiz.grpc.services.QuoteQuery parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static br.ortiz.grpc.services.QuoteQuery parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static br.ortiz.grpc.services.QuoteQuery parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static br.ortiz.grpc.services.QuoteQuery parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static br.ortiz.grpc.services.QuoteQuery parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static br.ortiz.grpc.services.QuoteQuery parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static br.ortiz.grpc.services.QuoteQuery parseFrom(
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
  public static Builder newBuilder(br.ortiz.grpc.services.QuoteQuery prototype) {
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
   * Protobuf type {@code services.QuoteQuery}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:services.QuoteQuery)
      br.ortiz.grpc.services.QuoteQueryOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return br.ortiz.grpc.services.Stocks.internal_static_services_QuoteQuery_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return br.ortiz.grpc.services.Stocks.internal_static_services_QuoteQuery_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              br.ortiz.grpc.services.QuoteQuery.class, br.ortiz.grpc.services.QuoteQuery.Builder.class);
    }

    // Construct using br.ortiz.grpc.services.QuoteQuery.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (symbolBuilder_ == null) {
        symbol_ = null;
      } else {
        symbol_ = null;
        symbolBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return br.ortiz.grpc.services.Stocks.internal_static_services_QuoteQuery_descriptor;
    }

    @java.lang.Override
    public br.ortiz.grpc.services.QuoteQuery getDefaultInstanceForType() {
      return br.ortiz.grpc.services.QuoteQuery.getDefaultInstance();
    }

    @java.lang.Override
    public br.ortiz.grpc.services.QuoteQuery build() {
      br.ortiz.grpc.services.QuoteQuery result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public br.ortiz.grpc.services.QuoteQuery buildPartial() {
      br.ortiz.grpc.services.QuoteQuery result = new br.ortiz.grpc.services.QuoteQuery(this);
      if (symbolBuilder_ == null) {
        result.symbol_ = symbol_;
      } else {
        result.symbol_ = symbolBuilder_.build();
      }
      onBuilt();
      return result;
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
      if (other instanceof br.ortiz.grpc.services.QuoteQuery) {
        return mergeFrom((br.ortiz.grpc.services.QuoteQuery)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(br.ortiz.grpc.services.QuoteQuery other) {
      if (other == br.ortiz.grpc.services.QuoteQuery.getDefaultInstance()) return this;
      if (other.hasSymbol()) {
        mergeSymbol(other.getSymbol());
      }
      this.mergeUnknownFields(other.unknownFields);
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
      br.ortiz.grpc.services.QuoteQuery parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (br.ortiz.grpc.services.QuoteQuery) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.google.protobuf.StringValue symbol_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.StringValue, com.google.protobuf.StringValue.Builder, com.google.protobuf.StringValueOrBuilder> symbolBuilder_;
    /**
     * <code>.google.protobuf.StringValue symbol = 1;</code>
     * @return Whether the symbol field is set.
     */
    public boolean hasSymbol() {
      return symbolBuilder_ != null || symbol_ != null;
    }
    /**
     * <code>.google.protobuf.StringValue symbol = 1;</code>
     * @return The symbol.
     */
    public com.google.protobuf.StringValue getSymbol() {
      if (symbolBuilder_ == null) {
        return symbol_ == null ? com.google.protobuf.StringValue.getDefaultInstance() : symbol_;
      } else {
        return symbolBuilder_.getMessage();
      }
    }
    /**
     * <code>.google.protobuf.StringValue symbol = 1;</code>
     */
    public Builder setSymbol(com.google.protobuf.StringValue value) {
      if (symbolBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        symbol_ = value;
        onChanged();
      } else {
        symbolBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.StringValue symbol = 1;</code>
     */
    public Builder setSymbol(
        com.google.protobuf.StringValue.Builder builderForValue) {
      if (symbolBuilder_ == null) {
        symbol_ = builderForValue.build();
        onChanged();
      } else {
        symbolBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.google.protobuf.StringValue symbol = 1;</code>
     */
    public Builder mergeSymbol(com.google.protobuf.StringValue value) {
      if (symbolBuilder_ == null) {
        if (symbol_ != null) {
          symbol_ =
            com.google.protobuf.StringValue.newBuilder(symbol_).mergeFrom(value).buildPartial();
        } else {
          symbol_ = value;
        }
        onChanged();
      } else {
        symbolBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.StringValue symbol = 1;</code>
     */
    public Builder clearSymbol() {
      if (symbolBuilder_ == null) {
        symbol_ = null;
        onChanged();
      } else {
        symbol_ = null;
        symbolBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.google.protobuf.StringValue symbol = 1;</code>
     */
    public com.google.protobuf.StringValue.Builder getSymbolBuilder() {
      
      onChanged();
      return getSymbolFieldBuilder().getBuilder();
    }
    /**
     * <code>.google.protobuf.StringValue symbol = 1;</code>
     */
    public com.google.protobuf.StringValueOrBuilder getSymbolOrBuilder() {
      if (symbolBuilder_ != null) {
        return symbolBuilder_.getMessageOrBuilder();
      } else {
        return symbol_ == null ?
            com.google.protobuf.StringValue.getDefaultInstance() : symbol_;
      }
    }
    /**
     * <code>.google.protobuf.StringValue symbol = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.StringValue, com.google.protobuf.StringValue.Builder, com.google.protobuf.StringValueOrBuilder> 
        getSymbolFieldBuilder() {
      if (symbolBuilder_ == null) {
        symbolBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.google.protobuf.StringValue, com.google.protobuf.StringValue.Builder, com.google.protobuf.StringValueOrBuilder>(
                getSymbol(),
                getParentForChildren(),
                isClean());
        symbol_ = null;
      }
      return symbolBuilder_;
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


    // @@protoc_insertion_point(builder_scope:services.QuoteQuery)
  }

  // @@protoc_insertion_point(class_scope:services.QuoteQuery)
  private static final br.ortiz.grpc.services.QuoteQuery DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new br.ortiz.grpc.services.QuoteQuery();
  }

  public static br.ortiz.grpc.services.QuoteQuery getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<QuoteQuery>
      PARSER = new com.google.protobuf.AbstractParser<QuoteQuery>() {
    @java.lang.Override
    public QuoteQuery parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new QuoteQuery(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<QuoteQuery> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<QuoteQuery> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public br.ortiz.grpc.services.QuoteQuery getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

