package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class HeaderV1Field {

  public static String ID = "Id";
  public static String VERSION = "Version";
  public static String SECTION_IDS = "SectionIds";

  //@formatter:off
  public static List<String> HEADER_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      HeaderV1Field.ID, 
      HeaderV1Field.VERSION,
      HeaderV1Field.SECTION_IDS
  });
  //@formatter:on


}
