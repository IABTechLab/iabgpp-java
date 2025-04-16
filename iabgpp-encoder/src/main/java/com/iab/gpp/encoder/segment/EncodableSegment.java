package com.iab.gpp.encoder.segment;

public interface EncodableSegment {

  boolean hasField(String fieldName);

  Object getFieldValue(String fieldName);

  void setFieldValue(String fieldName, Object value);

  CharSequence encodeCharSequence();

  void decode(CharSequence encodedString);
}
