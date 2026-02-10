package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsFlField;

public final class UsFlCoreSegment extends AbstractBase64Segment<UsFlField> {

  public UsFlCoreSegment() {
    super(UsFlField.USFL_CORE_SEGMENT_FIELD_NAMES);
  }

}
