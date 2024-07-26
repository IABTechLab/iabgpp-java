package com.iab.gpp.encoder.segment;

import java.util.List;

public interface EncodableSegment {

  List<String> getFieldNames();

  boolean hasField(String fieldName);

  Object getFieldValue(String fieldName);

  void setFieldValue(String fieldName, Object value);

  String encode();

  void decode(CharSequence encodedString);

  default void validate() {};
}
