package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.TcfCaV1Field;

public final class TcfCaV1PublisherPurposesSegment extends AbstractBase64Segment<TcfCaV1Field> {

  public TcfCaV1PublisherPurposesSegment() {
    super(TcfCaV1Field.TCFCAV1_PUBLISHER_PURPOSES_SEGMENT_FIELD_NAMES);
  }

}
