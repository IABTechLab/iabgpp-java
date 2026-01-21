package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.section.FieldKey;

public enum HeaderV1Field implements FieldKey {
  ID("Id"),
  VERSION("Version"),
  SECTION_IDS("SectionIds");

  private String name;

  HeaderV1Field(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  //@formatter:off
  public static final FieldNames<HeaderV1Field> HEADER_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
      HeaderV1Field.ID,
      HeaderV1Field.VERSION,
      HeaderV1Field.SECTION_IDS
  );
  //@formatter:on


}
