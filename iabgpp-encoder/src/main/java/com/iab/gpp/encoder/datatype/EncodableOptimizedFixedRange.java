package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.OptimizedFixedRangeEncoder;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;
import java.util.Collection;

public final class EncodableOptimizedFixedRange<E extends Enum<E> & FieldKey>
    extends AbstractDirtyableBitStringDataType<E, IntegerSet> {

  public EncodableOptimizedFixedRange(String name) {
    super(name, null);
  }

  @Override
  protected IntegerSet initialize() {
    return new IntegerSet();
  }

  @Override
  protected boolean isPresent(IntegerSet value) {
    return !value.isEmpty();
  }

  @Override
  protected void encode(BitString builder, IntegerSet value, EncodableSegment<E> segment) {
    OptimizedFixedRangeEncoder.encode(builder, value);
  }

  @Override
  protected IntegerSet decode(BitString reader, EncodableSegment<E> segment) {
    return OptimizedFixedRangeEncoder.decode(reader);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IntegerSet processValue(IntegerSet oldValue, Object newValue) {
    oldValue.clear();
    oldValue.addAll((Collection<Integer>) newValue);
    return oldValue;
  }
}
