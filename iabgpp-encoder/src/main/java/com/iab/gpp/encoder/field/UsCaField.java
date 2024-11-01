package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UsCaField {

  public static String VERSION = "Version";
  public static String SALE_OPT_OUT_NOTICE = "SaleOptOutNotice";
  public static String SHARING_OPT_OUT_NOTICE = "SharingOptOutNotice";
  public static String SENSITIVE_DATA_LIMIT_USE_NOTICE = "SensitiveDataLimitUseNotice";
  public static String SALE_OPT_OUT = "SaleOptOut";
  public static String SHARING_OPT_OUT = "SharingOptOut";
  public static String SENSITIVE_DATA_PROCESSING = "SensitiveDataProcessing";
  public static String KNOWN_CHILD_SENSITIVE_DATA_CONSENTS = "KnownChildSensitiveDataConsents";
  public static String PERSONAL_DATA_CONSENTS = "PersonalDataConsents";
  public static String MSPA_COVERED_TRANSACTION = "MspaCoveredTransaction";
  public static String MSPA_OPT_OUT_OPTION_MODE = "MspaOptOutOptionMode";
  public static String MSPA_SERVICE_PROVIDER_MODE = "MspaServiceProviderMode";

  public static String GPC_SEGMENT_TYPE = "GpcSegmentType";
  public static String GPC_SEGMENT_INCLUDED = "GpcSegmentIncluded";
  public static String GPC = "Gpc";

  //@formatter:off
  public static List<String> USCA_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsCaField.VERSION,
      UsCaField.SALE_OPT_OUT_NOTICE,
      UsCaField.SHARING_OPT_OUT_NOTICE,
      UsCaField.SENSITIVE_DATA_LIMIT_USE_NOTICE,
      UsCaField.SALE_OPT_OUT,
      UsCaField.SHARING_OPT_OUT,
      UsCaField.SENSITIVE_DATA_PROCESSING,
      UsCaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsCaField.PERSONAL_DATA_CONSENTS,
      UsCaField.MSPA_COVERED_TRANSACTION,
      UsCaField.MSPA_OPT_OUT_OPTION_MODE,
      UsCaField.MSPA_SERVICE_PROVIDER_MODE
  });
  //@formatter:on

  //@formatter:off
  public static List<String> USCA_GPC_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsCaField.GPC_SEGMENT_TYPE,
      UsCaField.GPC
  });
  //@formatter:on
}
