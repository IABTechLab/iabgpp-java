package com.iab.gpp.encoder.field;

public class UsNjField {

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

  public static final String GPC_SEGMENT_TYPE = "GpcSegmentType";
  public static final String GPC_SEGMENT_INCLUDED = "GpcSegmentIncluded";
  public static final String GPC = "Gpc";

  //@formatter:off
  public static final FieldNames USNJ_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsNjField.VERSION,
      UsNjField.PROCESSING_NOTICE,
      UsNjField.SALE_OPT_OUT_NOTICE,
      UsNjField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsNjField.SALE_OPT_OUT,
      UsNjField.TARGETED_ADVERTISING_OPT_OUT,
      UsNjField.SENSITIVE_DATA_PROCESSING,
      UsNjField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsNjField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsNjField.MSPA_COVERED_TRANSACTION,
      UsNjField.MSPA_OPT_OUT_OPTION_MODE,
      UsNjField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames USNJ_GPC_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsNjField.GPC_SEGMENT_TYPE,
      UsNjField.GPC_SEGMENT_INCLUDED,
      UsNjField.GPC
  );
  //@formatter:on
}
