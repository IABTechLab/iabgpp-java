package com.iab.gpp.encoder.field;

public enum UspV1Field implements FieldKey {
  VERSION("Version"),
  NOTICE("Notice"),
  OPT_OUT_SALE("OptOutSale"),
  LSPA_COVERED("LspaCovered");

  private String name;

  UspV1Field(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  //@formatter:off
  public static final FieldNames<UspV1Field> USPV1_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
      UspV1Field.VERSION,
      UspV1Field.NOTICE,
      UspV1Field.OPT_OUT_SALE,
      UspV1Field.LSPA_COVERED
  );
  //@formatter:on
}
