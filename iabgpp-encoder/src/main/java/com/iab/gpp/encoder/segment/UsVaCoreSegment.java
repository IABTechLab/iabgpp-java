package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.field.UsVaField;
import com.iab.gpp.encoder.section.UsVa;

public final class UsVaCoreSegment extends AbstractBase64Segment<UsVaField> {

  public UsVaCoreSegment() {
    super(UsVaField.USVA_CORE_SEGMENT_FIELD_NAMES);
  }

}
