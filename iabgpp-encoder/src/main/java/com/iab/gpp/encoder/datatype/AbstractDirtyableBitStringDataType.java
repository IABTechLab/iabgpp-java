package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.field.FieldKey;
import java.util.function.Predicate;

// This class is used to handle collection types.
// It is important that we monitor the collections we return for changes.
public abstract class AbstractDirtyableBitStringDataType<
        E extends Enum<E> & FieldKey, T extends Dirtyable>
    extends AbstractEncodableBitStringDataType<E, T> {

  protected AbstractDirtyableBitStringDataType(String name, Predicate<T> validator) {
    super(name, validator);
  }

  @Override
  public boolean isDirty(Object[] values, int index) {
    T value = get(values, index);
    return (value != null && value.isDirty());
  }

  @Override
  protected abstract boolean isPresent(T value);

  @SuppressWarnings("unchecked")
  @Override
  public void setDirty(Object[] values, int index, boolean dirty) {
    T value = (T) values[index];
    if (value != null) {
      value.setDirty(dirty);
    }
  }
}
