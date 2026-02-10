package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsMtField;

public final class UsMtGpcSegment extends AbstractBase64Segment<UsMtField> {

  public UsMtGpcSegment() {
    super(UsMtField.USMT_GPC_SEGMENT_FIELD_NAMES);
  }

}
