package com.iab.gpp.encoder.datatype;

import java.util.List;
import com.iab.gpp.encoder.datatype.encoder.FixedBitfieldEncoder;
import com.iab.gpp.encoder.error.DecodingException;

public class EncodableFixedBitfield extends AbstractEncodableBitStringDataType<List<Integer>> {
  
  private int bitStringLength;
  
  public EncodableFixedBitfield(int bitStringLength) {
    super();
    this.bitStringLength = bitStringLength;
  }
  
  public EncodableFixedBitfield(int bitStringLength, List<Integer> value) {
    super(value);
    this.bitStringLength = bitStringLength;
  }
  
  public String encode() {
    return FixedBitfieldEncoder.encode(this.value, this.bitStringLength);
  }

  public void decode(String bitString) throws DecodingException {
    this.value = FixedBitfieldEncoder.decode(bitString);
  }

  public String substring(String bitString, int fromIndex) {
    //TODO: validate
    return bitString.substring(fromIndex, fromIndex + this.bitStringLength);
  }
}
