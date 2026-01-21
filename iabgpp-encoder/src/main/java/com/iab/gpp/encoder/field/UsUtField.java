package com.iab.gpp.encoder.field;

public enum UsUtField implements FieldKey {
  VERSION("Version"),
  SHARING_NOTICE("SharingNotice"),
  SALE_OPT_OUT_NOTICE("SaleOptOutNotice"),
  TARGETED_ADVERTISING_OPT_OUT_NOTICE("TargetedAdvertisingOptOutNotice"),
  SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE("SensitiveDataProcessingOptOutNotice"),
  SALE_OPT_OUT("SaleOptOut"),
  TARGETED_ADVERTISING_OPT_OUT("TargetedAdvertisingOptOut"),
  SENSITIVE_DATA_PROCESSING("SensitiveDataProcessing"),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS("KnownChildSensitiveDataConsents"),
  MSPA_COVERED_TRANSACTION("MspaCoveredTransaction"),
  MSPA_OPT_OUT_OPTION_MODE("MspaOptOutOptionMode"),
  MSPA_SERVICE_PROVIDER_MODE("MspaServiceProviderMode");

  private String name;

  UsUtField(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  //@formatter:off
  public static final FieldNames<UsUtField> USUT_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsUtField.VERSION,
      UsUtField.SHARING_NOTICE,
      UsUtField.SALE_OPT_OUT_NOTICE,
      UsUtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsUtField.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE,
      UsUtField.SALE_OPT_OUT,
      UsUtField.TARGETED_ADVERTISING_OPT_OUT,
      UsUtField.SENSITIVE_DATA_PROCESSING,
      UsUtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsUtField.MSPA_COVERED_TRANSACTION,
      UsUtField.MSPA_OPT_OUT_OPTION_MODE,
      UsUtField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on
}
