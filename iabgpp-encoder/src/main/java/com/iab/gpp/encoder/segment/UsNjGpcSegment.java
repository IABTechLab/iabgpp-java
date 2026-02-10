package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsNjField;

public final class UsNjGpcSegment extends AbstractBase64Segment<UsNjField> {

  public UsNjGpcSegment() {
    super(UsNjField.USNJ_GPC_SEGMENT_FIELD_NAMES);
  }

}
