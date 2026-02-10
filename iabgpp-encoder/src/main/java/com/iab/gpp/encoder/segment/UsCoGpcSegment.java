package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsCoField;

public final class UsCoGpcSegment extends AbstractBase64Segment<UsCoField> {

  public UsCoGpcSegment() {
    super(UsCoField.USCO_GPC_SEGMENT_FIELD_NAMES);
  }

}
