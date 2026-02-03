package com.iab.gpp.encoder.datatype;

import java.util.Collection;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.FixedBitfieldEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public final class EncodableFixedBitfield extends AbstractDirtyableBitStringDataType<IntegerSet> {

  private final int numElements;

  public EncodableFixedBitfield(int numElements) {
    super(true);
    this.numElements = numElements;
  }

  @Override
  protected IntegerSet getDefaultValue() {
    return new IntegerSet(numElements);
  }

  public void encode(BitString builder) {
    try {
      FixedBitfieldEncoder.encode(builder, this.getValue(), this.numElements);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitString reader) {
    try {
      this.value = reader.readIntegerSet(this.numElements);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setValue(Object newValue) {
    IntegerSet value = this.getValue();
    value.clear();
    value.addAll((Collection<Integer>) newValue);
  }
}
