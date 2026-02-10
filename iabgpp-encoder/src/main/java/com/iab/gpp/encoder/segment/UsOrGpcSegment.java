package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsOrField;

public final class UsOrGpcSegment extends AbstractBase64Segment<UsOrField> {

  public UsOrGpcSegment() {
    super(UsOrField.USOR_GPC_SEGMENT_FIELD_NAMES);
  }

}
