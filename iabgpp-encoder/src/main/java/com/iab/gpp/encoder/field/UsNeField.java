package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.section.FieldKey;

public enum UsNeField implements FieldKey {
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

  UsNeField(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  //@formatter:off
  public static final FieldNames<UsNeField> USNE_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsNeField.VERSION,
      UsNeField.PROCESSING_NOTICE,
      UsNeField.SALE_OPT_OUT_NOTICE,
      UsNeField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsNeField.SALE_OPT_OUT,
      UsNeField.TARGETED_ADVERTISING_OPT_OUT,
      UsNeField.SENSITIVE_DATA_PROCESSING,
      UsNeField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsNeField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsNeField.MSPA_COVERED_TRANSACTION,
      UsNeField.MSPA_OPT_OUT_OPTION_MODE,
      UsNeField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<UsNeField> USNE_GPC_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsNeField.GPC_SEGMENT_TYPE,
      UsNeField.GPC_SEGMENT_INCLUDED,
      UsNeField.GPC
  );
  //@formatter:on
}
