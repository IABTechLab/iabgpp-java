package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UsFlV1Field {

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
  public static List<String> USFLV1_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsFlV1Field.VERSION,
      UsFlV1Field.PROCESSING_NOTICE,
      UsFlV1Field.SALE_OPT_OUT_NOTICE,
      UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsFlV1Field.SALE_OPT_OUT,
      UsFlV1Field.TARGETED_ADVERTISING_OPT_OUT,
      UsFlV1Field.SENSITIVE_DATA_PROCESSING,
      UsFlV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsFlV1Field.ADDITIONAL_DATA_PROCESSING_CONSENT,
      UsFlV1Field.MSPA_COVERED_TRANSACTION,
      UsFlV1Field.MSPA_OPT_OUT_OPTION_MODE,
      UsFlV1Field.MSPA_SERVICE_PROVIDER_MODE
  });
  //@formatter:on
}
