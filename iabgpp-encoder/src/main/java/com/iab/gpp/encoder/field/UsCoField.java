package com.iab.gpp.encoder.field;

public enum UsCoField implements FieldKey {
  VERSION("Version"),
  SHARING_NOTICE("SharingNotice"),
  SALE_OPT_OUT_NOTICE("SaleOptOutNotice"),
  TARGETED_ADVERTISING_OPT_OUT_NOTICE("TargetedAdvertisingOptOutNotice"),
  SALE_OPT_OUT("SaleOptOut"),
  TARGETED_ADVERTISING_OPT_OUT("TargetedAdvertisingOptOut"),
  SENSITIVE_DATA_PROCESSING("SensitiveDataProcessing"),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS("KnownChildSensitiveDataConsents"),
  MSPA_COVERED_TRANSACTION("MspaCoveredTransaction"),
  MSPA_OPT_OUT_OPTION_MODE("MspaOptOutOptionMode"),
  MSPA_SERVICE_PROVIDER_MODE("MspaServiceProviderMode"),

  GPC_SEGMENT_TYPE("GpcSegmentType"),
  GPC_SEGMENT_INCLUDED("GpcSegmentIncluded"),
  GPC("Gpc");

  private String name;

  UsCoField(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  //@formatter:off
  public static final FieldNames<UsCoField> USCO_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsCoField.VERSION,
      UsCoField.SHARING_NOTICE,
      UsCoField.SALE_OPT_OUT_NOTICE,
      UsCoField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsCoField.SALE_OPT_OUT,
      UsCoField.TARGETED_ADVERTISING_OPT_OUT,
      UsCoField.SENSITIVE_DATA_PROCESSING,
      UsCoField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsCoField.MSPA_COVERED_TRANSACTION,
      UsCoField.MSPA_OPT_OUT_OPTION_MODE,
      UsCoField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<UsCoField> USCO_GPC_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsCoField.GPC_SEGMENT_TYPE,
      UsCoField.GPC_SEGMENT_INCLUDED,
      UsCoField.GPC
  );
  //@formatter:on
}
