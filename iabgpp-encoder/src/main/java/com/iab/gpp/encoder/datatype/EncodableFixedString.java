package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.FixedStringEncoder;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;

public final class EncodableFixedString<E extends Enum<E> & FieldKey>
    extends AbstractEncodableBitStringDataType<E, String> {

  private final int stringLength;
  private final String initial;

  public EncodableFixedString(String name, int stringLength, String initial) {
    super(name, null);
    this.stringLength = stringLength;
    this.initial = initial;
  }

  @Override
  public String toString() {
    return name + "=String(" + stringLength + ")";
  }

  @Override
  protected String initialize() {
    return initial;
  }

  @Override
  protected void encode(BitString builder, String value, EncodableSegment<E> segment) {
    FixedStringEncoder.encode(builder, value, this.stringLength);
  }

  @Override
  protected String decode(BitString reader, EncodableSegment<E> segment) {
    return FixedStringEncoder.decode(reader, this.stringLength);
  }
}
