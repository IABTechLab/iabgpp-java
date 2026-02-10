package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.TcfCaV1Field;

public final class TcfCaV1CoreSegment extends AbstractBase64Segment<TcfCaV1Field> {

  public TcfCaV1CoreSegment() {
    super(TcfCaV1Field.TCFCAV1_CORE_SEGMENT_FIELD_NAMES);
  }

}
