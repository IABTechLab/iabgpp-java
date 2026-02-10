package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.field.UsCoField;
import com.iab.gpp.encoder.section.UsCo;

public final class UsCoCoreSegment extends AbstractBase64Segment<UsCoField> {

  public UsCoCoreSegment() {
    super(UsCoField.USCO_CORE_SEGMENT_FIELD_NAMES);
  }

}
