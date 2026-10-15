package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.ValidationException;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class DataType<E extends Enum<E> & FieldKey, T> {

  protected final String name;
  private final Predicate<T> validator;

  protected DataType(String name, Predicate<T> validator) {
    this.name = name;
    this.validator = validator;
  }

  public final String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name + "=" + this.getClass().getSimpleName();
  }

  protected final void validate(T v) {
    if (validator == null || validator.test(v)) {
      return;
    } else {
      if (v instanceof Collection) {
        throw new ValidationException(
            "Invalid value '"
                + ((Collection<?>) v)
                    .stream().map(Object::toString).collect(Collectors.joining(","))
                + "'");
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

  public void encode(BitString writer, Object[] values, int index, EncodableSegment<E> segment) {
    throw new UnsupportedOperationException("type does not permit bit string encoding");
  }

  public void decode(BitString reader, Object[] values, int index, EncodableSegment<E> segment) {
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

  @SuppressWarnings("unchecked")
  public final boolean isPresent(Object[] values, int index) {
    T value = (T) values[index];
    return value != null && isPresent(value);
  }

  protected boolean isPresent(T value) {
    return true;
  }

  public final void set(Object[] values, int index, Object newValue) {
    T oldValue = get(values, index);
    T effectiveValue = processValue(oldValue, newValue);
    validate(effectiveValue);
    values[index] = effectiveValue;
  }

  protected abstract T initialize();

  @SuppressWarnings("unchecked")
  protected T processValue(T oldValue, Object newValue) {
    return (T) newValue;
  }
}
