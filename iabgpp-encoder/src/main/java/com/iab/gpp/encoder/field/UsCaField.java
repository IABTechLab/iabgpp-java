package com.iab.gpp.encoder.field;

public enum UsCaField implements FieldKey {
  VERSION("Version"),
  SALE_OPT_OUT_NOTICE("SaleOptOutNotice"),
  SHARING_OPT_OUT_NOTICE("SharingOptOutNotice"),
  SENSITIVE_DATA_LIMIT_USE_NOTICE("SensitiveDataLimitUseNotice"),
  SALE_OPT_OUT("SaleOptOut"),
  SHARING_OPT_OUT("SharingOptOut"),
  SENSITIVE_DATA_PROCESSING("SensitiveDataProcessing"),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS("KnownChildSensitiveDataConsents"),
  PERSONAL_DATA_CONSENTS("PersonalDataConsents"),
  MSPA_COVERED_TRANSACTION("MspaCoveredTransaction"),
  MSPA_OPT_OUT_OPTION_MODE("MspaOptOutOptionMode"),
  MSPA_SERVICE_PROVIDER_MODE("MspaServiceProviderMode"),

  GPC_SEGMENT_TYPE("GpcSegmentType"),
  GPC_SEGMENT_INCLUDED("GpcSegmentIncluded"),
  GPC("Gpc");

  private String name;

  UsCaField(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  //@formatter:off
  public static final FieldNames<UsCaField> USCA_CORE_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsCaField.VERSION,
      UsCaField.SALE_OPT_OUT_NOTICE,
      UsCaField.SHARING_OPT_OUT_NOTICE,
      UsCaField.SENSITIVE_DATA_LIMIT_USE_NOTICE,
      UsCaField.SALE_OPT_OUT,
      UsCaField.SHARING_OPT_OUT,
      UsCaField.SENSITIVE_DATA_PROCESSING,
      UsCaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsCaField.PERSONAL_DATA_CONSENTS,
      UsCaField.MSPA_COVERED_TRANSACTION,
      UsCaField.MSPA_OPT_OUT_OPTION_MODE,
      UsCaField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<UsCaField> USCA_GPC_SEGMENT_FIELD_NAMES = new FieldNames<>(
      UsCaField.GPC_SEGMENT_TYPE,
      UsCaField.GPC_SEGMENT_INCLUDED,
      UsCaField.GPC
  );
  //@formatter:on
}
