package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.FieldKey;

public abstract class EncodableSegment<E extends Enum<E> & FieldKey> {
  
  public abstract boolean hasField(E fieldName);

  public abstract Object getFieldValue(E fieldName);

  public abstract void setFieldValue(E fieldName, Object value);
  
  public abstract boolean hasField(String fieldName);

  public abstract Object getFieldValue(String fieldName);

  public abstract void setFieldValue(String fieldName, Object value);

  public abstract CharSequence encodeCharSequence();

  public abstract void decode(CharSequence encodedString);
}
