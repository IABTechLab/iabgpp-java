package com.iab.gpp.encoder.datatype;

import java.util.AbstractList;
import java.util.Arrays;
import com.iab.gpp.encoder.datatype.encoder.Dirtyable;

public final class FixedIntegerList extends AbstractList<Integer> implements Dirtyable {

  private boolean dirty;
  private final byte[] array;

  public FixedIntegerList(int size) {
    this.array = new byte[size];
  }

  @Override
  public int size() {
    return array.length;
  }

  @Override
  public Integer get(int index) {
    return getInt(index);
  }

  public int getInt(int index) {
    return array[index];
  }

  @Override
  public Integer set(int index, Integer value) {
    return setInt(index, value);
  }

  public int setInt(int index, int value) {
    // NOTE: int 128 is prevented since it would get turned into byte -128
    if(value < 0 || value > 128) {
      throw new IllegalArgumentException("FixedIntegerList only supports positive integers less than 128.");
    }
    int prior = array[index];
    array[index] = (byte) value;
    dirty = true;
    return prior;
  }

  @Override
  public String toString() {
    return Arrays.toString(array);
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
