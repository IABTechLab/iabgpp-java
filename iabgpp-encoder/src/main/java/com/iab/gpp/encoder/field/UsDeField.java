package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UsDeField {

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

  public static String GPC_SEGMENT_TYPE = "GpcSegmentType";
  public static String GPC_SEGMENT_INCLUDED = "GpcSegmentIncluded";
  public static String GPC = "Gpc";
  
  //@formatter:off
  public static List<String> USDE_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsDeField.VERSION,
      UsDeField.PROCESSING_NOTICE,
      UsDeField.SALE_OPT_OUT_NOTICE,
      UsDeField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsDeField.SALE_OPT_OUT,
      UsDeField.TARGETED_ADVERTISING_OPT_OUT,
      UsDeField.SENSITIVE_DATA_PROCESSING,
      UsDeField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsDeField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsDeField.MSPA_COVERED_TRANSACTION,
      UsDeField.MSPA_OPT_OUT_OPTION_MODE,
      UsDeField.MSPA_SERVICE_PROVIDER_MODE
  });
  //@formatter:on
  
  //@formatter:off
  public static List<String> USDE_GPC_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsDeField.GPC_SEGMENT_TYPE,
      UsDeField.GPC
  });
  //@formatter:on
}
