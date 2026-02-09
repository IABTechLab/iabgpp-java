package com.iab.gpp.encoder.datatype;

import java.util.function.Predicate;

public final class UnencodableCharacter extends DataType<Character> {

  private final Character initial;
  
  public UnencodableCharacter(Character initial, Predicate<Character> validator) {
    this.initial = initial;
    withValidator(validator);
  }

  @Override
  protected Character initialize() {
    return initial;
  }

}
