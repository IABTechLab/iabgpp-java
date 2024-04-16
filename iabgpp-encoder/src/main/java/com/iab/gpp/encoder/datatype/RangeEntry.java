package com.iab.gpp.encoder.datatype;

import java.util.List;

public class RangeEntry {

  private int key;
  private int type;
  private List<Integer> ids;

  public RangeEntry(int key, int type, List<Integer> ids) {
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

  public List<Integer> getIds() {
    return ids;
  }

  public void setIds(List<Integer> ids) {
    this.ids = ids;
  }

}
