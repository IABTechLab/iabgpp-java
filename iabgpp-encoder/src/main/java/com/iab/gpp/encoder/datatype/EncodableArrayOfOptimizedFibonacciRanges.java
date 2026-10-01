package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerRangeEncoder;
import com.iab.gpp.encoder.datatype.encoder.OptimizedFibonacciRangeEncoder;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;
import java.util.List;

/**
 * Encodes an {@code N-ArrayOfRanges(X,Y)} field where each record's ids are encoded as an {@code
 * OptimizedRange} (Fibonacci coded), per the GPP Consent String Specification. Counterpart to
 * {@link EncodableArrayOfFixedIntegerRanges}, which encodes ids using fixed-integer ranges for
 * downward compatibility (e.g. TCF EU).
 */
public final class EncodableArrayOfOptimizedFibonacciRanges<E extends Enum<E> & FieldKey>
    extends AbstractDirtyableBitStringDataType<E, DirtyableList<RangeEntry>> {

  private final int keyBitStringLength;
  private final int typeBitStringLength;

  public EncodableArrayOfOptimizedFibonacciRanges(
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
      OptimizedFibonacciRangeEncoder.encode(sb, entry.getIds());
    }
  }

  @Override
  protected DirtyableList<RangeEntry> decode(BitString reader, EncodableSegment<E> segment) {
    // ids are an OptimizedRange in the current spec, but strings produced by the previous encoder
    // used a fixed-integer range for ids. Decode the current way and, if the consumed bits do not
    // re-encode to the same thing, re-read using the legacy fixed-integer range.
    int mark = reader.getReadIndex();
    try {
      DirtyableList<RangeEntry> value = decodeEntries(reader, true);
      BitString consumed = new BitString();
      consumed.write(reader, mark, reader.getReadIndex());
      BitString reEncoded = new BitString();
      encode(reEncoded, value, segment);
      if (reEncoded.toString().equals(consumed.toString())) {
        return value;
      }
    } catch (RuntimeException e) {
      // not the current format; fall back to the legacy fixed-integer range ids
    }
    reader.setReadIndex(mark);
    return decodeEntries(reader, false);
  }

  private DirtyableList<RangeEntry> decodeEntries(BitString reader, boolean optimized) {
    int size = reader.readInt(12);
    DirtyableList<RangeEntry> value = initialize();
    for (int i = 0; i < size; i++) {
      int key = reader.readInt(keyBitStringLength);
      int type = reader.readInt(typeBitStringLength);
      IntegerSet ids =
          optimized
              ? OptimizedFibonacciRangeEncoder.decode(reader)
              : FixedIntegerRangeEncoder.decode(reader);
      value.add(new RangeEntry(key, type, ids));
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
