package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.field.UsCaField;

public final class UsCaGpcSegment extends AbstractBase64Segment<UsCaField> {

  public UsCaGpcSegment() {
    super(UsCaField.USCA_GPC_SEGMENT_FIELD_NAMES);
  }

}
