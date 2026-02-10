package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsMtField;

public final class UsMtCoreSegment extends AbstractBase64Segment<UsMtField> {

  public UsMtCoreSegment() {
    super(UsMtField.USMT_CORE_SEGMENT_FIELD_NAMES);
  }

}
