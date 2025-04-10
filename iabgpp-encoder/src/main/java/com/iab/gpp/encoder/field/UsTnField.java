package com.iab.gpp.encoder.field;

public final class UsTnField {
  private UsTnField() {}

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
  public static final FieldNames USTN_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
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
  public static final FieldNames USTN_GPC_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsTnField.GPC_SEGMENT_TYPE,
      UsTnField.GPC_SEGMENT_INCLUDED,
      UsTnField.GPC
  );
  //@formatter:on
}
