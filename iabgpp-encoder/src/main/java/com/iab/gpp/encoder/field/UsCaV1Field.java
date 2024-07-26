package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UsCaV1Field {

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
  public static final List<String> USCAV1_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsCaV1Field.VERSION,
      UsCaV1Field.SALE_OPT_OUT_NOTICE,
      UsCaV1Field.SHARING_OPT_OUT_NOTICE,
      UsCaV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE,
      UsCaV1Field.SALE_OPT_OUT,
      UsCaV1Field.SHARING_OPT_OUT,
      UsCaV1Field.SENSITIVE_DATA_PROCESSING,
      UsCaV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsCaV1Field.PERSONAL_DATA_CONSENTS,
      UsCaV1Field.MSPA_COVERED_TRANSACTION,
      UsCaV1Field.MSPA_OPT_OUT_OPTION_MODE,
      UsCaV1Field.MSPA_SERVICE_PROVIDER_MODE
  });
  //@formatter:on

  //@formatter:off
  public static final List<String> USCAV1_GPC_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsCaV1Field.GPC_SEGMENT_TYPE,
      UsCaV1Field.GPC
  });
  //@formatter:on
}
