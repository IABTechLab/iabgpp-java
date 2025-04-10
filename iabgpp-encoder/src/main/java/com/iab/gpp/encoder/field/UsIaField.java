package com.iab.gpp.encoder.field;

public class UsIaField {

  public static final String VERSION = "Version";
  public static final String PROCESSING_NOTICE = "ProcessingNotice";
  public static final String SALE_OPT_OUT_NOTICE = "SaleOptOutNotice";
  public static final String TARGETED_ADVERTISING_OPT_OUT_NOTICE = "TargetedAdvertisingOptOutNotice";
  public static final String SENSITIVE_DATA_OPT_OUT_NOTICE = "SensitiveDataOptOutNotice";
  public static final String SALE_OPT_OUT = "SaleOptOut";
  public static final String TARGETED_ADVERTISING_OPT_OUT = "TargetedAdvertisingOptOut";
  public static final String SENSITIVE_DATA_PROCESSING = "SensitiveDataProcessing";
  public static final String KNOWN_CHILD_SENSITIVE_DATA_CONSENTS = "KnownChildSensitiveDataConsents";
  public static final String MSPA_COVERED_TRANSACTION = "MspaCoveredTransaction";
  public static final String MSPA_OPT_OUT_OPTION_MODE = "MspaOptOutOptionMode";
  public static final String MSPA_SERVICE_PROVIDER_MODE = "MspaServiceProviderMode";

  public static final String GPC_SEGMENT_TYPE = "GpcSegmentType";
  public static final String GPC_SEGMENT_INCLUDED = "GpcSegmentIncluded";
  public static final String GPC = "Gpc";

  //@formatter:off
  public static final FieldNames USIA_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsIaField.VERSION,
      UsIaField.PROCESSING_NOTICE,
      UsIaField.SALE_OPT_OUT_NOTICE,
      UsIaField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsIaField.SENSITIVE_DATA_OPT_OUT_NOTICE,
      UsIaField.SALE_OPT_OUT,
      UsIaField.TARGETED_ADVERTISING_OPT_OUT,
      UsIaField.SENSITIVE_DATA_PROCESSING,
      UsIaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsIaField.MSPA_COVERED_TRANSACTION,
      UsIaField.MSPA_OPT_OUT_OPTION_MODE,
      UsIaField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames USIA_GPC_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsIaField.GPC_SEGMENT_TYPE,
      UsIaField.GPC_SEGMENT_INCLUDED,
      UsIaField.GPC
  );
  //@formatter:on
}
