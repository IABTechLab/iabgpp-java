package com.iab.gpp.encoder.field;

import java.util.Map;
import com.iab.gpp.encoder.datatype.DataType;

public interface Fields<T extends DataType<?>> {

  boolean containsKey(String key);
  void put(String key, T value);
  T get(String key);
  Map<String, T> getAll();
  void reset(Fields<T> fields);

}
