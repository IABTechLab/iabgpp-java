package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UspV1Field {

  public static String VERSION = "Version";
  public static String NOTICE = "Notice";
  public static String OPT_OUT_SALE = "OptOutSale";
  public static String LSPA_COVERED = "LspaCovered";

  //@formatter:off
  public static List<String> USPV1_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UspV1Field.VERSION,
      UspV1Field.NOTICE,
      UspV1Field.OPT_OUT_SALE,
      UspV1Field.LSPA_COVERED
  });
  //@formatter:on
}
