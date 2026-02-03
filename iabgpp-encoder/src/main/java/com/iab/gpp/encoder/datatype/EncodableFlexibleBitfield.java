package com.iab.gpp.encoder.datatype;

import java.util.Collection;
import java.util.function.IntSupplier;

import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringReader;
import com.iab.gpp.encoder.datatype.encoder.FixedBitfieldEncoder;
import com.iab.gpp.encoder.datatype.encoder.IntegerSet;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public final class EncodableFlexibleBitfield extends AbstractDirtyableBitStringDataType<IntegerSet> {

  private IntSupplier getLengthSupplier;

  public EncodableFlexibleBitfield(IntSupplier getLengthSupplier) {
    super(true);
    this.getLengthSupplier = getLengthSupplier;
  }

  @Override
  protected IntegerSet getDefaultValue() {
    return new IntegerSet();
  }

  public void encode(BitStringBuilder builder) {
    try {
      FixedBitfieldEncoder.encode(builder, this.getValue(), this.getLengthSupplier.getAsInt());
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitStringReader reader) {
    try {
      this.value = reader.readIntegerSet(getLengthSupplier.getAsInt());
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
