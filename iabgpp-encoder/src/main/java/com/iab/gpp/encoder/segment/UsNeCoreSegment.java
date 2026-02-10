package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.field.UsNeField;
import com.iab.gpp.encoder.section.UsNe;

public final class UsNeCoreSegment extends AbstractBase64Segment<UsNeField> {

  public UsNeCoreSegment() {
    super(UsNeField.USNE_CORE_SEGMENT_FIELD_NAMES);
  }

}
