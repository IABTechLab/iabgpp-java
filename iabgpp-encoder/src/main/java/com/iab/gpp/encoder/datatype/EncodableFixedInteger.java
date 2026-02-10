package com.iab.gpp.encoder.datatype;

import java.util.function.Predicate;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;

public final class EncodableFixedInteger<E extends Enum<E> & FieldKey> extends AbstractEncodableBitStringDataType<E, Integer> {

  private final int bitStringLength;
  private final Integer initial;
  
  public EncodableFixedInteger(int bitStringLength, Integer initial, Predicate<Integer> validator) {
    this.bitStringLength = bitStringLength;
    this.initial = initial;
    this.validator = validator;
  }
  
  public EncodableFixedInteger(int bitStringLength, Integer initial) {
    this(bitStringLength, initial, null);
  }

  @Override
  protected Integer initialize() {
    return initial;
  }

  @Override
  protected void encode(BitString builder, Integer value, EncodableSegment<E> segment) {
    try {
      builder.writeInt(value, this.bitStringLength);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  @Override
  protected Integer decode(BitString reader, EncodableSegment<E> segment) {
    try {
      return IntegerCache.valueOf(reader.readInt(bitStringLength));
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }
}
