package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.field.UsOrField;
import com.iab.gpp.encoder.section.UsOr;

public final class UsOrCoreSegment extends AbstractBase64Segment<UsOrField> {

  public UsOrCoreSegment() {
    super(UsOrField.USOR_CORE_SEGMENT_FIELD_NAMES);
  }

}
