package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.field.UsDeField;

public final class UsDeGpcSegment extends AbstractBase64Segment<UsDeField> {

  public UsDeGpcSegment() {
    super(UsDeField.USDE_GPC_SEGMENT_FIELD_NAMES);
    initialize(UsDeField.GPC_SEGMENT_TYPE, new EncodableFixedInteger(2, 1));
    initialize(UsDeField.GPC_SEGMENT_INCLUDED, new UnencodableBoolean(true));
    initialize(UsDeField.GPC, new EncodableBoolean(false));
  }

}
