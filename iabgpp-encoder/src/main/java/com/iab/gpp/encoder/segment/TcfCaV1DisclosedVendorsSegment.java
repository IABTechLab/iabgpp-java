package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.TcfCaV1Field;

public final class TcfCaV1DisclosedVendorsSegment extends AbstractBase64Segment<TcfCaV1Field> {

  public TcfCaV1DisclosedVendorsSegment() {
    super(TcfCaV1Field.TCFCAV1_DISCLOSED_VENDORS_SEGMENT_FIELD_NAMES);
  }

}
