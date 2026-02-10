package com.iab.gpp.encoder.datatype;

import java.util.Collection;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.FixedBitfieldEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.SegmentValueProvider;
import com.iab.gpp.encoder.segment.EncodableSegment;

public final class EncodableFlexibleBitfield<E extends Enum<E> & FieldKey> extends AbstractDirtyableBitStringDataType<E, IntegerSet> {

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
    try {
      FixedBitfieldEncoder.encode(builder, value, this.getLengthSupplier.extract(segment));
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  @Override
  protected IntegerSet decode(BitString reader, EncodableSegment<E> segment) {
    try {
      return reader.readIntegerSet(getLengthSupplier.extract(segment));
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }


  @SuppressWarnings("unchecked")
  @Override
  protected IntegerSet processValue(IntegerSet oldValue, Object newValue) {
    oldValue.clear();
    oldValue.addAll((Collection<Integer>) newValue);
    return oldValue;
  }
}
