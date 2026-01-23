package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.section.AbstractEncodable;

public abstract class EncodableSegment<E extends Enum<E> & FieldKey> extends AbstractEncodable {
  public abstract E resolveKey(String fieldName);

  public abstract DataType<?> getField(E fieldName);
  
  public abstract Object getFieldValue(E fieldName);

  public abstract void setFieldValue(E fieldName, Object value);
}
