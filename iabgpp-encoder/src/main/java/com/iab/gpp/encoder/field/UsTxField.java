package com.iab.gpp.encoder.field;

public class UsTxField {

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
  public static final FieldNames USTX_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
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
  public static FieldNames USTX_GPC_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsTxField.GPC_SEGMENT_TYPE,
      UsTxField.GPC_SEGMENT_INCLUDED,
      UsTxField.GPC
  );
  //@formatter:on
}
