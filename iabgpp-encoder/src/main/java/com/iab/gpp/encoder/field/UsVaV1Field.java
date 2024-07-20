package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UsVaV1Field {

  public static final String VERSION = "Version";
  public static final String SHARING_NOTICE = "SharingNotice";
  public static final String SALE_OPT_OUT_NOTICE = "SaleOptOutNotice";
  public static final String TARGETED_ADVERTISING_OPT_OUT_NOTICE = "TargetedAdvertisingOptOutNotice";
  public static final String SALE_OPT_OUT = "SaleOptOut";
  public static final String TARGETED_ADVERTISING_OPT_OUT = "TargetedAdvertisingOptOut";
  public static final String SENSITIVE_DATA_PROCESSING = "SensitiveDataProcessing";
  public static final String KNOWN_CHILD_SENSITIVE_DATA_CONSENTS = "KnownChildSensitiveDataConsents";
  public static final String MSPA_COVERED_TRANSACTION = "MspaCoveredTransaction";
  public static final String MSPA_OPT_OUT_OPTION_MODE = "MspaOptOutOptionMode";
  public static final String MSPA_SERVICE_PROVIDER_MODE = "MspaServiceProviderMode";

  //@formatter:off
  public static final List<String> USVAV1_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsVaV1Field.VERSION,
      UsVaV1Field.SHARING_NOTICE,
      UsVaV1Field.SALE_OPT_OUT_NOTICE,
      UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsVaV1Field.SALE_OPT_OUT,
      UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT,
      UsVaV1Field.SENSITIVE_DATA_PROCESSING,
      UsVaV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsVaV1Field.MSPA_COVERED_TRANSACTION,
      UsVaV1Field.MSPA_OPT_OUT_OPTION_MODE,
      UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE
  });
  //@formatter:on
}
