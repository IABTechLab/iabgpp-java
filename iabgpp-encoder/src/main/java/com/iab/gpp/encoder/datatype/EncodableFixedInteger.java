package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
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

  public void encode(BitStringBuilder builder) {
    try {
      FixedIntegerEncoder.encode(builder, this.value, this.bitStringLength);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitString bitString) {
    try {
      this.value = FixedIntegerEncoder.decode(bitString);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }

  public BitString substring(BitString bitString, int fromIndex) throws SubstringException {
    try {
      return bitString.substring(fromIndex, fromIndex + this.bitStringLength);
    } catch (Exception e) {
      throw new SubstringException(e);
    }
  }
}
