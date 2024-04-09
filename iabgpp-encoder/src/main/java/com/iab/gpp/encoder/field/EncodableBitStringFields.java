package com.iab.gpp.encoder.field;

import java.util.HashMap;
import java.util.Map;
import com.iab.gpp.encoder.datatype.AbstractEncodableBitStringDataType;

public class EncodableBitStringFields implements Fields<AbstractEncodableBitStringDataType<?>> {

  private Map<String, AbstractEncodableBitStringDataType<?>> fields = new HashMap<>();
  
  public boolean containsKey(String key) {
    return this.fields.containsKey(key);
  }
  
  public void put(String key, AbstractEncodableBitStringDataType<?> value) {
    this.fields.put(key, value);
  }
  
  public AbstractEncodableBitStringDataType<?> get(String key) {
    return this.fields.get(key);
  }
  
  public Map<String, AbstractEncodableBitStringDataType<?>> getAll() {
    return new HashMap<>(this.fields);
  }

  public void reset(Fields<AbstractEncodableBitStringDataType<?>> fields) {
    this.fields.clear();
    this.fields.putAll(fields.getAll());
  }
}
