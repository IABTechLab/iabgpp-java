package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.UsDeField;

public final class UsDeCoreSegment extends AbstractBase64Segment<UsDeField> {

  public UsDeCoreSegment() {
    super(UsDeField.USDE_CORE_SEGMENT_FIELD_NAMES);
  }

}
