package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UsKyField {

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

  public static String GPC_SEGMENT_TYPE = "GpcSegmentType";
  public static String GPC_SEGMENT_INCLUDED = "GpcSegmentIncluded";
  public static String GPC = "Gpc";

  //@formatter:off
  public static List<String> USKY_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsKyField.MSPA_VERSION,
      UsKyField.MSPA_COVERED_TRANSACTION,
      UsKyField.MSPA_MODE,
      UsKyField.PROCESSING_NOTICE,
      UsKyField.SALE_OPT_OUT_NOTICE,
      UsKyField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsKyField.SALE_OPT_OUT,
      UsKyField.TARGETED_ADVERTISING_OPT_OUT,
      UsKyField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsKyField.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsKyField.SENSITIVE_DATA_PROCESSING
  });
  //@formatter:on

  //@formatter:off
  public static List<String> USKY_GPC_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsKyField.GPC_SEGMENT_TYPE,
      UsKyField.GPC
  });
  //@formatter:on
}
