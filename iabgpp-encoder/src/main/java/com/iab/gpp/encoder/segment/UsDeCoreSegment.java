package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.field.UsDeField;
import com.iab.gpp.encoder.section.UsDe;

public final class UsDeCoreSegment extends AbstractBase64Segment<UsDeField> {

  public UsDeCoreSegment() {
    super(UsDeField.USDE_CORE_SEGMENT_FIELD_NAMES);
  }

}
