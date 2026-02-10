package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsCtField;

public final class UsCtCoreSegment extends AbstractBase64Segment<UsCtField> {

  public UsCtCoreSegment() {
    super(UsCtField.USCT_CORE_SEGMENT_FIELD_NAMES);
  }

}
