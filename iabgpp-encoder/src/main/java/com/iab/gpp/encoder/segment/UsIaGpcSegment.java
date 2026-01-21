package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.field.UsIaField;

public final class UsIaGpcSegment extends AbstractBase64Segment<UsIaField> {

  public UsIaGpcSegment() {
    super(UsIaField.USIA_GPC_SEGMENT_FIELD_NAMES, CompressedBase64UrlEncoder.getInstance());
    initialize(UsIaField.GPC_SEGMENT_TYPE, new EncodableFixedInteger(2, 1));
    initialize(UsIaField.GPC_SEGMENT_INCLUDED, new UnencodableBoolean(true));
    initialize(UsIaField.GPC, new EncodableBoolean(false));
  }

}
