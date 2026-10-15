package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableFibonacciIntegerRange;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.section.HeaderV1;

public enum HeaderV1Field implements FieldKey {
  ID(new EncodableFixedInteger<>("Id", 6, HeaderV1.ID)),
  VERSION(new EncodableFixedInteger<>("Version", 6, HeaderV1.VERSION)),
  SECTION_IDS(new EncodableFibonacciIntegerRange<>("SectionIds"));

  private final DataType<HeaderV1Field, ?> type;

  HeaderV1Field(DataType<HeaderV1Field, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<HeaderV1Field, ?> getType() {
    return type;
  }

  public static final FieldNames<HeaderV1Field> HEADER_CORE_SEGMENT_FIELD_NAMES =
      new FieldNames<>(HeaderV1Field.ID, HeaderV1Field.VERSION, HeaderV1Field.SECTION_IDS);
}
