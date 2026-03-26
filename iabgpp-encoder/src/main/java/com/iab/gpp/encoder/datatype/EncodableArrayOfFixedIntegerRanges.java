package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerRangeEncoder;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;
import java.util.List;

public final class EncodableArrayOfFixedIntegerRanges<E extends Enum<E> & FieldKey>
    extends AbstractDirtyableBitStringDataType<E, DirtyableList<RangeEntry>> {

  private final int keyBitStringLength;
  private final int typeBitStringLength;

  public EncodableArrayOfFixedIntegerRanges(
      String name, int keyBitStringLength, int typeBitStringLength) {
    super(name, null);
    this.keyBitStringLength = keyBitStringLength;
    this.typeBitStringLength = typeBitStringLength;
  }

  @Override
  public String toString() {
    return name + "=N-ArrayOfRanges(" + keyBitStringLength + "," + typeBitStringLength + ")";
  }

  @Override
  protected DirtyableList<RangeEntry> initialize() {
    return new DirtyableList<>();
  }

  @Override
  protected boolean isPresent(DirtyableList<RangeEntry> value) {
    return !value.isEmpty();
  }

  @Override
  protected void encode(
      BitString sb, DirtyableList<RangeEntry> entries, EncodableSegment<E> segment) {
    sb.writeInt(entries.size(), 12);
    for (RangeEntry entry : entries) {
      sb.writeInt(entry.getKey(), keyBitStringLength);
      sb.writeInt(entry.getType(), typeBitStringLength);
      FixedIntegerRangeEncoder.encode(sb, entry.getIds());
    }
  }

  @Override
  protected DirtyableList<RangeEntry> decode(BitString reader, EncodableSegment<E> segment) {
    int size = reader.readInt(12);
    DirtyableList<RangeEntry> value = initialize();
    for (int i = 0; i < size; i++) {
      int key = reader.readInt(keyBitStringLength);
      int type = reader.readInt(typeBitStringLength);
      IntegerSet ids = FixedIntegerRangeEncoder.decode(reader);
      RangeEntry entry = new RangeEntry(key, type, ids);
      value.add(entry);
    }
    return value;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected DirtyableList<RangeEntry> processValue(
      DirtyableList<RangeEntry> oldValue, Object newValue) {
    oldValue.clear();
    oldValue.addAll((List<RangeEntry>) newValue);
    return oldValue;
  }
}
