package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UsFlField {

  public static String VERSION = "Version";
  public static String PROCESSING_NOTICE = "ProcessingNotice";
  public static String SALE_OPT_OUT_NOTICE = "SaleOptOutNotice";
  public static String TARGETED_ADVERTISING_OPT_OUT_NOTICE = "TargetedAdvertisingOptOutNotice";
  public static String SALE_OPT_OUT = "SaleOptOut";
  public static String TARGETED_ADVERTISING_OPT_OUT = "TargetedAdvertisingOptOut";
  public static String SENSITIVE_DATA_PROCESSING = "SensitiveDataProcessing";
  public static String KNOWN_CHILD_SENSITIVE_DATA_CONSENTS = "KnownChildSensitiveDataConsents";
  public static String ADDITIONAL_DATA_PROCESSING_CONSENT = "AdditionalDataProcessingConsent";
  public static String MSPA_COVERED_TRANSACTION = "MspaCoveredTransaction";
  public static String MSPA_OPT_OUT_OPTION_MODE = "MspaOptOutOptionMode";
  public static String MSPA_SERVICE_PROVIDER_MODE = "MspaServiceProviderMode";

  //@formatter:off
  public static List<String> USFL_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsFlField.VERSION,
      UsFlField.PROCESSING_NOTICE,
      UsFlField.SALE_OPT_OUT_NOTICE,
      UsFlField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsFlField.SALE_OPT_OUT,
      UsFlField.TARGETED_ADVERTISING_OPT_OUT,
      UsFlField.SENSITIVE_DATA_PROCESSING,
      UsFlField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsFlField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsFlField.MSPA_COVERED_TRANSACTION,
      UsFlField.MSPA_OPT_OUT_OPTION_MODE,
      UsFlField.MSPA_SERVICE_PROVIDER_MODE
  });
  //@formatter:on
}
