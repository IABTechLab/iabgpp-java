package com.iab.gpp.encoder.datatype;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import com.iab.gpp.encoder.error.ValidationException;

public abstract class AbstractEncodableBitStringDataType<T> implements EncodableDataType<T> {
  //this if for backwards compatibility with the newer fields
  protected boolean hardFailIfMissing = true;
  protected Predicate<T> validator = v -> true;
  protected T value;

  protected AbstractEncodableBitStringDataType(boolean hardFailIfMissing) {
    this.hardFailIfMissing = hardFailIfMissing;
  }
  
  protected AbstractEncodableBitStringDataType(Predicate<T> validator) {
    this.validator = validator;
  }
  
  protected AbstractEncodableBitStringDataType(boolean hardFailIfMissing, Predicate<T> validator) {
    this.hardFailIfMissing = hardFailIfMissing;
    this.validator = validator;
  }

  public void setValidator(Predicate<T> validator) {
    this.validator = validator;
  }
  
  public boolean hasValue() {
    return this.value != null;
  }

  public T getValue() {
    return this.value;
  }

  @SuppressWarnings("unchecked")
  public void setValue(Object value) {
    T v = (T) value;
    if (validator.test(v)) {
      this.value = v;
    } else {
      if (v instanceof Collection) {
        throw new ValidationException("Invalid value '"
            + ((Collection<?>) v).stream().map(i -> i.toString()).collect(Collectors.joining(",")) + "'");
      } else {
        throw new ValidationException("Invalid value '" + v + "'");
      }
    }

  }

  public boolean getHardFailIfMissing() {
    return this.hardFailIfMissing;
  }

  public abstract String encode();

  public abstract void decode(String bitString);

  public abstract String substring(String bitString, int fromIndex) throws SubstringException;

}
