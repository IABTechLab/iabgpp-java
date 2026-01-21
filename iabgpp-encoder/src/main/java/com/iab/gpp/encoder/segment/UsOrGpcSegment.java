package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.field.UsOrField;

public final class UsOrGpcSegment extends AbstractBase64Segment<UsOrField> {

  public UsOrGpcSegment() {
    super(UsOrField.USOR_GPC_SEGMENT_FIELD_NAMES, CompressedBase64UrlEncoder.getInstance());
    initialize(UsOrField.GPC_SEGMENT_TYPE, new EncodableFixedInteger(2, 1));
    initialize(UsOrField.GPC_SEGMENT_INCLUDED, new UnencodableBoolean(true));
    initialize(UsOrField.GPC, new EncodableBoolean(false));
  }

}
