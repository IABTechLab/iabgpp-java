package com.iab.gpp.encoder.field;

public final class UsVaField {
  private UsVaField() {}

  public static final String VERSION = "Version";
  public static final String SHARING_NOTICE = "SharingNotice";
  public static final String SALE_OPT_OUT_NOTICE = "SaleOptOutNotice";
  public static final String TARGETED_ADVERTISING_OPT_OUT_NOTICE = "TargetedAdvertisingOptOutNotice";
  public static final String SALE_OPT_OUT = "SaleOptOut";
  public static final String TARGETED_ADVERTISING_OPT_OUT = "TargetedAdvertisingOptOut";
  public static final String SENSITIVE_DATA_PROCESSING = "SensitiveDataProcessing";
  public static final String KNOWN_CHILD_SENSITIVE_DATA_CONSENTS = "KnownChildSensitiveDataConsents";
  public static final String MSPA_COVERED_TRANSACTION = "MspaCoveredTransaction";
  public static final String MSPA_OPT_OUT_OPTION_MODE = "MspaOptOutOptionMode";
  public static final String MSPA_SERVICE_PROVIDER_MODE = "MspaServiceProviderMode";

  //@formatter:off
  public static final FieldNames USVA_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsVaField.VERSION,
      UsVaField.SHARING_NOTICE,
      UsVaField.SALE_OPT_OUT_NOTICE,
      UsVaField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsVaField.SALE_OPT_OUT,
      UsVaField.TARGETED_ADVERTISING_OPT_OUT,
      UsVaField.SENSITIVE_DATA_PROCESSING,
      UsVaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsVaField.MSPA_COVERED_TRANSACTION,
      UsVaField.MSPA_OPT_OUT_OPTION_MODE,
      UsVaField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on
}
