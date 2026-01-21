package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.section.FieldKey;

public enum UsTnField implements FieldKey {
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

  UsTnField(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  //@formatter:off
  public static final FieldNames<UsTnField> USTN_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsTnField.VERSION,
      UsTnField.PROCESSING_NOTICE,
      UsTnField.SALE_OPT_OUT_NOTICE,
      UsTnField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsTnField.SALE_OPT_OUT,
      UsTnField.TARGETED_ADVERTISING_OPT_OUT,
      UsTnField.SENSITIVE_DATA_PROCESSING,
      UsTnField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsTnField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsTnField.MSPA_COVERED_TRANSACTION,
      UsTnField.MSPA_OPT_OUT_OPTION_MODE,
      UsTnField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<UsTnField> USTN_GPC_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsTnField.GPC_SEGMENT_TYPE,
      UsTnField.GPC_SEGMENT_INCLUDED,
      UsTnField.GPC
  );
  //@formatter:on
}
