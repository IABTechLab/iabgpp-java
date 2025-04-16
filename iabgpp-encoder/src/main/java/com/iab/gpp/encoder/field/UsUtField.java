package com.iab.gpp.encoder.field;

public final class UsUtField {
  private UsUtField() {}

  public static final String VERSION = "Version";
  public static final String SHARING_NOTICE = "SharingNotice";
  public static final String SALE_OPT_OUT_NOTICE = "SaleOptOutNotice";
  public static final String TARGETED_ADVERTISING_OPT_OUT_NOTICE = "TargetedAdvertisingOptOutNotice";
  public static final String SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE = "SensitiveDataProcessingOptOutNotice";
  public static final String SALE_OPT_OUT = "SaleOptOut";
  public static final String TARGETED_ADVERTISING_OPT_OUT = "TargetedAdvertisingOptOut";
  public static final String SENSITIVE_DATA_PROCESSING = "SensitiveDataProcessing";
  public static final String KNOWN_CHILD_SENSITIVE_DATA_CONSENTS = "KnownChildSensitiveDataConsents";
  public static final String MSPA_COVERED_TRANSACTION = "MspaCoveredTransaction";
  public static final String MSPA_OPT_OUT_OPTION_MODE = "MspaOptOutOptionMode";
  public static final String MSPA_SERVICE_PROVIDER_MODE = "MspaServiceProviderMode";

  //@formatter:off
  public static final FieldNames USUT_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsUtField.VERSION,
      UsUtField.SHARING_NOTICE,
      UsUtField.SALE_OPT_OUT_NOTICE,
      UsUtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsUtField.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE,
      UsUtField.SALE_OPT_OUT,
      UsUtField.TARGETED_ADVERTISING_OPT_OUT,
      UsUtField.SENSITIVE_DATA_PROCESSING,
      UsUtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsUtField.MSPA_COVERED_TRANSACTION,
      UsUtField.MSPA_OPT_OUT_OPTION_MODE,
      UsUtField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on
}
