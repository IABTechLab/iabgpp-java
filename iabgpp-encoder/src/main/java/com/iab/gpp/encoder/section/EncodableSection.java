package com.iab.gpp.encoder.section;

public interface EncodableSection {

  int getId();

  String getName();

  int getVersion();

  boolean hasField(String fieldName);

  Object getFieldValue(String fieldName);

  void setFieldValue(String fieldName, Object value);

  String encode();

  void decode(CharSequence encodedString);
}
