package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.TraditionalBase64UrlEncoder;
import com.iab.gpp.encoder.field.TcfEuV2Field;

public final class TcfEuV2CoreSegment extends AbstractBase64Segment<TcfEuV2Field> {

  public TcfEuV2CoreSegment() {
    super(TcfEuV2Field.TCFEUV2_CORE_SEGMENT_FIELD_NAMES);
    // NOTE: TcfEuV2.onSet records modifications
    
  }

  protected AbstractBase64UrlEncoder getBase64UrlEncoder() {
    return TraditionalBase64UrlEncoder.getInstance();
  }

}
