package com.iab.gpp.encoder.datatype;

import java.util.function.Predicate;
import com.iab.gpp.encoder.error.ValidationException;

public final class UnencodableInteger implements DataType<Integer> {

  private boolean dirty;
  private Predicate<Integer> validator;
  private Integer value = null;

  public UnencodableInteger() {
    this.validator = v -> true;
  }

  public UnencodableInteger(Integer value) {
    this.validator = v -> true;
    setValue(value);
  }

  public UnencodableInteger(Integer value, Predicate<Integer> validator) {
    this.validator = validator;
    setValue(value);
  }

  public void setValidator(Predicate<Integer> validator) {
    this.validator = validator;
  }

  @Override
  public boolean hasValue() {
    return this.value != null;
  }

  @Override
  public Integer getValue() {
    return this.value;
  }

  @Override
  public void setValue(Object value) {
    Integer i = (Integer)value;
    if(validator.test(i)) {
      this.value = i;
      this.dirty = true;
    } else {
      throw new ValidationException("Invalid value '" + i + "'");
    }
  }

  @Override
  public boolean isDirty() {
    return dirty;
  }

  @Override
  public void setDirty(boolean dirty) {
    this.dirty = dirty;
  }
}
