package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsNeField;

public final class UsNeGpcSegment extends AbstractBase64Segment<UsNeField> {

  public UsNeGpcSegment() {
    super(UsNeField.USNE_GPC_SEGMENT_FIELD_NAMES);
  }

}
