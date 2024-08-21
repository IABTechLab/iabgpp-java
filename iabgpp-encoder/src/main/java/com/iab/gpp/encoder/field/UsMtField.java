package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UsMtField {

  public static String VERSION = "Version";
  public static String SHARING_NOTICE = "SharingNotice";
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
  public static List<String> USMT_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsMtField.VERSION,
      UsMtField.SHARING_NOTICE,
      UsMtField.SALE_OPT_OUT_NOTICE,
      UsMtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsMtField.SALE_OPT_OUT,
      UsMtField.TARGETED_ADVERTISING_OPT_OUT,
      UsMtField.SENSITIVE_DATA_PROCESSING,
      UsMtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsMtField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsMtField.MSPA_COVERED_TRANSACTION,
      UsMtField.MSPA_OPT_OUT_OPTION_MODE,
      UsMtField.MSPA_SERVICE_PROVIDER_MODE
  });
  //@formatter:on
  
  //@formatter:off
  public static List<String> USMT_GPC_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsMtField.GPC_SEGMENT_TYPE,
      UsMtField.GPC
  });
  //@formatter:on
}
