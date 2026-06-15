package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UsInField {

  public static String MSPA_VERSION = "MspaVersion";
  public static String MSPA_COVERED_TRANSACTION = "MspaCoveredTransaction";
  public static String MSPA_MODE = "MspaMode";
  public static String PROCESSING_NOTICE = "ProcessingNotice";
  public static String SALE_OPT_OUT_NOTICE = "SaleOptOutNotice";
  public static String TARGETED_ADVERTISING_OPT_OUT_NOTICE = "TargetedAdvertisingOptOutNotice";
  public static String SALE_OPT_OUT = "SaleOptOut";
  public static String TARGETED_ADVERTISING_OPT_OUT = "TargetedAdvertisingOptOut";
  public static String KNOWN_CHILD_SENSITIVE_DATA_CONSENTS = "KnownChildSensitiveDataConsents";
  public static String ADDITIONAL_DATA_PROCESSING_CONSENT = "AdditionalDataProcessingConsent";
  public static String SENSITIVE_DATA_PROCESSING = "SensitiveDataProcessing";

  public static String SENSITIVE_DATA_CONSENT_SEGMENT_INCLUDED = "SensitiveDataConsentSegmentIncluded";

  //@formatter:off
  public static List<String> USIN_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsInField.MSPA_VERSION,
      UsInField.MSPA_COVERED_TRANSACTION,
      UsInField.MSPA_MODE,
      UsInField.PROCESSING_NOTICE,
      UsInField.SALE_OPT_OUT_NOTICE,
      UsInField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsInField.SALE_OPT_OUT,
      UsInField.TARGETED_ADVERTISING_OPT_OUT,
      UsInField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsInField.ADDITIONAL_DATA_PROCESSING_CONSENT
  });
  //@formatter:on

  //@formatter:off
  public static List<String> USIN_SENSITIVE_DATA_CONSENT_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsInField.SENSITIVE_DATA_PROCESSING
  });
  //@formatter:on
}
