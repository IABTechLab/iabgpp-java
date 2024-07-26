package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UsUtV1Field {

  public static final String VERSION = "Version";
  public static final String SHARING_NOTICE = "SharingNotice";
  public static final String SALE_OPT_OUT_NOTICE = "SaleOptOutNotice";
  public static final String TARGETED_ADVERTISING_OPT_OUT_NOTICE = "TargetedAdvertisingOptOutNotice";
  public static final String SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE = "SensitiveDataProcessingOptOutNotice";
  public static final String SALE_OPT_OUT = "SaleOptOut";
  public static final String TARGETED_ADVERTISING_OPT_OUT = "TargetedAdvertisingOptOut";
  public static final String SENSITIVE_DATA_PROCESSING = "SensitiveDataProcessing";
  public static final String KNOWN_CHILD_SENSITIVE_DATA_CONSENTS = "KnownChildSensitiveDataConsents";
  public static final String MSPA_COVERED_TRANSACTION = "MspaCoveredTransaction";
  public static final String MSPA_OPT_OUT_OPTION_MODE = "MspaOptOutOptionMode";
  public static final String MSPA_SERVICE_PROVIDER_MODE = "MspaServiceProviderMode";

  //@formatter:off
  public static final List<String> USUTV1_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsUtV1Field.VERSION,
      UsUtV1Field.SHARING_NOTICE,
      UsUtV1Field.SALE_OPT_OUT_NOTICE,
      UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsUtV1Field.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE,
      UsUtV1Field.SALE_OPT_OUT,
      UsUtV1Field.TARGETED_ADVERTISING_OPT_OUT,
      UsUtV1Field.SENSITIVE_DATA_PROCESSING,
      UsUtV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsUtV1Field.MSPA_COVERED_TRANSACTION,
      UsUtV1Field.MSPA_OPT_OUT_OPTION_MODE,
      UsUtV1Field.MSPA_SERVICE_PROVIDER_MODE
  });
  //@formatter:on
}
