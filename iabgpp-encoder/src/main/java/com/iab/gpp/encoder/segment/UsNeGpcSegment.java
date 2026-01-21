package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.field.UsNeField;

public final class UsNeGpcSegment extends AbstractBase64Segment<UsNeField> {

  public UsNeGpcSegment() {
    super(UsNeField.USNE_GPC_SEGMENT_FIELD_NAMES, CompressedBase64UrlEncoder.getInstance());
    fields.put(UsNeField.GPC_SEGMENT_TYPE, new EncodableFixedInteger(2, 1));
    fields.put(UsNeField.GPC_SEGMENT_INCLUDED, new UnencodableBoolean(true));
    fields.put(UsNeField.GPC, new EncodableBoolean(false));
  }

}
