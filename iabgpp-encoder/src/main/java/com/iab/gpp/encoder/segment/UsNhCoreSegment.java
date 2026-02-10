package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsNhField;

public final class UsNhCoreSegment extends AbstractBase64Segment<UsNhField> {

  public UsNhCoreSegment() {
    super(UsNhField.USNH_CORE_SEGMENT_FIELD_NAMES);
  }

}
