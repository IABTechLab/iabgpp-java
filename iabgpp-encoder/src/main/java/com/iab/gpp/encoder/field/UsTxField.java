package com.iab.gpp.encoder.field;

public enum UsTxField implements FieldKey {
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

  UsTxField(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  //@formatter:off
  public static final FieldNames<UsTxField> USTX_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsTxField.VERSION,
      UsTxField.PROCESSING_NOTICE,
      UsTxField.SALE_OPT_OUT_NOTICE,
      UsTxField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsTxField.SALE_OPT_OUT,
      UsTxField.TARGETED_ADVERTISING_OPT_OUT,
      UsTxField.SENSITIVE_DATA_PROCESSING,
      UsTxField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsTxField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsTxField.MSPA_COVERED_TRANSACTION,
      UsTxField.MSPA_OPT_OUT_OPTION_MODE,
      UsTxField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static FieldNames<UsTxField> USTX_GPC_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsTxField.GPC_SEGMENT_TYPE,
      UsTxField.GPC_SEGMENT_INCLUDED,
      UsTxField.GPC
  );
  //@formatter:on
}
