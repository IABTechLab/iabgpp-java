package com.iab.gpp.encoder.field;

import java.util.Arrays;
import java.util.List;

public class UsNatV1Field {

  public static final String VERSION = "Version";
  public static final String SHARING_NOTICE = "SharingNotice";
  public static final String SALE_OPT_OUT_NOTICE = "SaleOptOutNotice";
  public static final String SHARING_OPT_OUT_NOTICE = "SharingOptOutNotice";
  public static final String TARGETED_ADVERTISING_OPT_OUT_NOTICE = "TargetedAdvertisingOptOutNotice";
  public static final String SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE = "SensitiveDataProcessingOptOutNotice";
  public static final String SENSITIVE_DATA_LIMIT_USE_NOTICE = "SensitiveDataLimitUseNotice";
  public static final String SALE_OPT_OUT = "SaleOptOut";
  public static final String SHARING_OPT_OUT = "SharingOptOut";
  public static final String TARGETED_ADVERTISING_OPT_OUT = "TargetedAdvertisingOptOut";
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
  public static final List<String> USNATV1_CORE_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsNatV1Field.VERSION,
      UsNatV1Field.SHARING_NOTICE,
      UsNatV1Field.SALE_OPT_OUT_NOTICE,
      UsNatV1Field.SHARING_OPT_OUT_NOTICE,
      UsNatV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
      UsNatV1Field.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE,
      UsNatV1Field.SENSITIVE_DATA_LIMIT_USE_NOTICE,
      UsNatV1Field.SALE_OPT_OUT,
      UsNatV1Field.SHARING_OPT_OUT,
      UsNatV1Field.TARGETED_ADVERTISING_OPT_OUT,
      UsNatV1Field.SENSITIVE_DATA_PROCESSING,
      UsNatV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
      UsNatV1Field.PERSONAL_DATA_CONSENTS,
      UsNatV1Field.MSPA_COVERED_TRANSACTION,
      UsNatV1Field.MSPA_OPT_OUT_OPTION_MODE,
      UsNatV1Field.MSPA_SERVICE_PROVIDER_MODE
  });
  //@formatter:on
  
  //@formatter:off
  public static final List<String> USNATV1_GPC_SEGMENT_FIELD_NAMES = Arrays.asList(new String[] {
      UsNatV1Field.GPC_SEGMENT_TYPE,
      UsNatV1Field.GPC
  });
  //@formatter:on
}
