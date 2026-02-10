package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.field.UsNhField;
import com.iab.gpp.encoder.section.UsNh;

public final class UsNhCoreSegment extends AbstractBase64Segment<UsNhField> {

  public UsNhCoreSegment() {
    super(UsNhField.USNH_CORE_SEGMENT_FIELD_NAMES);
  }

}
