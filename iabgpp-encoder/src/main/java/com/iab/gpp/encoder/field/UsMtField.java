package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UsMtField {

  public static final String VERSION = "Version";
  public static final String SHARING_NOTICE = "SharingNotice";
  public static final String SALE_OPT_OUT_NOTICE = "SaleOptOutNotice";
  public static final String TARGETED_ADVERTISING_OPT_OUT_NOTICE = "TargetedAdvertisingOptOutNotice";
  public static final String SALE_OPT_OUT = "SaleOptOut";
  public static final String TARGETED_ADVERTISING_OPT_OUT = "TargetedAdvertisingOptOut";
  public static final String SENSITIVE_DATA_PROCESSING = "SensitiveDataProcessing";
  public static final String KNOWN_CHILD_SENSITIVE_DATA_CONSENTS = "KnownChildSensitiveDataConsents";
  public static final String ADDITIONAL_DATA_PROCESSING_CONSENT = "AdditionalDataProcessingConsent";
  public static final String MSPA_COVERED_TRANSACTION = "MspaCoveredTransaction";
  public static final String MSPA_OPT_OUT_OPTION_MODE = "MspaOptOutOptionMode";
  public static final String MSPA_SERVICE_PROVIDER_MODE = "MspaServiceProviderMode";

  public static final String GPC_SEGMENT_TYPE = "GpcSegmentType";
  public static final String GPC_SEGMENT_INCLUDED = "GpcSegmentIncluded";
  public static final String GPC = "Gpc";
  
  //@formatter:off
  public static final List<String> USMT_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
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
