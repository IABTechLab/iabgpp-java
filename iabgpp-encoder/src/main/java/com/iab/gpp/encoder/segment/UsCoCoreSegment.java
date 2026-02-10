package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsCoField;

public final class UsCoCoreSegment extends AbstractBase64Segment<UsCoField> {

  public UsCoCoreSegment() {
    super(UsCoField.USCO_CORE_SEGMENT_FIELD_NAMES);
  }

}
