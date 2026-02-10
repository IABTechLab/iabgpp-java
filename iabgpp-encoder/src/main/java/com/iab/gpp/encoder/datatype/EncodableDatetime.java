package com.iab.gpp.encoder.datatype;

import java.time.Instant;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.DatetimeEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;

public final class EncodableDatetime<E extends Enum<E> & FieldKey> extends AbstractEncodableBitStringDataType<E, Instant> {

  public EncodableDatetime(String name) {
    super(name, null);
  }

  @Override
  protected Instant initialize() {
    return Instant.EPOCH;
  }

  @Override
  protected void encode(BitString builder, Instant value, EncodableSegment<E> segment) {
    try {
      DatetimeEncoder.encode(builder, value);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  @Override
  protected Instant decode(BitString reader, EncodableSegment<E> segment) {
    try {
      return DatetimeEncoder.decode(reader);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }
}
