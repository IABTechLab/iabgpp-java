package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.FieldKey;

public interface EncodableSegment<E extends Enum<E> & FieldKey> {
  
  boolean hasField(E fieldName);

  Object getFieldValue(E fieldName);

  void setFieldValue(E fieldName, Object value);
  
  boolean hasField(String fieldName);

  Object getFieldValue(String fieldName);

  void setFieldValue(String fieldName, Object value);

  CharSequence encodeCharSequence();

  void decode(CharSequence encodedString);
}
