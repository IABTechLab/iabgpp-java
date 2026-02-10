package com.iab.gpp.encoder.datatype;

import java.util.Collection;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.FibonacciIntegerRangeEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;

public final class EncodableFibonacciIntegerRange<E extends Enum<E> & FieldKey> extends AbstractDirtyableBitStringDataType<E, IntegerSet> {

  public EncodableFibonacciIntegerRange(String name) {
    super(name, null);
  }

  @Override
  public IntegerSet initialize() {
    return new IntegerSet();
  }

  @Override
  protected void encode(BitString builder, IntegerSet value, EncodableSegment<E> segment) {
    try {
      FibonacciIntegerRangeEncoder.encode(builder, value);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  @Override
  protected IntegerSet decode(BitString reader, EncodableSegment<E> segment) {
    try {
      return FibonacciIntegerRangeEncoder.decode(reader);
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
