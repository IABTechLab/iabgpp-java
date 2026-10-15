package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.DatetimeEncoder;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;
import java.time.Instant;

public final class EncodableDatetime<E extends Enum<E> & FieldKey>
    extends AbstractEncodableBitStringDataType<E, Instant> {

  public EncodableDatetime(String name) {
    super(name, null);
  }

  @Override
  protected Instant initialize() {
    return Instant.EPOCH;
  }

  @Override
  protected void encode(BitString builder, Instant value, EncodableSegment<E> segment) {
    DatetimeEncoder.encode(builder, value);
  }

  @Override
  protected Instant decode(BitString reader, EncodableSegment<E> segment) {
    return DatetimeEncoder.decode(reader);
  }
}
