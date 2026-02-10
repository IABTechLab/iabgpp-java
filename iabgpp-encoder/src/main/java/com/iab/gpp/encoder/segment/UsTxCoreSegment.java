package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsTxField;

public final class UsTxCoreSegment extends AbstractBase64Segment<UsTxField> {

  public UsTxCoreSegment() {
    super(UsTxField.USTX_CORE_SEGMENT_FIELD_NAMES);
  }

}
