package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UsVaField {

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

  //@formatter:off
  public static List<String> USVA_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsVaField.VERSION,
      UsVaField.SHARING_NOTICE,
      UsVaField.SALE_OPT_OUT_NOTICE,
      UsVaField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsVaField.SALE_OPT_OUT,
      UsVaField.TARGETED_ADVERTISING_OPT_OUT,
      UsVaField.SENSITIVE_DATA_PROCESSING,
      UsVaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsVaField.MSPA_COVERED_TRANSACTION,
      UsVaField.MSPA_OPT_OUT_OPTION_MODE,
      UsVaField.MSPA_SERVICE_PROVIDER_MODE
  });
  //@formatter:on
}
