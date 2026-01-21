package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.field.UsCtField;

public final class UsCtGpcSegment extends AbstractBase64Segment<UsCtField> {

  public UsCtGpcSegment() {
    super(UsCtField.USCT_GPC_SEGMENT_FIELD_NAMES, CompressedBase64UrlEncoder.getInstance());
    initialize(UsCtField.GPC_SEGMENT_TYPE, new EncodableFixedInteger(2, 1));
    initialize(UsCtField.GPC_SEGMENT_INCLUDED, new UnencodableBoolean(true));
    initialize(UsCtField.GPC, new EncodableBoolean(false));
  }

}
