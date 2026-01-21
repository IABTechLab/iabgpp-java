package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.field.UsNjField;

public final class UsNjGpcSegment extends AbstractBase64Segment<UsNjField> {

  public UsNjGpcSegment() {
    super(UsNjField.USNJ_GPC_SEGMENT_FIELD_NAMES, CompressedBase64UrlEncoder.getInstance());
    fields.put(UsNjField.GPC_SEGMENT_TYPE, new EncodableFixedInteger(2, 1));
    fields.put(UsNjField.GPC_SEGMENT_INCLUDED, new UnencodableBoolean(true));
    fields.put(UsNjField.GPC, new EncodableBoolean(false));
  }

}
