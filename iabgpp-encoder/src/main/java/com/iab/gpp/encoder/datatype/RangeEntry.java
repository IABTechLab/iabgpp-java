package com.iab.gpp.encoder.datatype;

import java.util.Collection;
import java.util.Set;

public class RangeEntry {

  private int key;
  private int type;
  private Set<Integer> ids;

  public RangeEntry(int key, int type, Set<Integer> ids) {
    super();
    this.key = key;
    this.type = type;
    this.ids = ids;
  }

  public int getKey() {
    return key;
  }

  public void setKey(int key) {
    this.key = key;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public Set<Integer> getIds() {
    return ids;
  }

  public void setIds(Collection<Integer> ids) {
    this.ids.clear();
    this.ids.addAll(ids);
  }

}
