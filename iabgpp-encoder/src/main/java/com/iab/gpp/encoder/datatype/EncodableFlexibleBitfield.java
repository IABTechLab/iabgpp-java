package com.iab.gpp.encoder.datatype;

import java.util.Collection;
import java.util.function.IntSupplier;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.FixedBitfieldEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public final class EncodableFlexibleBitfield extends AbstractDirtyableBitStringDataType<IntegerSet> {

  private final IntSupplier getLengthSupplier;

  public EncodableFlexibleBitfield(IntSupplier getLengthSupplier) {
    this.getLengthSupplier = getLengthSupplier;
  }

  @Override
  protected IntegerSet initialize() {
    return new IntegerSet();
  }

  @Override
  protected void encode(BitString builder, IntegerSet value) {
    try {
      FixedBitfieldEncoder.encode(builder, value, this.getLengthSupplier.getAsInt());
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  @Override
  protected IntegerSet decode(BitString reader) {
    try {
      return reader.readIntegerSet(getLengthSupplier.getAsInt());
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }


  @SuppressWarnings("unchecked")
  @Override
  protected IntegerSet processValue(IntegerSet oldValue, Object newValue) {
    oldValue.clear();
    oldValue.addAll((Collection<Integer>) newValue);
    return oldValue;
  }
}
