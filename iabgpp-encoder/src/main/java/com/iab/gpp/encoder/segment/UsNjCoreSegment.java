package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.field.UsNjField;
import com.iab.gpp.encoder.section.UsNj;

public final class UsNjCoreSegment extends AbstractBase64Segment<UsNjField> {

  public UsNjCoreSegment() {
    super(UsNjField.USNJ_CORE_SEGMENT_FIELD_NAMES);
  }

}
