package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsVaField;

public final class UsVaCoreSegment extends AbstractBase64Segment<UsVaField> {

  public UsVaCoreSegment() {
    super(UsVaField.USVA_CORE_SEGMENT_FIELD_NAMES);
  }

}
