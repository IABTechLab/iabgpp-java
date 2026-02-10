package com.iab.gpp.encoder.segment;

import java.util.function.IntSupplier;
import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.TraditionalBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.EncodableFixedBitfield;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFlexibleBitfield;
import com.iab.gpp.encoder.field.TcfEuV2Field;

public final class TcfEuV2PublisherPurposesSegment extends AbstractBase64Segment<TcfEuV2Field> {

  public TcfEuV2PublisherPurposesSegment() {
    super(TcfEuV2Field.TCFEUV2_PUBLISHER_PURPOSES_SEGMENT_FIELD_NAMES);
  }

  protected AbstractBase64UrlEncoder getBase64UrlEncoder() {
    return TraditionalBase64UrlEncoder.getInstance();
  }

}
