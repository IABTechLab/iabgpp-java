package com.iab.gpp.encoder.datatype;

import java.util.function.Predicate;
import com.iab.gpp.encoder.error.ValidationException;

public class UnencodableCharacter implements DataType<Character> {

  private boolean dirty = false;
  private Predicate<Character> validator;
  private Character value = null;

  public UnencodableCharacter() {
    this.validator = v -> true;
  }
  
  public UnencodableCharacter(Character value) {
    this.validator = v -> true;
    setValue(value);
  }

  public UnencodableCharacter(Character value, Predicate<Character> validator) {
    this.validator = validator;
    setValue(value);
  }

  public void setValidator(Predicate<Character> validator) {
    this.validator = validator;
  }
  
  @Override
  public boolean hasValue() {
    return this.value != null;
  }

  @Override
  public Character getValue() {
    return this.value;
  }

  @Override
  public void setValue(Object value) {
    Character c = (Character)value.toString().charAt(0);
    if(validator.test(c)) {
      this.value = c;
      this.dirty = true;
    } else {
      throw new ValidationException("Invalid value '" + c + "'");
    }
  }

  @Override
  public boolean isDirty() {
    return dirty;
  }

  @Override
  public void markClean() {
    dirty = false;
  }

}
