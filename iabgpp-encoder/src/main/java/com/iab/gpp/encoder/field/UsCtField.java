package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UsCtField {

  public static String VERSION = "Version";
  public static String SHARING_NOTICE = "SharingNotice";
  public static String SALE_OPT_OUT_NOTICE = "SaleOptOutNotice";
  public static String TARGETED_ADVERTISING_OPT_OUT_NOTICE = "TargetedAdvertisingOptOutNotice";
  public static String SALE_OPT_OUT = "SaleOptOut";
  public static String TARGETED_ADVERTISING_OPT_OUT = "TargetedAdvertisingOptOut";
  public static String SENSITIVE_DATA_PROCESSING = "SensitiveDataProcessing";
  public static String KNOWN_CHILD_SENSITIVE_DATA_CONSENTS = "KnownChildSensitiveDataConsents";
  public static String MSPA_COVERED_TRANSACTION = "MspaCoveredTransaction";
  public static String MSPA_OPT_OUT_OPTION_MODE = "MspaOptOutOptionMode";
  public static String MSPA_SERVICE_PROVIDER_MODE = "MspaServiceProviderMode";

  public static String GPC_SEGMENT_TYPE = "GpcSegmentType";
  public static String GPC_SEGMENT_INCLUDED = "GpcSegmentIncluded";
  public static String GPC = "Gpc";
  
  //@formatter:off
  public static List<String> USCT_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
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
  });
  //@formatter:on
  
  //@formatter:off
  public static List<String> USCT_GPC_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsCtField.GPC_SEGMENT_TYPE,
      UsCtField.GPC
  });
  //@formatter:on
}
