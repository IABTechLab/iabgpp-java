package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UsTxField {

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
  public static List<String> USTX_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsTxField.VERSION,
      UsTxField.PROCESSING_NOTICE,
      UsTxField.SALE_OPT_OUT_NOTICE,
      UsTxField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsTxField.SALE_OPT_OUT,
      UsTxField.TARGETED_ADVERTISING_OPT_OUT,
      UsTxField.SENSITIVE_DATA_PROCESSING,
      UsTxField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsTxField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsTxField.MSPA_COVERED_TRANSACTION,
      UsTxField.MSPA_OPT_OUT_OPTION_MODE,
      UsTxField.MSPA_SERVICE_PROVIDER_MODE
  });
  //@formatter:on
  
  //@formatter:off
  public static List<String> USTX_GPC_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsTxField.GPC_SEGMENT_TYPE,
      UsTxField.GPC
  });
  //@formatter:on
}
