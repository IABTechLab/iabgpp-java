package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsCaField;

public final class UsCaCoreSegment extends AbstractBase64Segment<UsCaField> {

  public UsCaCoreSegment() {
    super(UsCaField.USCA_CORE_SEGMENT_FIELD_NAMES);
  }

}
