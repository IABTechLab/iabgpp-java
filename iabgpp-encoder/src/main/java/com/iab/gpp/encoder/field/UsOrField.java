package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.section.FieldKey;

public enum UsOrField implements FieldKey {
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

  UsOrField(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  //@formatter:off
  public static final FieldNames<UsOrField> USOR_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsOrField.VERSION,
      UsOrField.PROCESSING_NOTICE,
      UsOrField.SALE_OPT_OUT_NOTICE,
      UsOrField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsOrField.SALE_OPT_OUT,
      UsOrField.TARGETED_ADVERTISING_OPT_OUT,
      UsOrField.SENSITIVE_DATA_PROCESSING,
      UsOrField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsOrField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsOrField.MSPA_COVERED_TRANSACTION,
      UsOrField.MSPA_OPT_OUT_OPTION_MODE,
      UsOrField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<UsOrField> USOR_GPC_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsOrField.GPC_SEGMENT_TYPE,
      UsOrField.GPC_SEGMENT_INCLUDED,
      UsOrField.GPC
  );
  //@formatter:on
}
