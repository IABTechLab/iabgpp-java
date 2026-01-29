package com.iab.gpp.encoder.datatype;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.iab.gpp.encoder.error.ValidationException;

public abstract class AbstractEncodableBitStringDataType<T> extends EncodableDataType<T> {
  //this if for backwards compatibility with the newer fields
  protected boolean hardFailIfMissing = true;
  private boolean dirty = false;
  private Predicate<T> validator = null;
  protected T value;

  protected AbstractEncodableBitStringDataType(boolean hardFailIfMissing) {
    this.hardFailIfMissing = hardFailIfMissing;
  }

  public AbstractEncodableBitStringDataType<T> withValidator(Predicate<T> validator) {
    this.validator = validator;
    return this;
  }

  public final boolean hasValue() {
    return this.value != null;
  }

  public final T getValue() {
    return this.value;
  }

  public void setValue(Object value) {
    setValue(value, true);
  }
  
  @SuppressWarnings("unchecked")
  protected final void setValue(Object value, boolean dirty) {
    T v = (T) value;
    if (validator == null || validator.test(v)) {
      this.value = v;
      this.dirty = dirty;
    } else {
      if (v instanceof Collection) {
        throw new ValidationException("Invalid value '"
            + ((Collection<?>) v).stream().map(Object::toString).collect(Collectors.joining(",")) + "'");
      } else {
        throw new ValidationException("Invalid value '" + v + "'");
      }
    }

  }

  public final boolean getHardFailIfMissing() {
    return this.hardFailIfMissing;
  }

  public boolean isDirty() {
    return dirty;
  }

  public void setDirty(boolean dirty) {
    this.dirty = dirty;
  }

  public String toString() {
    return String.valueOf(value);
  }
}
