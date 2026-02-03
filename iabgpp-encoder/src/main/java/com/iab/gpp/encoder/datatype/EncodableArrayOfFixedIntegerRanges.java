package com.iab.gpp.encoder.datatype;

import java.util.List;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerRangeEncoder;
import com.iab.gpp.encoder.datatype.encoder.IntegerSet;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public final class EncodableArrayOfFixedIntegerRanges extends AbstractDirtyableBitStringDataType<DirtyableList<RangeEntry>> {

  private int keyBitStringLength;
  private int typeBitStringLength;

  public EncodableArrayOfFixedIntegerRanges(int keyBitStringLength, int typeBitStringLength, boolean hardFailIfMissing) {
    super(hardFailIfMissing);
    this.keyBitStringLength = keyBitStringLength;
    this.typeBitStringLength = typeBitStringLength;
    this.value = new DirtyableList<>();
  }

  @Override
  public void encode(BitString sb) {
    try {
      List<RangeEntry> entries = this.value;

      sb.writeInt(entries.size(), 12);
      for (RangeEntry entry : entries) {
        sb.writeInt(entry.getKey(), keyBitStringLength);
        sb.writeInt(entry.getType(), typeBitStringLength);
        FixedIntegerRangeEncoder.encode(sb, entry.getIds());
      }

    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  @Override
  public void decode(BitString reader) {
    try {
      int size = reader.readInt(12);
      value.clear();
      for (int i = 0; i < size; i++) {
        int key = reader.readInt(keyBitStringLength);
        int type = reader.readInt(typeBitStringLength);
        IntegerSet ids = FixedIntegerRangeEncoder.decode(reader);
        RangeEntry entry = new RangeEntry(key, type, ids);
        value.add(entry);
      }
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setValue(Object value) {
    this.value.clear();
    this.value.addAll((List<RangeEntry>) value);
  }
}
