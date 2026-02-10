package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsTxField;

public final class UsTxGpcSegment extends AbstractBase64Segment<UsTxField> {

  public UsTxGpcSegment() {
    super(UsTxField.USTX_GPC_SEGMENT_FIELD_NAMES);
  }
}
