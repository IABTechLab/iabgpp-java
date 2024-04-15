package com.iab.gpp.encoder.datatype;

import java.util.ArrayList;
import java.util.List;
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
  public void decode(String bitString) {
    try {
      List<RangeEntry> entries = new ArrayList<>();
  
      int size = FixedIntegerEncoder.decode(bitString.substring(0, 12));
      int index = 12;
      for (int i = 0; i < size; i++) {
        int key = FixedIntegerEncoder.decode(bitString.substring(index, index + keyBitStringLength));
        index += keyBitStringLength;
  
        int type = FixedIntegerEncoder.decode(bitString.substring(index, index + typeBitStringLength));
        index += typeBitStringLength;
  
        String substring = new EncodableFixedIntegerRange().substring(bitString, index);
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
  public String substring(String bitString, int fromIndex) throws SubstringException {
    try {
      StringBuilder sb = new StringBuilder();
      sb.append(bitString.substring(fromIndex, fromIndex + 12));
  
      int size = FixedIntegerEncoder.decode(sb.toString());
  
      int index = fromIndex + sb.length();
      for (int i = 0; i < size; i++) {
        String keySubstring = bitString.substring(index, index + keyBitStringLength);
        index += keySubstring.length();
        sb.append(keySubstring);
        
        String typeSubstring = bitString.substring(index, index + typeBitStringLength);
        index += typeSubstring.length();
        sb.append(typeSubstring);
        
        String rangeSubstring = new EncodableFixedIntegerRange().substring(bitString, index);
        index += rangeSubstring.length();
        sb.append(rangeSubstring);
      }
  
      return sb.toString();
    } catch (Exception e) {
      throw new SubstringException(e);
    }
  }

}
