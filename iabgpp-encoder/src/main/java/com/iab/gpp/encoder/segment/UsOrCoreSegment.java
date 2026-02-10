package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsOrField;

public final class UsOrCoreSegment extends AbstractBase64Segment<UsOrField> {

  public UsOrCoreSegment() {
    super(UsOrField.USOR_CORE_SEGMENT_FIELD_NAMES);
  }

}
