package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.section.UsCa;

public enum UsCaField implements FieldKey {
  VERSION(new EncodableFixedInteger<>("Version", 6, UsCa.VERSION)),
  SALE_OPT_OUT_NOTICE(new EncodableFixedInteger<>("SaleOptOutNotice", 2, 0, VALIDATOR_012)),
  SHARING_OPT_OUT_NOTICE(new EncodableFixedInteger<>("SharingOptOutNotice", 2, 0, VALIDATOR_012)),
  SENSITIVE_DATA_LIMIT_USE_NOTICE(
      new EncodableFixedInteger<>("SensitiveDataLimitUseNotice", 2, 0, VALIDATOR_012)),
  SALE_OPT_OUT(new EncodableFixedInteger<>("SaleOptOut", 2, 0, VALIDATOR_012)),
  SHARING_OPT_OUT(new EncodableFixedInteger<>("SharingOptOut", 2, 0, VALIDATOR_012)),
  SENSITIVE_DATA_PROCESSING(
      new EncodableFixedIntegerList<>("SensitiveDataProcessing", 2, 9, VALIDATOR_LIST_012)),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS(
      new EncodableFixedIntegerList<>("KnownChildSensitiveDataConsents", 2, 2, VALIDATOR_LIST_012)),
  PERSONAL_DATA_CONSENTS(new EncodableFixedInteger<>("PersonalDataConsents", 2, 0, VALIDATOR_012)),
  MSPA_COVERED_TRANSACTION(
      new EncodableFixedInteger<>("MspaCoveredTransaction", 2, 1, VALIDATOR_12)),
  MSPA_OPT_OUT_OPTION_MODE(
      new EncodableFixedInteger<>("MspaOptOutOptionMode", 2, 0, VALIDATOR_012)),
  MSPA_SERVICE_PROVIDER_MODE(
      new EncodableFixedInteger<>("MspaServiceProviderMode", 2, 0, VALIDATOR_012)),

  GPC_SEGMENT_TYPE(new EncodableFixedInteger<>("GpcSegmentType", 2, 1)),
  GPC_SEGMENT_INCLUDED(new UnencodableBoolean<>("GpcSegmentIncluded", true)),
  GPC(new EncodableBoolean<>("Gpc", false));

  private final DataType<UsCaField, ?> type;

  UsCaField(DataType<UsCaField, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<UsCaField, ?> getType() {
    return type;
  }

  // @formatter:off
  public static final FieldNames<UsCaField> USCA_CORE_SEGMENT_FIELD_NAMES =
      new FieldNames<>(
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
          UsCaField.MSPA_SERVICE_PROVIDER_MODE);
  // @formatter:on

  // @formatter:off
  public static final FieldNames<UsCaField> USCA_GPC_SEGMENT_FIELD_NAMES =
      new FieldNames<>(UsCaField.GPC_SEGMENT_TYPE, UsCaField.GPC_SEGMENT_INCLUDED, UsCaField.GPC);
  // @formatter:on
}
