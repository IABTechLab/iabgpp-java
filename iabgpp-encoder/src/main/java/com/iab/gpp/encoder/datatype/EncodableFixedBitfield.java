package com.iab.gpp.encoder.datatype;

import java.util.Collection;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.FixedBitfieldEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;

public final class EncodableFixedBitfield<E extends Enum<E> & FieldKey> extends AbstractDirtyableBitStringDataType<E, IntegerSet> {

  private final int numElements;

  public EncodableFixedBitfield(int numElements) {
    this.numElements = numElements;
  }

  @Override
  protected IntegerSet initialize() {
    return new IntegerSet(numElements);
  }

  @Override
  protected void encode(BitString builder, IntegerSet value, EncodableSegment<E> segment) {
    try {
      FixedBitfieldEncoder.encode(builder, value, this.numElements);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  @Override
  protected IntegerSet decode(BitString reader, EncodableSegment<E> segment) {
    try {
      return reader.readIntegerSet(this.numElements);
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
