package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.field.UsTxField;
import com.iab.gpp.encoder.section.UsTx;

public final class UsTxCoreSegment extends AbstractBase64Segment<UsTxField> {

  public UsTxCoreSegment() {
    super(UsTxField.USTX_CORE_SEGMENT_FIELD_NAMES);
  }

}
