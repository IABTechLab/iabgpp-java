package com.iab.gpp.encoder.section;

public interface EncodableSection<E extends Enum<E> & FieldKey> {

  int getId();

  String getName();

  int getVersion();

  boolean hasField(E fieldName);

  Object getFieldValue(E fieldName);

  void setFieldValue(E fieldName, Object value);
  
  boolean hasField(String fieldName);

  Object getFieldValue(String fieldName);

  void setFieldValue(String fieldName, Object value);

  String encode();

  CharSequence encodeCharSequence();

  void decode(CharSequence encodedString);
}
