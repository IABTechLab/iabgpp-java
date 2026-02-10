package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.field.UsCaField;
import com.iab.gpp.encoder.section.UsCa;

public final class UsCaCoreSegment extends AbstractBase64Segment<UsCaField> {

  public UsCaCoreSegment() {
    super(UsCaField.USCA_CORE_SEGMENT_FIELD_NAMES);
  }

}
