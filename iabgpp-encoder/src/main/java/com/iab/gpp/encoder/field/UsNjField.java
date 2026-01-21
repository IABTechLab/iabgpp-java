package com.iab.gpp.encoder.field;

public enum UsNjField implements FieldKey {
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
  MSPA_SERVICE_PROVIDER_MODE("MspaServiceProviderMode"),

  GPC_SEGMENT_TYPE("GpcSegmentType"),
  GPC_SEGMENT_INCLUDED("GpcSegmentIncluded"),
  GPC("Gpc");

  private String name;

  UsNjField(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  //@formatter:off
  public static final FieldNames<UsNjField> USNJ_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsNjField.VERSION,
      UsNjField.PROCESSING_NOTICE,
      UsNjField.SALE_OPT_OUT_NOTICE,
      UsNjField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsNjField.SALE_OPT_OUT,
      UsNjField.TARGETED_ADVERTISING_OPT_OUT,
      UsNjField.SENSITIVE_DATA_PROCESSING,
      UsNjField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsNjField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsNjField.MSPA_COVERED_TRANSACTION,
      UsNjField.MSPA_OPT_OUT_OPTION_MODE,
      UsNjField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<UsNjField> USNJ_GPC_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsNjField.GPC_SEGMENT_TYPE,
      UsNjField.GPC_SEGMENT_INCLUDED,
      UsNjField.GPC
  );
  //@formatter:on
}
