package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.field.UsUtField;
import com.iab.gpp.encoder.section.UsUt;

public final  class UsUtCoreSegment extends AbstractBase64Segment<UsUtField> {

  public UsUtCoreSegment() {
    super(UsUtField.USUT_CORE_SEGMENT_FIELD_NAMES);
  }

}
