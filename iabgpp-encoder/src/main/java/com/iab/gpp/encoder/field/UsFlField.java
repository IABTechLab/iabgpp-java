package com.iab.gpp.encoder.field;

public enum UsFlField implements FieldKey {
  VERSION("Version"),
  PROCESSING_NOTICE("ProcessingNotice"),
  SALE_OPT_OUT_NOTICE("SaleOptOutNotice"),
  TARGETED_ADVERTISING_OPT_OUT_NOTICE("TargetedAdvertisingOptOutNotice"),
  SALE_OPT_OUT("SaleOptOut"),
  TARGETED_ADVERTISING_OPT_OUT("TargetedAdvertisingOptOut"),
  SENSITIVE_DATA_PROCESSING("SensitiveDataProcessing"),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS("KnownChildSensitiveDataConsents"),
  ADDITIONAL_DATA_PROCESSING_CONSENT("AdditionalDataProcessingConsent"),
  MSPA_COVERED_TRANSACTION("MspaCoveredTransaction"),
  MSPA_OPT_OUT_OPTION_MODE("MspaOptOutOptionMode"),
  MSPA_SERVICE_PROVIDER_MODE("MspaServiceProviderMode");

  private String name;

  UsFlField(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  //@formatter:off
  public static final FieldNames<UsFlField> USFL_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsFlField.VERSION,
      UsFlField.PROCESSING_NOTICE,
      UsFlField.SALE_OPT_OUT_NOTICE,
      UsFlField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsFlField.SALE_OPT_OUT,
      UsFlField.TARGETED_ADVERTISING_OPT_OUT,
      UsFlField.SENSITIVE_DATA_PROCESSING,
      UsFlField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsFlField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsFlField.MSPA_COVERED_TRANSACTION,
      UsFlField.MSPA_OPT_OUT_OPTION_MODE,
      UsFlField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on
}
