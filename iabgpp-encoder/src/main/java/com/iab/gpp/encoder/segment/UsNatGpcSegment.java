package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.field.UsNatField;

public final class UsNatGpcSegment extends AbstractBase64Segment<UsNatField> {

  public UsNatGpcSegment() {
    super(UsNatField.USNAT_GPC_SEGMENT_FIELD_NAMES, CompressedBase64UrlEncoder.getInstance());
    initialize(UsNatField.GPC_SEGMENT_TYPE, new EncodableFixedInteger(2, 1));
    initialize(UsNatField.GPC_SEGMENT_INCLUDED, new UnencodableBoolean(true));
    initialize(UsNatField.GPC, new EncodableBoolean(false));
  }

}
