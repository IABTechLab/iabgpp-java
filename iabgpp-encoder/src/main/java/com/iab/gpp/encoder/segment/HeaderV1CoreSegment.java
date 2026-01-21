package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.EncodableFibonacciIntegerRange;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.field.HeaderV1Field;
import com.iab.gpp.encoder.section.HeaderV1;

public final class HeaderV1CoreSegment extends AbstractBase64Segment<HeaderV1Field> {

  public HeaderV1CoreSegment() {
    super(HeaderV1Field.HEADER_CORE_SEGMENT_FIELD_NAMES, CompressedBase64UrlEncoder.getInstance());
    fields.put(HeaderV1Field.ID, new EncodableFixedInteger(6, HeaderV1.ID));
    fields.put(HeaderV1Field.VERSION, new EncodableFixedInteger(6, HeaderV1.VERSION));
    fields.put(HeaderV1Field.SECTION_IDS, new EncodableFibonacciIntegerRange());
  }

}
