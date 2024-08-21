package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UsNatField {

  public static String VERSION = "Version";
  public static String SHARING_NOTICE = "SharingNotice";
  public static String SALE_OPT_OUT_NOTICE = "SaleOptOutNotice";
  public static String SHARING_OPT_OUT_NOTICE = "SharingOptOutNotice";
  public static String TARGETED_ADVERTISING_OPT_OUT_NOTICE = "TargetedAdvertisingOptOutNotice";
  public static String SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE = "SensitiveDataProcessingOptOutNotice";
  public static String SENSITIVE_DATA_LIMIT_USE_NOTICE = "SensitiveDataLimitUseNotice";
  public static String SALE_OPT_OUT = "SaleOptOut";
  public static String SHARING_OPT_OUT = "SharingOptOut";
  public static String TARGETED_ADVERTISING_OPT_OUT = "TargetedAdvertisingOptOut";
  public static String SENSITIVE_DATA_PROCESSING = "SensitiveDataProcessing";
  public static String KNOWN_CHILD_SENSITIVE_DATA_CONSENTS = "KnownChildSensitiveDataConsents";
  public static String PERSONAL_DATA_CONSENTS = "PersonalDataConsents";
  public static String MSPA_COVERED_TRANSACTION = "MspaCoveredTransaction";
  public static String MSPA_OPT_OUT_OPTION_MODE = "MspaOptOutOptionMode";
  public static String MSPA_SERVICE_PROVIDER_MODE = "MspaServiceProviderMode";

  public static String GPC_SEGMENT_TYPE = "GpcSegmentType";
  public static String GPC_SEGMENT_INCLUDED = "GpcSegmentIncluded";
  public static String GPC = "Gpc";

  //@formatter:off
  public static List<String> USNAT_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
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
  });
  //@formatter:on
  
  //@formatter:off
  public static List<String> USNAT_GPC_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsNatField.GPC_SEGMENT_TYPE,
      UsNatField.GPC
  });
  //@formatter:on
}
