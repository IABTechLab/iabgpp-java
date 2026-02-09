package com.iab.gpp.encoder.datatype;

import java.util.function.Predicate;
import com.iab.gpp.encoder.bitstring.BitString;

public abstract class AbstractEncodableBitStringDataType<T> extends DataType<T> {
  public AbstractEncodableBitStringDataType<T> withValidator(Predicate<T> validator) {
    this.validator = validator;
    return this;
  }
  
  public final void encode(BitString writer, Object[] values, int index) {
    encode(writer, get(values, index));
  }
  
  protected abstract void encode(BitString writer, T value);

  public final void decode(BitString reader, Object[] values, int index) {
    values[index] = decode(reader);
  }
  
  protected abstract T decode(BitString reader);
}
