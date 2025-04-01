package com.iab.gpp.encoder.datatype;

import java.util.Collection;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.datatype.encoder.BitStringSet;
import com.iab.gpp.encoder.datatype.encoder.FixedBitfieldEncoder;
import com.iab.gpp.encoder.datatype.encoder.IntegerSet;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableFixedBitfield extends AbstractEncodableBitStringDataType<IntegerSet> {

  private final int numElements;

  public EncodableFixedBitfield(int numElements) {
    super(true);
    this.numElements = numElements;
    this.value = BitStringSet.withLimit(numElements);
  }

  public void encode(BitStringBuilder builder) {
    try {
      FixedBitfieldEncoder.encode(builder, this.value, this.numElements);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitString bitString) {
    try {
      this.value = FixedBitfieldEncoder.decode(bitString);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }

  public BitString substring(BitString bitString, int fromIndex) throws SubstringException {
    try {
      return bitString.substring(fromIndex, fromIndex + this.numElements);
    } catch (Exception e) {
      throw new SubstringException(e);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setValue(Object value) {
    this.value.clear();
    this.value.addAll((Collection<Integer>) value);
  }

  @Override
  public IntegerSet getValue() {
    return new ManagedIntegerSet(this, super.getValue());
  }
}
