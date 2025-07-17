package com.iab.gpp.encoder.field;

public final class UspV1Field {
  private UspV1Field() {}

  public static final String VERSION = "Version";
  public static final String NOTICE = "Notice";
  public static final String OPT_OUT_SALE = "OptOutSale";
  public static final String LSPA_COVERED = "LspaCovered";

  //@formatter:off
  public static final FieldNames USPV1_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
      UspV1Field.VERSION,
      UspV1Field.NOTICE,
      UspV1Field.OPT_OUT_SALE,
      UspV1Field.LSPA_COVERED
  );
  //@formatter:on
}
