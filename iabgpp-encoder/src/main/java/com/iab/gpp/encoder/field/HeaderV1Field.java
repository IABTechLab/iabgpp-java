package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableFibonacciIntegerRange;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.section.HeaderV1;

public enum HeaderV1Field implements FieldKey {
  ID("Id", new EncodableFixedInteger(6, HeaderV1.ID)),
  VERSION("Version", new EncodableFixedInteger(6, HeaderV1.VERSION)),
  SECTION_IDS("SectionIds", new EncodableFibonacciIntegerRange());

  private final String name;
  private final DataType<?> type;

  HeaderV1Field(String name, DataType<?> type) {
    this.name = name;
    this.type = type;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public DataType<?> getType() {
    return type;
  }

  //@formatter:off
  public static final FieldNames<HeaderV1Field> HEADER_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      HeaderV1Field.ID,
      HeaderV1Field.VERSION,
      HeaderV1Field.SECTION_IDS
  );
  //@formatter:on


}
