package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.field.UsTnField;
import com.iab.gpp.encoder.section.UsTn;

public final class UsTnCoreSegment extends AbstractBase64Segment<UsTnField> {

  public UsTnCoreSegment() {
    super(UsTnField.USTN_CORE_SEGMENT_FIELD_NAMES);
  }

}
