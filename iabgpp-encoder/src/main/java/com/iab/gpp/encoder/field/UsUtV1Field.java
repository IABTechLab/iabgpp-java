package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UsUtV1Field {

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
  public static List<String> USUTV1_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsUtV1Field.VERSION,
      UsUtV1Field.SHARING_NOTICE,
      UsUtV1Field.SALE_OPT_OUT_NOTICE,
      UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsUtV1Field.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE,
      UsUtV1Field.SALE_OPT_OUT,
      UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT,
      UsUtV1Field.SENSITIVE_DATA_PROCESSING,
      UsUtV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsUtV1Field.MSPA_COVERED_TRANSACTION,
      UsUtV1Field.MSPA_OPT_OUT_OPTION_MODE,
      UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE
  });
  //@formatter:on
}
