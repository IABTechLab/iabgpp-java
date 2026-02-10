package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.field.UsIaField;

public final class UsIaGpcSegment extends AbstractBase64Segment<UsIaField> {

  public UsIaGpcSegment() {
    super(UsIaField.USIA_GPC_SEGMENT_FIELD_NAMES);
  }

}
