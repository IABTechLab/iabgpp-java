package com.iab.gpp.encoder.datatype;

import java.util.ArrayList;
import java.util.List;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerRangeEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableArrayOfFixedIntegerRanges extends AbstractEncodableBitStringDataType<List<RangeEntry>> {

  private int keyBitStringLength;
  private int typeBitStringLength;

  protected EncodableArrayOfFixedIntegerRanges(int keyBitStringLength, int typeBitStringLength) {
    super(true);
    this.keyBitStringLength = keyBitStringLength;
    this.typeBitStringLength = typeBitStringLength;
  }

  public EncodableArrayOfFixedIntegerRanges(int keyBitStringLength, int typeBitStringLength, List<RangeEntry> value) {
    super(true);
    this.keyBitStringLength = keyBitStringLength;
    this.typeBitStringLength = typeBitStringLength;
    setValue(value);
  }
  
  public EncodableArrayOfFixedIntegerRanges(int keyBitStringLength, int typeBitStringLength, List<RangeEntry> value, boolean hardFailIfMissing) {
    super(hardFailIfMissing);
    this.keyBitStringLength = keyBitStringLength;
    this.typeBitStringLength = typeBitStringLength;
    setValue(value);
  }

  @Override
  public String encode() {
    try {
      List<RangeEntry> entries = this.value;

      StringBuilder sb = new StringBuilder();
      sb.append(FixedIntegerEncoder.encode(entries.size(), 12));
      for (RangeEntry entry : entries) {
        sb.append(FixedIntegerEncoder.encode(entry.getKey(), keyBitStringLength))
            .append(FixedIntegerEncoder.encode(entry.getType(), typeBitStringLength))
            .append(FixedIntegerRangeEncoder.encode(entry.getIds()));
      }

      return sb.toString();
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
        List<Integer> ids = FixedIntegerRangeEncoder.decode(substring);
        index += substring.length();

        entries.add(new RangeEntry(key, type, ids));
      }

      this.value = entries;
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
