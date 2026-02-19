package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.FixedBitfieldEncoder;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;
import com.iab.gpp.encoder.segment.SegmentValueProvider;
import java.util.Collection;

public final class EncodableFlexibleBitfield<E extends Enum<E> & FieldKey>
    extends AbstractDirtyableBitStringDataType<E, IntegerSet> {

  private final SegmentValueProvider<E> getLengthSupplier;

  public EncodableFlexibleBitfield(String name, E key) {
    super(name, null);
    this.getLengthSupplier = new SegmentValueProvider<>(key);
  }

  @Override
  protected IntegerSet initialize() {
    return new IntegerSet();
  }

  @Override
  protected void encode(BitString builder, IntegerSet value, EncodableSegment<E> segment) {
    FixedBitfieldEncoder.encode(builder, value, this.getLengthSupplier.extract(segment));
  }

  @Override
  protected IntegerSet decode(BitString reader, EncodableSegment<E> segment) {
    return reader.readIntegerSet(getLengthSupplier.extract(segment));
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IntegerSet processValue(IntegerSet oldValue, Object newValue) {
    oldValue.clear();
    oldValue.addAll((Collection<Integer>) newValue);
    return oldValue;
  }
}
