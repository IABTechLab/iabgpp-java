package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UsUtField {

  public static String VERSION = "Version";
  public static String SHARING_NOTICE = "SharingNotice";
  public static String SALE_OPT_OUT_NOTICE = "SaleOptOutNotice";
  public static String TARGETED_ADVERTISING_OPT_OUT_NOTICE = "TargetedAdvertisingOptOutNotice";
  public static String SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE = "SensitiveDataProcessingOptOutNotice";
  public static String SALE_OPT_OUT = "SaleOptOut";
  public static String TARGETED_ADVERTISING_OPT_OUT = "TargetedAdvertisingOptOut";
  public static String SENSITIVE_DATA_PROCESSING = "SensitiveDataProcessing";
  public static String KNOWN_CHILD_SENSITIVE_DATA_CONSENTS = "KnownChildSensitiveDataConsents";
  public static String MSPA_COVERED_TRANSACTION = "MspaCoveredTransaction";
  public static String MSPA_OPT_OUT_OPTION_MODE = "MspaOptOutOptionMode";
  public static String MSPA_SERVICE_PROVIDER_MODE = "MspaServiceProviderMode";

  //@formatter:off
  public static List<String> USUT_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
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
  });
  //@formatter:on
}
