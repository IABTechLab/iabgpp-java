package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsTnField;

public final class UsTnCoreSegment extends AbstractBase64Segment<UsTnField> {

  public UsTnCoreSegment() {
    super(UsTnField.USTN_CORE_SEGMENT_FIELD_NAMES);
  }

}
