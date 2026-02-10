package com.iab.gpp.encoder.datatype;

import java.util.function.Predicate;
import com.iab.gpp.encoder.field.FieldKey;

public final class UnencodableCharacter<E extends Enum<E> & FieldKey> extends DataType<E, Character> {

  private final Character initial;
  
  public UnencodableCharacter(Character initial, Predicate<Character> validator) {
    this.initial = initial;
    this.validator = validator;
  }

  @Override
  protected Character initialize() {
    return initial;
  }

}
