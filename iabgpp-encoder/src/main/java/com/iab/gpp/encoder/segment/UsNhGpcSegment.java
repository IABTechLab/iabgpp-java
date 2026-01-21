package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.field.UsNhField;

public final class UsNhGpcSegment extends AbstractBase64Segment<UsNhField> {

  public UsNhGpcSegment() {
    super(UsNhField.USNH_GPC_SEGMENT_FIELD_NAMES, CompressedBase64UrlEncoder.getInstance());
    initialize(UsNhField.GPC_SEGMENT_TYPE, new EncodableFixedInteger(2, 1));
    initialize(UsNhField.GPC_SEGMENT_INCLUDED, new UnencodableBoolean(true));
    initialize(UsNhField.GPC, new EncodableBoolean(false));
  }

}
