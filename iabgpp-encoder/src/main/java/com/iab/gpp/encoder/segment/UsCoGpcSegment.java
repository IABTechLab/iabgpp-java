package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.field.UsCoField;

public final class UsCoGpcSegment extends AbstractBase64Segment<UsCoField> {

  public UsCoGpcSegment() {
    super(UsCoField.USCO_GPC_SEGMENT_FIELD_NAMES, CompressedBase64UrlEncoder.getInstance());
    initialize(UsCoField.GPC_SEGMENT_TYPE, new EncodableFixedInteger(2, 1));
    initialize(UsCoField.GPC_SEGMENT_INCLUDED, new UnencodableBoolean(true));
    initialize(UsCoField.GPC, new EncodableBoolean(false));
  }

}
