package com.iab.gpp.encoder.field;

public class UsNatField {

  public static final String VERSION = "Version";
  public static final String SHARING_NOTICE = "SharingNotice";
  public static final String SALE_OPT_OUT_NOTICE = "SaleOptOutNotice";
  public static final String SHARING_OPT_OUT_NOTICE = "SharingOptOutNotice";
  public static final String TARGETED_ADVERTISING_OPT_OUT_NOTICE = "TargetedAdvertisingOptOutNotice";
  public static final String SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE = "SensitiveDataProcessingOptOutNotice";
  public static final String SENSITIVE_DATA_LIMIT_USE_NOTICE = "SensitiveDataLimitUseNotice";
  public static final String SALE_OPT_OUT = "SaleOptOut";
  public static final String SHARING_OPT_OUT = "SharingOptOut";
  public static final String TARGETED_ADVERTISING_OPT_OUT = "TargetedAdvertisingOptOut";
  public static final String SENSITIVE_DATA_PROCESSING = "SensitiveDataProcessing";
  public static final String KNOWN_CHILD_SENSITIVE_DATA_CONSENTS = "KnownChildSensitiveDataConsents";
  public static final String PERSONAL_DATA_CONSENTS = "PersonalDataConsents";
  public static final String MSPA_COVERED_TRANSACTION = "MspaCoveredTransaction";
  public static final String MSPA_OPT_OUT_OPTION_MODE = "MspaOptOutOptionMode";
  public static final String MSPA_SERVICE_PROVIDER_MODE = "MspaServiceProviderMode";

  public static final String GPC_SEGMENT_TYPE = "GpcSegmentType";
  public static final String GPC_SEGMENT_INCLUDED = "GpcSegmentIncluded";
  public static final String GPC = "Gpc";

  //@formatter:off
  public static final FieldNames USNAT_CORE_SEGMENT_FIELD_NAMES = FieldNames.of(
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
  public static final FieldNames USNAT_GPC_SEGMENT_FIELD_NAMES = FieldNames.of(
      UsNatField.GPC_SEGMENT_TYPE,
      UsNatField.GPC_SEGMENT_INCLUDED,
      UsNatField.GPC
  );
  //@formatter:on
}
