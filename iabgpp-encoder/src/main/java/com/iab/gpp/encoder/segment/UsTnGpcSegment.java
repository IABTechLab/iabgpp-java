package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsTnField;

public final class UsTnGpcSegment extends AbstractBase64Segment<UsTnField> {

  public UsTnGpcSegment() {
    super(UsTnField.USTN_GPC_SEGMENT_FIELD_NAMES);
  }

}
