package com.iab.gpp.encoder.datatype;

import java.util.ArrayList;
import java.util.List;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerRangeEncoder;
import com.iab.gpp.encoder.datatype.encoder.OptimizedFixedRangeEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableOptimizedArrayOfRanges extends AbstractEncodableBitStringDataType<List<RangeEntry>> {

  private int keyBitStringLength;
  private int typeBitStringLength;

  protected EncodableOptimizedArrayOfRanges(int keyBitStringLength, int typeBitStringLength) {
    super(true);
    this.keyBitStringLength = keyBitStringLength;
    this.typeBitStringLength = typeBitStringLength;
  }

  public EncodableOptimizedArrayOfRanges(int keyBitStringLength, int typeBitStringLength, List<RangeEntry> value) {
    super(true);
    this.keyBitStringLength = keyBitStringLength;
    this.typeBitStringLength = typeBitStringLength;
    setValue(value);
  }
  
  public EncodableOptimizedArrayOfRanges(int keyBitStringLength, int typeBitStringLength, List<RangeEntry> value, boolean hardFailIfMissing) {
    super(hardFailIfMissing);
    this.keyBitStringLength = keyBitStringLength;
    this.typeBitStringLength = typeBitStringLength;
    setValue(value);
  }

  @Override
  public String encode() throws EncodingException {
    try {
      List<RangeEntry> entries = this.value;
  
      StringBuilder sb = new StringBuilder();
      sb.append(FixedIntegerEncoder.encode(entries.size(), 12));
      for (RangeEntry entry : entries) {
        sb.append(FixedIntegerEncoder.encode(entry.getKey(), keyBitStringLength))
            .append(FixedIntegerEncoder.encode(entry.getType(), typeBitStringLength))
            .append(OptimizedFixedRangeEncoder.encode(entry.getIds()));
      }
  
      return sb.toString();
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  @Override
  public void decode(String bitString) throws DecodingException {
    try {
      List<RangeEntry> entries = new ArrayList<>();
  
      int size = FixedIntegerEncoder.decode(bitString.substring(0, 12));
      int index = 12;
      for (int i = 0; i < size; i++) {
        int key = FixedIntegerEncoder.decode(bitString.substring(index, index + keyBitStringLength));
        index += keyBitStringLength;
  
        int type = FixedIntegerEncoder.decode(bitString.substring(index, index + typeBitStringLength));
        index += typeBitStringLength;
  
        String substring = new EncodableOptimizedFixedRange().substring(bitString, index);
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
        sb.append(bitString.substring(index, index + 8));
        index += 8;
        
        String substring = null;
        int max = FixedIntegerEncoder.decode(bitString.substring(index, index + 16));
        if (bitString.charAt(index + 16) == '1') {
          substring = bitString.substring(index, index + 17)
              + new EncodableFixedIntegerRange().substring(bitString, index + 17);
        } else {
          substring = bitString.substring(index, index + 17 + max);
        }
        index += substring.length();
        
        sb.append(substring);
      }
  
      return sb.toString();
    } catch (Exception e) {
      throw new SubstringException(e);
    }
  }

}
