package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsNatField;

public final class UsNatGpcSegment extends AbstractBase64Segment<UsNatField> {

  public UsNatGpcSegment() {
    super(UsNatField.USNAT_GPC_SEGMENT_FIELD_NAMES);
  }

}
