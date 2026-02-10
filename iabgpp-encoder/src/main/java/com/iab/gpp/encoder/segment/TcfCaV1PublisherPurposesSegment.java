package com.iab.gpp.encoder.segment;

import java.util.function.IntSupplier;
import com.iab.gpp.encoder.datatype.EncodableFixedBitfield;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFlexibleBitfield;
import com.iab.gpp.encoder.field.TcfCaV1Field;

public final class TcfCaV1PublisherPurposesSegment extends AbstractBase64Segment<TcfCaV1Field> {

  public TcfCaV1PublisherPurposesSegment() {
    super(TcfCaV1Field.TCFCAV1_PUBLISHER_PURPOSES_SEGMENT_FIELD_NAMES);
  }

}
