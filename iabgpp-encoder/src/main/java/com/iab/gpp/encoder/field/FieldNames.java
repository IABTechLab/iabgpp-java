package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class FieldNames {

  private final List<String> list;
  private final Map<String, Integer> map;

  FieldNames(String... names) {
    this.list = Collections.unmodifiableList(Arrays.asList(names));
    this.map = new LinkedHashMap<>();
    for (int i = 0; i < names.length; i++) {
      this.map.put(names[i], i);
    }
  }

  public boolean contains(String name) {
    return map.containsKey(name);
  }

  static final FieldNames of(String... names) {
    return new FieldNames(names);
  }

  public List<String> getNames() {
    return list;
  }
  public Integer convertKey(String key) {
    return map.get(key);
  }
}
