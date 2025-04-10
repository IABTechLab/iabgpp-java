package com.iab.gpp.encoder.field;

public class UsCtField {

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

  public static final String GPC_SEGMENT_TYPE = "GpcSegmentType";
  public static final String GPC_SEGMENT_INCLUDED = "GpcSegmentIncluded";
  public static final String GPC = "Gpc";

  //@formatter:off
  public static final FieldNames USCT_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsCtField.VERSION,
      UsCtField.SHARING_NOTICE,
      UsCtField.SALE_OPT_OUT_NOTICE,
      UsCtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsCtField.SALE_OPT_OUT,
      UsCtField.TARGETED_ADVERTISING_OPT_OUT,
      UsCtField.SENSITIVE_DATA_PROCESSING,
      UsCtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsCtField.MSPA_COVERED_TRANSACTION,
      UsCtField.MSPA_OPT_OUT_OPTION_MODE,
      UsCtField.MSPA_SERVICE_PROVIDER_MODE
  );
  //@formatter:on

  //@formatter:off
  public static final FieldNames USCT_GPC_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsCtField.GPC_SEGMENT_TYPE,
      UsCtField.GPC_SEGMENT_INCLUDED,
      UsCtField.GPC
  );
  //@formatter:on
}
