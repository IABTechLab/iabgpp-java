package com.iab.gpp.encoder.datatype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerRangeEncoder;
import com.iab.gpp.encoder.datatype.encoder.IntegerSet;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public final class EncodableArrayOfFixedIntegerRanges extends AbstractEncodableBitStringDataType<List<RangeEntry>> {

  private int keyBitStringLength;
  private int typeBitStringLength;

  public EncodableArrayOfFixedIntegerRanges(int keyBitStringLength, int typeBitStringLength, boolean hardFailIfMissing) {
    super(hardFailIfMissing);
    this.keyBitStringLength = keyBitStringLength;
    this.typeBitStringLength = typeBitStringLength;
    this.value = Collections.emptyList();
  }

  @Override
  public void encode(BitStringBuilder sb) {
    try {
      List<RangeEntry> entries = this.value;

      FixedIntegerEncoder.encode(sb, entries.size(), 12);
      for (RangeEntry entry : entries) {
        FixedIntegerEncoder.encode(sb, entry.getKey(), keyBitStringLength);
        FixedIntegerEncoder.encode(sb, entry.getType(), typeBitStringLength);
        FixedIntegerRangeEncoder.encode(sb, entry.getIds());
      }

    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  @Override
  public void decode(BitString bitString) {
    try {
      int size = FixedIntegerEncoder.decode(bitString, 0, 12);
      List<RangeEntry> entries = new ArrayList<>(size);
      int index = 12;
      for (int i = 0; i < size; i++) {
        int key = FixedIntegerEncoder.decode(bitString, index, keyBitStringLength);
        index += keyBitStringLength;

        int type = FixedIntegerEncoder.decode(bitString, index, typeBitStringLength);
        index += typeBitStringLength;

        BitString substring = new EncodableFixedIntegerRange().substring(bitString, index);
        IntegerSet ids = FixedIntegerRangeEncoder.decode(substring);
        index += substring.length();

        entries.add(new RangeEntry(key, type, ids));
      }

      // NOTE: this requires that updates to structure be done using the setter
      this.value = Collections.unmodifiableList(entries);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }

  @Override
  public BitString substring(BitString bitString, int fromIndex) throws SubstringException {
    try {
      BitStringBuilder sb = new BitStringBuilder();
      BitString lengthString = bitString.substring(fromIndex, fromIndex + 12);
      sb.append(lengthString);

      int size = FixedIntegerEncoder.decode(lengthString);

      int index = fromIndex + lengthString.length();
      for (int i = 0; i < size; i++) {
        BitString keySubstring = bitString.substring(index, index + keyBitStringLength);
        index += keySubstring.length();
        sb.append(keySubstring);

        BitString typeSubstring = bitString.substring(index, index + typeBitStringLength);
        index += typeSubstring.length();
        sb.append(typeSubstring);

        BitString rangeSubstring = new EncodableFixedIntegerRange().substring(bitString, index);
        index += rangeSubstring.length();
        sb.append(rangeSubstring);
      }

      return sb.build();
    } catch (Exception e) {
      throw new SubstringException(e);
    }
  }

}
