package com.iab.gpp.encoder.field;

import java.util.List;
import com.iab.gpp.encoder.datatype.DataType;

public abstract class Fields<T extends DataType<?>> {

  private final FieldNames fieldNames;
  private final Object[] values;

  protected Fields(FieldNames fieldNames) {
    this.fieldNames = fieldNames;
    this.values = new Object[fieldNames.getNames().size()];
  }
  
  public List<String> getNames() {
    return fieldNames.getNames();
  }
  
  public boolean containsKey(String key) {
    Integer index = fieldNames.convertKey(key);
    return index != null && values[index] != null;
  }
  
  public void put(String key, T value) {
    Integer index = fieldNames.convertKey(key);
    if (index != null) {
      values[index] = value;
    }
  }
  
  @SuppressWarnings("unchecked")
  public T get(int index) {
    return (T) values[index];
  }

  public T get(String key) {
    Integer index = fieldNames.convertKey(key);
    if (index != null) {
      return get(index);
    }
    return null;
  }

  public void reset(Fields<T> fields) {
    for (String name : fieldNames.getNames()) {
      put(name, fields.get(name));
    }
  }
}
