package com.iab.gpp.encoder.field;

public class UsFlField {

  public static final String VERSION = "Version";
  public static final String PROCESSING_NOTICE = "ProcessingNotice";
  public static final String SALE_OPT_OUT_NOTICE = "SaleOptOutNotice";
  public static final String TARGETED_ADVERTISING_OPT_OUT_NOTICE = "TargetedAdvertisingOptOutNotice";
  public static final String SALE_OPT_OUT = "SaleOptOut";
  public static final String TARGETED_ADVERTISING_OPT_OUT = "TargetedAdvertisingOptOut";
  public static final String SENSITIVE_DATA_PROCESSING = "SensitiveDataProcessing";
  public static final String KNOWN_CHILD_SENSITIVE_DATA_CONSENTS = "KnownChildSensitiveDataConsents";
  public static final String ADDITIONAL_DATA_PROCESSING_CONSENT = "AdditionalDataProcessingConsent";
  public static final String MSPA_COVERED_TRANSACTION = "MspaCoveredTransaction";
  public static final String MSPA_OPT_OUT_OPTION_MODE = "MspaOptOutOptionMode";
  public static final String MSPA_SERVICE_PROVIDER_MODE = "MspaServiceProviderMode";

  //@formatter:off
  public static final FieldNames USFL_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsFlField.VERSION,
      UsFlField.PROCESSING_NOTICE,
      UsFlField.SALE_OPT_OUT_NOTICE,
      UsFlField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsFlField.SALE_OPT_OUT,
      UsFlField.TARGETED_ADVERTISING_OPT_OUT,
      UsFlField.SENSITIVE_DATA_PROCESSING,
      UsFlField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsFlField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsFlField.MSPA_COVERED_TRANSACTION,
      UsFlField.MSPA_OPT_OUT_OPTION_MODE,
      UsFlField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on
}
