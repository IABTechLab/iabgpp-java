package com.iab.gpp.encoder.datatype;

import java.util.Collection;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerRangeEncoder;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;

public final class EncodableFixedIntegerRange<E extends Enum<E> & FieldKey> extends AbstractDirtyableBitStringDataType<E, IntegerSet> {

  public EncodableFixedIntegerRange(String name) {
    super(name, null);
  }

  @Override
  protected IntegerSet initialize() {
    return new IntegerSet();
  }

  @Override
  protected void encode(BitString builder, IntegerSet value, EncodableSegment<E> segment) {
    FixedIntegerRangeEncoder.encode(builder, value);
  }

  @Override
  protected IntegerSet decode(BitString reader, EncodableSegment<E> segment) {
    return FixedIntegerRangeEncoder.decode(reader);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IntegerSet processValue(IntegerSet oldValue, Object newValue) {
    oldValue.clear();
    oldValue.addAll((Collection<Integer>) newValue);
    return oldValue;
  }
}
