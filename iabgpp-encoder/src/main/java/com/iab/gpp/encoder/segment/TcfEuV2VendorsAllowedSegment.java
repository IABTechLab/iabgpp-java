package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.TraditionalBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableOptimizedFixedRange;
import com.iab.gpp.encoder.field.TcfEuV2Field;

public final class TcfEuV2VendorsAllowedSegment extends AbstractBase64Segment<TcfEuV2Field> {

  public TcfEuV2VendorsAllowedSegment() {
    super(TcfEuV2Field.TCFEUV2_VENDORS_ALLOWED_SEGMENT_FIELD_NAMES, TraditionalBase64UrlEncoder.getInstance());
    fields.put(TcfEuV2Field.VENDORS_ALLOWED_SEGMENT_TYPE, new EncodableFixedInteger(3, 2));
    fields.put(TcfEuV2Field.VENDORS_ALLOWED, new EncodableOptimizedFixedRange());
  }

}
