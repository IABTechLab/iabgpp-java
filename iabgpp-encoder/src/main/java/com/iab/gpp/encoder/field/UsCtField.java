package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.section.UsCt;

public enum UsCtField implements FieldKey {
  VERSION(new EncodableFixedInteger<>("Version", 6, UsCt.VERSION)),
  SHARING_NOTICE(new EncodableFixedInteger<>("SharingNotice", 2, 0, VALIDATOR_012)),
  SALE_OPT_OUT_NOTICE(new EncodableFixedInteger<>("SaleOptOutNotice", 2, 0, VALIDATOR_012)),
  TARGETED_ADVERTISING_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOutNotice", 2, 0, VALIDATOR_012)),
  SALE_OPT_OUT(new EncodableFixedInteger<>("SaleOptOut", 2, 0, VALIDATOR_012)),
  TARGETED_ADVERTISING_OPT_OUT(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOut", 2, 0, VALIDATOR_012)),
  SENSITIVE_DATA_PROCESSING(
      new EncodableFixedIntegerList<>("SensitiveDataProcessing", 2, 8, VALIDATOR_LIST_012)),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS(
      new EncodableFixedIntegerList<>("KnownChildSensitiveDataConsents", 2, 3, VALIDATOR_LIST_012)),
  MSPA_COVERED_TRANSACTION(
      new EncodableFixedInteger<>("MspaCoveredTransaction", 2, 1, VALIDATOR_12)),
  MSPA_OPT_OUT_OPTION_MODE(
      new EncodableFixedInteger<>("MspaOptOutOptionMode", 2, 0, VALIDATOR_012)),
  MSPA_SERVICE_PROVIDER_MODE(
      new EncodableFixedInteger<>("MspaServiceProviderMode", 2, 0, VALIDATOR_012)),

  GPC_SEGMENT_TYPE(new EncodableFixedInteger<>("GpcSegmentType", 2, 1)),
  GPC_SEGMENT_INCLUDED(new UnencodableBoolean<>("GpcSegmentIncluded", true)),
  GPC(new EncodableBoolean<>("Gpc", false));

  private final DataType<UsCtField, ?> type;

  UsCtField(DataType<UsCtField, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<UsCtField, ?> getType() {
    return type;
  }

  public static final FieldNames<UsCtField> USCT_CORE_SEGMENT_FIELD_NAMES =
      new FieldNames<>(
          UsCtField.VERSION,
          UsCtField.SHARING_NOTICE,
          UsCtField.SALE_OPT_OUT_NOTICE,
          UsCtField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
          UsCtField.SALE_OPT_OUT,
          UsCtField.TARGETED_ADVERTISING_OPT_OUT,
          UsCtField.SENSITIVE_DATA_PROCESSING,
          UsCtField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
          UsCtField.MSPA_COVERED_TRANSACTION,
          UsCtField.MSPA_OPT_OUT_OPTION_MODE,
          UsCtField.MSPA_SERVICE_PROVIDER_MODE);

  public static final FieldNames<UsCtField> USCT_GPC_SEGMENT_FIELD_NAMES =
      new FieldNames<>(UsCtField.GPC_SEGMENT_TYPE, UsCtField.GPC_SEGMENT_INCLUDED, UsCtField.GPC);
}
