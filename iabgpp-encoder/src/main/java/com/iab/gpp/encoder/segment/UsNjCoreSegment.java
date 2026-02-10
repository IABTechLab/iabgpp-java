package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsNjField;

public final class UsNjCoreSegment extends AbstractBase64Segment<UsNjField> {

  public UsNjCoreSegment() {
    super(UsNjField.USNJ_CORE_SEGMENT_FIELD_NAMES);
  }

}
