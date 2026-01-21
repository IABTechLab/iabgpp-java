package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.field.UsTnField;

public final class UsTnGpcSegment extends AbstractBase64Segment<UsTnField> {

  public UsTnGpcSegment() {
    super(UsTnField.USTN_GPC_SEGMENT_FIELD_NAMES, CompressedBase64UrlEncoder.getInstance());
    initialize(UsTnField.GPC_SEGMENT_TYPE, new EncodableFixedInteger(2, 1));
    initialize(UsTnField.GPC_SEGMENT_INCLUDED, new UnencodableBoolean(true));
    initialize(UsTnField.GPC, new EncodableBoolean(false));
  }

}
