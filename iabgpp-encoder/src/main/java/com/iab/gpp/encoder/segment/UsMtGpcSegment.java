package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.field.UsMtField;

public final class UsMtGpcSegment extends AbstractBase64Segment<UsMtField> {

  public UsMtGpcSegment() {
    super(UsMtField.USMT_GPC_SEGMENT_FIELD_NAMES);
    initialize(UsMtField.GPC_SEGMENT_TYPE, new EncodableFixedInteger(2, 1));
    initialize(UsMtField.GPC_SEGMENT_INCLUDED, new UnencodableBoolean(true));
    initialize(UsMtField.GPC, new EncodableBoolean(false));
  }

}
