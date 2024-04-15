package com.iab.gpp.encoder.datatype;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import com.iab.gpp.encoder.error.ValidationException;

public abstract class AbstractEncodableBitStringDataType<T> implements EncodableDataType<T> {
  protected Predicate<T> validator;
  protected T value;

  protected AbstractEncodableBitStringDataType() {
    
  }
  
  public AbstractEncodableBitStringDataType<T> withValidator(Predicate<T> validator) {
    this.validator = validator;
    return this;
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
    if (validator == null || validator.test(v)) {
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

  public abstract String substring(String str, int fromIndex);
}
