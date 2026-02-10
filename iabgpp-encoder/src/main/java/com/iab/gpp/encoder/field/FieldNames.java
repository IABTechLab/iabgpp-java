package com.iab.gpp.encoder.field;

import java.util.LinkedHashMap;
import com.iab.gpp.encoder.datatype.DataType;

public final class FieldNames<E extends Enum<E> & FieldKey> {

  private final LinkedHashMap<FieldKey, E> map;
  private final Integer[] indices;
  private final DataType<?,?>[] types;

  @SafeVarargs
  FieldNames(E... keys) {
    this.map = new LinkedHashMap<>();
    this.indices = new Integer[keys[0].getClass().getEnumConstants().length];
    this.types = new DataType[keys.length];
    for (int i = 0; i < keys.length; i++) {
      E key = keys[i];
      this.map.put(key, key);
      this.indices[key.ordinal()] = i;
      this.types[i] = key.getType();
    }
  }
  
  public int size() {
    return types.length;
  }

  @SuppressWarnings("unchecked")
  public DataType<E, ?> get(int i) {
    return (DataType<E, ?>) types[i];
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
