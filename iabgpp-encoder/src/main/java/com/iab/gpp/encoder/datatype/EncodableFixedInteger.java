package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableFixedInteger extends AbstractEncodableBitStringDataType<Integer> {

  private int bitStringLength;

  protected EncodableFixedInteger(int bitStringLength) {
    super(true);
    this.bitStringLength = bitStringLength;
  }

  public EncodableFixedInteger(int bitStringLength, Integer value) {
    super(true);
    this.bitStringLength = bitStringLength;
    setValue(value);
  }

  public EncodableFixedInteger(int bitStringLength, Integer value, boolean hardFailIfMissing) {
    super(hardFailIfMissing);
    this.bitStringLength = bitStringLength;
    setValue(value);
  }

  public String encode() {
    try {
      return FixedIntegerEncoder.encode(this.value, this.bitStringLength);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(String bitString) {
    try {
      this.value = FixedIntegerEncoder.decode(bitString);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }

  public String substring(String bitString, int fromIndex) throws SubstringException {
    try {
      return bitString.substring(fromIndex, fromIndex + this.bitStringLength);
    } catch (Exception e) {
      throw new SubstringException(e);
    }
  }
}
