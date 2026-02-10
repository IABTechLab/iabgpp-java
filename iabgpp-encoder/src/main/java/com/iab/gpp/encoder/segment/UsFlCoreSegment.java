package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.field.UsFlField;
import com.iab.gpp.encoder.section.UsFl;

public final class UsFlCoreSegment extends AbstractBase64Segment<UsFlField> {

  public UsFlCoreSegment() {
    super(UsFlField.USFL_CORE_SEGMENT_FIELD_NAMES);
  }

}
