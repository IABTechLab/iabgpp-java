package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;
import java.util.function.Predicate;

public final class EncodableFixedInteger<E extends Enum<E> & FieldKey>
    extends AbstractEncodableBitStringDataType<E, Integer> {

  private final int bitStringLength;
  private final Integer initial;

  public EncodableFixedInteger(
      String name, int bitStringLength, Integer initial, Predicate<Integer> validator) {
    super(name, validator);
    this.bitStringLength = bitStringLength;
    this.initial = initial;
  }

  public EncodableFixedInteger(String name, int bitStringLength, Integer initial) {
    this(name, bitStringLength, initial, null);
  }

  @Override
  public String toString() {
    return name + "=Int(" + bitStringLength + ")";
  }

  @Override
  protected Integer initialize() {
    return initial;
  }

  @Override
  protected void encode(BitString builder, Integer value, EncodableSegment<E> segment) {
    builder.writeInt(value, this.bitStringLength);
  }

  @Override
  protected Integer decode(BitString reader, EncodableSegment<E> segment) {
    return IntegerCache.valueOf(reader.readInt(bitStringLength));
  }
}
