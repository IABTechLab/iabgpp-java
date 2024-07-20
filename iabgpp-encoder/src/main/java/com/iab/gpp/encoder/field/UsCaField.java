package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UsCaField {

  public static final String VERSION = "Version";
  public static final String SALE_OPT_OUT_NOTICE = "SaleOptOutNotice";
  public static final String SHARING_OPT_OUT_NOTICE = "SharingOptOutNotice";
  public static final String SENSITIVE_DATA_LIMIT_USE_NOTICE = "SensitiveDataLimitUseNotice";
  public static final String SALE_OPT_OUT = "SaleOptOut";
  public static final String SHARING_OPT_OUT = "SharingOptOut";
  public static final String SENSITIVE_DATA_PROCESSING = "SensitiveDataProcessing";
  public static final String KNOWN_CHILD_SENSITIVE_DATA_CONSENTS = "KnownChildSensitiveDataConsents";
  public static final String PERSONAL_DATA_CONSENTS = "PersonalDataConsents";
  public static final String MSPA_COVERED_TRANSACTION = "MspaCoveredTransaction";
  public static final String MSPA_OPT_OUT_OPTION_MODE = "MspaOptOutOptionMode";
  public static final String MSPA_SERVICE_PROVIDER_MODE = "MspaServiceProviderMode";

  public static final String GPC_SEGMENT_TYPE = "GpcSegmentType";
  public static final String GPC_SEGMENT_INCLUDED = "GpcSegmentIncluded";
  public static final String GPC = "Gpc";

  //@formatter:off
  public static final List<String> USCA_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
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
  public static final List<String> USCA_GPC_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsCaField.GPC_SEGMENT_TYPE,
      UsCaField.GPC
  });
  //@formatter:on
}
