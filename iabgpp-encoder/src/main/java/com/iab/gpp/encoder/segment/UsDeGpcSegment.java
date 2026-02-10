package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsDeField;

public final class UsDeGpcSegment extends AbstractBase64Segment<UsDeField> {

  public UsDeGpcSegment() {
    super(UsDeField.USDE_GPC_SEGMENT_FIELD_NAMES);
  }

}
