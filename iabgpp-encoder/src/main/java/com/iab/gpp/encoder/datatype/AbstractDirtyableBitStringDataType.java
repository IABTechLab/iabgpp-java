package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.datatype.encoder.Dirtyable;

// This class is used to handle collection types.
// It is important that we monitor the collections we return for changes.
public abstract class AbstractDirtyableBitStringDataType<T extends Dirtyable>
    extends AbstractEncodableBitStringDataType<T> {

  protected AbstractDirtyableBitStringDataType(boolean hardFailIfMissing) {
    super(hardFailIfMissing);
  }

  @Override
  public boolean isDirty() {
    return super.isDirty() || value.isDirty();
  }

  @Override
  public void setDirty(boolean dirty) {
    super.setDirty(dirty);
    value.setDirty(dirty);
  }
}
