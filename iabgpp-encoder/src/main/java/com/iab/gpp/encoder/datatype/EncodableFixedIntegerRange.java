package com.iab.gpp.encoder.datatype;

import java.util.Collection;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.datatype.encoder.IntegerBitSet;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerEncoder;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerRangeEncoder;
import com.iab.gpp.encoder.datatype.encoder.IntegerSet;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableFixedIntegerRange extends AbstractEncodableBitStringDataType<IntegerSet> {

  protected EncodableFixedIntegerRange() {
    super(true);
    this.value = new IntegerBitSet();
  }

  public void encode(BitStringBuilder builder) {
    try {
      FixedIntegerRangeEncoder.encode(builder, this.value);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitString bitString) {
    try {
      this.value = FixedIntegerRangeEncoder.decode(bitString);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }

  public BitString substring(BitString bitString, int fromIndex) throws SubstringException {
    try {
      int count = FixedIntegerEncoder.decode(bitString, fromIndex, 12);
      int index = fromIndex + 12;
      for (int i = 0; i < count; i++) {
        if (bitString.getValue(index)) {
          index += 33;
        } else {
          index += 17;
        }
      }
      return bitString.substring(fromIndex, index);
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
