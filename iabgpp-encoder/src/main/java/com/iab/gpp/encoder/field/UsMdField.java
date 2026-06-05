package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UsMdField {

  public static String MSPA_VERSION = "MspaVersion";
  public static String MSPA_COVERED_TRANSACTION = "MspaCoveredTransaction";
  public static String MSPA_MODE = "MspaMode";
  public static String PROCESSING_NOTICE = "ProcessingNotice";
  public static String SALE_OPT_OUT_NOTICE = "SaleOptOutNotice";
  public static String TARGETED_ADVERTISING_OPT_OUT_NOTICE = "TargetedAdvertisingOptOutNotice";
  public static String SALE_OPT_OUT = "SaleOptOut";
  public static String TARGETED_ADVERTISING_OPT_OUT = "TargetedAdvertisingOptOut";
  public static String ADDITIONAL_DATA_PROCESSING_CONSENT = "AdditionalDataProcessingConsent";

  public static String GPC_SEGMENT_TYPE = "GpcSegmentType";
  public static String GPC_SEGMENT_INCLUDED = "GpcSegmentIncluded";
  public static String GPC = "Gpc";

  //@formatter:off
  public static List<String> USMD_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsMdField.MSPA_VERSION,
      UsMdField.MSPA_COVERED_TRANSACTION,
      UsMdField.MSPA_MODE,
      UsMdField.PROCESSING_NOTICE,
      UsMdField.SALE_OPT_OUT_NOTICE,
      UsMdField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsMdField.SALE_OPT_OUT,
      UsMdField.TARGETED_ADVERTISING_OPT_OUT,
      UsMdField.ADDITIONAL_DATA_PROCESSING_CONSENT
  });
  //@formatter:on

  //@formatter:off
  public static List<String> USMD_GPC_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsMdField.GPC_SEGMENT_TYPE,
      UsMdField.GPC
  });
  //@formatter:on
}
