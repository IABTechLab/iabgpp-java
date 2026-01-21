package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.field.UsCaField;

public final class UsCaGpcSegment extends AbstractBase64Segment<UsCaField> {

  public UsCaGpcSegment() {
    super(UsCaField.USCA_GPC_SEGMENT_FIELD_NAMES, CompressedBase64UrlEncoder.getInstance());
    initialize(UsCaField.GPC_SEGMENT_TYPE, new EncodableFixedInteger(2, 1));
    initialize(UsCaField.GPC_SEGMENT_INCLUDED, new UnencodableBoolean(true));
    initialize(UsCaField.GPC, new EncodableBoolean(false));
  }

}
