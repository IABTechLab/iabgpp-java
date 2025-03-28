package com.iab.gpp.encoder.field;

public class HeaderV1Field {

  public static final String ID = "Id";
  public static final String VERSION = "Version";
  public static final String SECTION_IDS = "SectionIds";

  //@formatter:off
  public static final FieldNames HEADER_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
      HeaderV1Field.ID, 
      HeaderV1Field.VERSION,
      HeaderV1Field.SECTION_IDS
  );
  //@formatter:on


}
