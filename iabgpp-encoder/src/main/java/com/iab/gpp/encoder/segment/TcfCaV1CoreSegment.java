package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableArrayOfFixedIntegerRanges;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableDatetime;
import com.iab.gpp.encoder.datatype.EncodableFixedBitfield;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedString;
import com.iab.gpp.encoder.datatype.EncodableOptimizedFixedRange;
import com.iab.gpp.encoder.field.TcfCaV1Field;
import com.iab.gpp.encoder.section.TcfCaV1;

public final class TcfCaV1CoreSegment extends AbstractBase64Segment<TcfCaV1Field> {

  public TcfCaV1CoreSegment() {
    super(TcfCaV1Field.TCFCAV1_CORE_SEGMENT_FIELD_NAMES);
  }

}
