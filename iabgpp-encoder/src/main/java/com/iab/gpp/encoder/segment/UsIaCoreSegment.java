package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsIaField;

public final class UsIaCoreSegment extends AbstractBase64Segment<UsIaField> {

  public UsIaCoreSegment() {
    super(UsIaField.USIA_CORE_SEGMENT_FIELD_NAMES);
  }

}
