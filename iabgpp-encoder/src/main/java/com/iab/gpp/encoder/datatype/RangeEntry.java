package com.iab.gpp.encoder.datatype;

import java.util.Collection;
import java.util.Set;
import com.iab.gpp.encoder.datatype.encoder.Dirtyable;
import com.iab.gpp.encoder.datatype.encoder.IntegerBitSet;
import com.iab.gpp.encoder.datatype.encoder.IntegerSet;

public class RangeEntry implements Dirtyable {

  private boolean dirty;
  private int key;
  private int type;
  private final IntegerSet ids;

  public RangeEntry(int key, int type, Set<Integer> ids) {
    super();
    this.key = key;
    this.type = type;
    this.ids = new IntegerBitSet();
    this.ids.addAll(ids);
  }

  public int getKey() {
    return key;
  }

  public void setKey(int key) {
    this.dirty = true;
    this.key = key;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.dirty = true;
    this.type = type;
  }

  public IntegerSet getIds() {
    return ids;
  }

  public void setIds(Collection<Integer> ids) {
    this.dirty = true;
    this.ids.clear();
    this.ids.addAll(ids);
  }

  @Override
  public boolean isDirty() {
    return dirty || ids.isDirty();
  }

  @Override
  public void setDirty(boolean dirty) {
    this.dirty = dirty;
    ids.setDirty(dirty);
  }

}
