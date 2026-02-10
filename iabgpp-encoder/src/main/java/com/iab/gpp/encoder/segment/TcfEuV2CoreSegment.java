package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.TraditionalBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.EncodableArrayOfFixedIntegerRanges;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableDatetime;
import com.iab.gpp.encoder.datatype.EncodableFixedBitfield;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedString;
import com.iab.gpp.encoder.datatype.EncodableOptimizedFixedRange;
import com.iab.gpp.encoder.field.TcfEuV2Field;
import com.iab.gpp.encoder.section.TcfEuV2;

public final class TcfEuV2CoreSegment extends AbstractBase64Segment<TcfEuV2Field> {

  public TcfEuV2CoreSegment() {
    super(TcfEuV2Field.TCFEUV2_CORE_SEGMENT_FIELD_NAMES);
    // NOTE: TcfEuV2.onSet records modifications
    
  }

  protected AbstractBase64UrlEncoder getBase64UrlEncoder() {
    return TraditionalBase64UrlEncoder.getInstance();
  }

}
