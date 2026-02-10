package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsNhField;

public final class UsNhGpcSegment extends AbstractBase64Segment<UsNhField> {

  public UsNhGpcSegment() {
    super(UsNhField.USNH_GPC_SEGMENT_FIELD_NAMES);
  }

}
