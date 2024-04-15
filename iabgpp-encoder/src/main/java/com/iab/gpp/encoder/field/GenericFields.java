package com.iab.gpp.encoder.field;

import java.util.HashMap;
import java.util.Map;
import com.iab.gpp.encoder.datatype.DataType;

public class GenericFields implements Fields<DataType<?>> {

  private Map<String, DataType<?>> fields = new HashMap<>();
  
  public boolean containsKey(String key) {
    return this.fields.containsKey(key);
  }
  
  public void put(String key, DataType<?> value) {
    this.fields.put(key, value);
  }
  
  public DataType<?> get(String key) {
    return this.fields.get(key);
  }
  
  public Map<String, DataType<?>> getAll() {
    return new HashMap<>(this.fields);
  }

  public void reset(Fields<DataType<?>> fields) {
    this.fields.clear();
    this.fields.putAll(fields.getAll());
  }
}
