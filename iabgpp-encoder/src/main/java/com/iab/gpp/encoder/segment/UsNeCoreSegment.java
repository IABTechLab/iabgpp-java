package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsNeField;

public final class UsNeCoreSegment extends AbstractBase64Segment<UsNeField> {

  public UsNeCoreSegment() {
    super(UsNeField.USNE_CORE_SEGMENT_FIELD_NAMES);
  }

}
