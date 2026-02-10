package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.HeaderV1Field;

public final class HeaderV1CoreSegment extends AbstractBase64Segment<HeaderV1Field> {

  public HeaderV1CoreSegment() {
    super(HeaderV1Field.HEADER_CORE_SEGMENT_FIELD_NAMES);
  }

}
