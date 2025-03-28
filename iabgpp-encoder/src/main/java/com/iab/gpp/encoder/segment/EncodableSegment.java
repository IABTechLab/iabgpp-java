package com.iab.gpp.encoder.segment;

public interface EncodableSegment {

  boolean hasField(String fieldName);

  Object getFieldValue(String fieldName);

  void setFieldValue(String fieldName, Object value);

  String encode();

  void decode(CharSequence encodedString);
}
