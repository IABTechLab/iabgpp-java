package com.iab.gpp.encoder.datatype;

public class UnencodableInteger implements DataType<Integer> {

  private Integer value = null;

  public UnencodableInteger(Integer value) {
    this.value = value;
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
    this.value = (Integer)value;
  }

}
