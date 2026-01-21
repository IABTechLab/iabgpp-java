package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.field.UsTxField;

public final class UsTxGpcSegment extends AbstractBase64Segment<UsTxField> {

  public UsTxGpcSegment() {
    super(UsTxField.USTX_GPC_SEGMENT_FIELD_NAMES, CompressedBase64UrlEncoder.getInstance());
    initialize(UsTxField.GPC_SEGMENT_TYPE, new EncodableFixedInteger(2, 1));
    initialize(UsTxField.GPC_SEGMENT_INCLUDED, new UnencodableBoolean(true));
    initialize(UsTxField.GPC, new EncodableBoolean(false));
  }
}
