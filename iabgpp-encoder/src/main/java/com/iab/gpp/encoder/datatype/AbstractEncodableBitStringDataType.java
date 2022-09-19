package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public abstract class AbstractEncodableBitStringDataType<T> {
  protected T value;

  public AbstractEncodableBitStringDataType() {
    
  }
  
  public AbstractEncodableBitStringDataType(T value) {
    this.value = value;
  }

  public boolean hasValue() {
    return this.value != null;
  }

  public T getValue() {
    return this.value;
  }

  @SuppressWarnings("unchecked")
  public void setValue(Object value) {
    this.value = (T)value;
  }

  public abstract String encode() throws EncodingException;
  public abstract void decode(String bitString) throws DecodingException;
  public abstract String substring(String bitString, int fromIndex) throws DecodingException;
}
