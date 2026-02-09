package com.iab.gpp.encoder.datatype;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.ValidationException;

public abstract class DataType<T> {
  
  protected Predicate<T> validator = null;

  public DataType<T> withValidator(Predicate<T> validator) {
    this.validator = validator;
    return this;
  }
  
  protected final void validate(T v) {
    if (validator == null || validator.test(v)) {
      return;
    } else {
      if (v instanceof Collection) {
        throw new ValidationException("Invalid value '"
            + ((Collection<?>) v).stream().map(Object::toString).collect(Collectors.joining(",")) + "'");
      } else {
        throw new ValidationException("Invalid value '" + v + "'");
      }
    }
  }

  public boolean isDirty(Object[] values, int index) {
    return false;
  }

  public void setDirty(Object[] values, int index, boolean dirty) {
    // pass
  }

  public void encode(BitString writer, Object[] values, int index) {
    throw new UnsupportedOperationException("type does not permit bit string encoding");
  }

  public void decode(BitString reader, Object[] values, int index) {
    throw new UnsupportedOperationException("type does not permit bit string decoding");
  }

  @SuppressWarnings("unchecked")
  public final T get(Object[] values, int index) {
    T value = (T) values[index];
    if (value == null) {
      value = initialize();
      values[index] = value;
    }
    return value;
  }
  
  public final void set(Object[] values, int index, Object newValue) {
    T oldValue = get(values, index);
    T effectiveValue = processValue(oldValue, newValue);
    validate(effectiveValue);
    values[index] = effectiveValue;
  }
    
  protected abstract T initialize();

  // TODO: rename
  @SuppressWarnings("unchecked")
  protected T processValue(T oldValue, Object newValue) {
    return (T) newValue;
  }

}
