package com.iab.gpp.encoder.field;

import java.util.LinkedHashMap;

public final class FieldNames<E extends Enum<E> & FieldKey> {

  private final E[] keys;
  private final LinkedHashMap<FieldKey, E> map;
  private final Integer[] indices;

  @SafeVarargs
  FieldNames(E... keys) {
    this.keys = keys;
    this.map = new LinkedHashMap<>();
    this.indices = new Integer[keys[0].getClass().getEnumConstants().length];
    for (int i = 0; i < keys.length; i++) {
      E key = keys[i];
      this.map.put(key, key);
      this.indices[key.ordinal()] = i;
    }
  }
  
  public int size() {
    return keys.length;
  }
  
  public E get(int i) {
    return keys[i];
  }
  
  public Integer getIndex(E key) {
    if (key == null) {
      return null;
    }
    return indices[key.ordinal()];
  }

  public E resolveKey(FieldKey key) {
    return map.get(key);
  }

}
