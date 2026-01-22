package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.datatype.encoder.Dirtyable;
import com.iab.gpp.encoder.field.AbstractEncodable;
import com.iab.gpp.encoder.field.FieldKey;

public abstract class EncodableSection<E extends Enum<E> & FieldKey> extends AbstractEncodable implements Dirtyable {

  public abstract int getId();

  public abstract String getName();

  public abstract int getVersion();

  public abstract boolean hasField(E fieldName);

  public abstract Object getFieldValue(E fieldName);

  public abstract void setFieldValue(E fieldName, Object value);
  
  public abstract boolean hasField(String fieldName);

  public abstract Object getFieldValue(String fieldName);

  public abstract void setFieldValue(String fieldName, Object value);

}
