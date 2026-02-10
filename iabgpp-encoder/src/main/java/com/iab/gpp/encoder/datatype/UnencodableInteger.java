package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.field.FieldKey;

public final class UnencodableInteger<E extends Enum<E> & FieldKey> extends DataType<E, Integer> {

  private final Integer initial;
  
  public UnencodableInteger(String name, Integer initial) {
    super(name, null);
    this.initial = initial;
  }

  @Override
  protected Integer initialize() {
    return initial;
  }

}
