package com.iab.gpp.encoder.datatype;

public final class UnencodableInteger extends DataType<Integer> {

  private final Integer initial;
  
  public UnencodableInteger(Integer initial) {
    this.initial = initial;
  }

  @Override
  protected Integer initialize() {
    return initial;
  }

}
