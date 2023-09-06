package com.iab.gpp.encoder.datatype;

public class UnencodableCharacter implements DataType<Character> {

  private Character value = null;

  public UnencodableCharacter(Character value) {
    this.value = value;
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
    this.value = value.toString().charAt(0);
  }

}
