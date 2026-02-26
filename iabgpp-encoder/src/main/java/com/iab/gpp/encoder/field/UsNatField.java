package com.iab.gpp.encoder.field;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.AbstractDirtyableBitStringDataType;
import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.FixedIntegerList;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerListEncoder;
import com.iab.gpp.encoder.section.UsNat;
import com.iab.gpp.encoder.segment.EncodableSegment;
import com.iab.gpp.encoder.segment.SegmentValueProvider;
import java.util.List;
import java.util.function.Predicate;

public enum UsNatField implements FieldKey {
  VERSION(new EncodableFixedInteger<>("Version", 6, UsNat.VERSION)),
  SHARING_NOTICE(new EncodableFixedInteger<>("SharingNotice", 2, 0, VALIDATOR_012)),
  SALE_OPT_OUT_NOTICE(new EncodableFixedInteger<>("SaleOptOutNotice", 2, 0, VALIDATOR_012)),
  SHARING_OPT_OUT_NOTICE(new EncodableFixedInteger<>("SharingOptOutNotice", 2, 0, VALIDATOR_012)),
  TARGETED_ADVERTISING_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOutNotice", 2, 0, VALIDATOR_012)),
  SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE(
      new EncodableFixedInteger<>("SensitiveDataProcessingOptOutNotice", 2, 0, VALIDATOR_012)),
  SENSITIVE_DATA_LIMIT_USE_NOTICE(
      new EncodableFixedInteger<>("SensitiveDataLimitUseNotice", 2, 0, VALIDATOR_012)),
  SALE_OPT_OUT(new EncodableFixedInteger<>("SaleOptOut", 2, 0, VALIDATOR_012)),
  SHARING_OPT_OUT(new EncodableFixedInteger<>("SharingOptOut", 2, 0, VALIDATOR_012)),
  TARGETED_ADVERTISING_OPT_OUT(
      new EncodableFixedInteger<>("TargetedAdvertisingOptOut", 2, 0, VALIDATOR_012)),
  SENSITIVE_DATA_PROCESSING(
      new EncodableFlexibleIntegerList(
          "SensitiveDataProcessing", 2, new VersionedLengthProvider(12, 16), VALIDATOR_LIST_012)),
  KNOWN_CHILD_SENSITIVE_DATA_CONSENTS(
      new EncodableFlexibleIntegerList(
          "KnownChildSensitiveDataConsents",
          2,
          new VersionedLengthProvider(2, 3),
          VALIDATOR_LIST_012)),
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

  private final DataType<UsNatField, ?> type;

  UsNatField(DataType<UsNatField, ?> type) {
    this.type = type;
  }

  @Override
  public DataType<UsNatField, ?> getType() {
    return type;
  }

  public static final FieldNames<UsNatField> USNAT_CORE_SEGMENT_FIELD_NAMES =
      new FieldNames<>(
          UsNatField.VERSION,
          UsNatField.SHARING_NOTICE,
          UsNatField.SALE_OPT_OUT_NOTICE,
          UsNatField.SHARING_OPT_OUT_NOTICE,
          UsNatField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
          UsNatField.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE,
          UsNatField.SENSITIVE_DATA_LIMIT_USE_NOTICE,
          UsNatField.SALE_OPT_OUT,
          UsNatField.SHARING_OPT_OUT,
          UsNatField.TARGETED_ADVERTISING_OPT_OUT,
          UsNatField.SENSITIVE_DATA_PROCESSING,
          UsNatField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
          UsNatField.PERSONAL_DATA_CONSENTS,
          UsNatField.MSPA_COVERED_TRANSACTION,
          UsNatField.MSPA_OPT_OUT_OPTION_MODE,
          UsNatField.MSPA_SERVICE_PROVIDER_MODE);

  public static final FieldNames<UsNatField> USNAT_GPC_SEGMENT_FIELD_NAMES =
      new FieldNames<>(
          UsNatField.GPC_SEGMENT_TYPE, UsNatField.GPC_SEGMENT_INCLUDED, UsNatField.GPC);

  // used for usnat v1 to v2 conversion, see note in UsNatCoreSegment
  private static final class EncodableFlexibleIntegerList
      extends AbstractDirtyableBitStringDataType<UsNatField, FixedIntegerList> {

    private final int elementBitStringLength;
    private final VersionedLengthProvider lengthProvider;

    public EncodableFlexibleIntegerList(
        String name,
        int elementBitStringLength,
        VersionedLengthProvider lengthProvider,
        Predicate<FixedIntegerList> validator) {
      super(name, validator);
      this.elementBitStringLength = elementBitStringLength;

      this.lengthProvider = lengthProvider;
    }

    @Override
    protected FixedIntegerList initialize() {
      return new FixedIntegerList(elementBitStringLength, lengthProvider.v2Length);
    }

    @Override
    protected void encode(
        BitString builder, FixedIntegerList value, EncodableSegment<UsNatField> segment) {
      FixedIntegerListEncoder.encode(
          builder, value, this.elementBitStringLength, lengthProvider.extract(segment));
    }

    @Override
    protected FixedIntegerList decode(BitString reader, EncodableSegment<UsNatField> segment) {
      return reader.readFixedIntegerList(elementBitStringLength, lengthProvider.extract(segment));
    }

    @SuppressWarnings("unchecked")
    @Override
    protected FixedIntegerList processValue(FixedIntegerList oldValue, Object newValue) {
      List<Integer> list = (List<Integer>) newValue;
      int size = list.size();
      for (int i = 0; i < oldValue.size(); i++) {
        oldValue.set(i, i < size ? list.get(i) : 0);
      }
      return oldValue;
    }
  }

  private static final class VersionedLengthProvider extends SegmentValueProvider<UsNatField> {

    private final int v1Length;
    private final int v2Length;

    public VersionedLengthProvider(int v1Length, int v2Length) {
      super(UsNatField.VERSION);
      this.v1Length = v1Length;
      this.v2Length = v2Length;
    }

    public int modify(int original) {
      return original == 1 ? v1Length : v2Length;
    }
  }
}
