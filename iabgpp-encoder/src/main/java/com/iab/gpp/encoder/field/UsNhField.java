package com.iab.gpp.encoder.field;

public enum UsNhField implements FieldKey {
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

  UsNhField(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  //@formatter:off
  public static final FieldNames<UsNhField> USNH_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsNhField.VERSION,
      UsNhField.PROCESSING_NOTICE,
      UsNhField.SALE_OPT_OUT_NOTICE,
      UsNhField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsNhField.SALE_OPT_OUT,
      UsNhField.TARGETED_ADVERTISING_OPT_OUT,
      UsNhField.SENSITIVE_DATA_PROCESSING,
      UsNhField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsNhField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsNhField.MSPA_COVERED_TRANSACTION,
      UsNhField.MSPA_OPT_OUT_OPTION_MODE,
      UsNhField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<UsNhField> USNH_GPC_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsNhField.GPC_SEGMENT_TYPE,
      UsNhField.GPC_SEGMENT_INCLUDED,
      UsNhField.GPC
  );
  //@formatter:on
}
