package com.iab.gpp.encoder.datatype;

import java.util.Collection;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.FixedIntegerRangeEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public final class EncodableFixedIntegerRange extends AbstractDirtyableBitStringDataType<IntegerSet> {

  protected EncodableFixedIntegerRange() {
    super(true);
  }

  @Override
  protected IntegerSet getDefaultValue() {
    return new IntegerSet();
  }

  public void encode(BitString builder) {
    try {
      FixedIntegerRangeEncoder.encode(builder, this.getValue());
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitString reader) {
    try {
      this.value = FixedIntegerRangeEncoder.decode(reader);
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
