package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.section.FieldKey;

public enum UsNatField implements FieldKey {
  VERSION("Version"),
  SHARING_NOTICE("SharingNotice"),
  SALE_OPT_OUT_NOTICE("SaleOptOutNotice"),
  SHARING_OPT_OUT_NOTICE("SharingOptOutNotice"),
  TARGETED_ADVERTISING_OPT_OUT_NOTICE("TargetedAdvertisingOptOutNotice"),
  SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE("SensitiveDataProcessingOptOutNotice"),
  SENSITIVE_DATA_LIMIT_USE_NOTICE("SensitiveDataLimitUseNotice"),
  SALE_OPT_OUT("SaleOptOut"),
  SHARING_OPT_OUT("SharingOptOut"),
  TARGETED_ADVERTISING_OPT_OUT("TargetedAdvertisingOptOut"),
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

  UsNatField(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  //@formatter:off
  public static final FieldNames<UsNatField> USNAT_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsNatField.VERSION,
      UsNatField.SHARING_NOTICE,
      UsNatField.SALE_OPT_OUT_NOTICE,
      UsNatField.SHARING_OPT_OUT_NOTICE,
      UsNatField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsNatField.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE,
      UsNatField.SENSITIVE_DATA_LIMIT_USE_NOTICE,
      UsNatField.SALE_OPT_OUT,
      UsNatField.SHARING_OPT_OUT,
      UsNatField.TARGETED_ADVERTISING_OPT_OUT,
      UsNatField.SENSITIVE_DATA_PROCESSING,
      UsNatField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsNatField.PERSONAL_DATA_CONSENTS,
      UsNatField.MSPA_COVERED_TRANSACTION,
      UsNatField.MSPA_OPT_OUT_OPTION_MODE,
      UsNatField.MSPA_SERVICE_PROVIDER_MODE
   );
  //@formatter:on

  //@formatter:off
  public static final FieldNames<UsNatField> USNAT_GPC_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsNatField.GPC_SEGMENT_TYPE,
      UsNatField.GPC_SEGMENT_INCLUDED,
      UsNatField.GPC
  );
  //@formatter:on
}
