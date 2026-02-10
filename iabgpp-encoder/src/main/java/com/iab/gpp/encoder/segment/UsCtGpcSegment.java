package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.field.UsCtField;

public final class UsCtGpcSegment extends AbstractBase64Segment<UsCtField> {

  public UsCtGpcSegment() {
    super(UsCtField.USCT_GPC_SEGMENT_FIELD_NAMES);
  }

}
