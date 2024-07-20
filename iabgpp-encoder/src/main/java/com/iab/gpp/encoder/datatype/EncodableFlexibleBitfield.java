package com.iab.gpp.encoder.datatype;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntSupplier;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.FixedBitfieldEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableFlexibleBitfield extends AbstractEncodableBitStringDataType<List<Boolean>> {

  private IntSupplier getLengthSupplier;

  protected EncodableFlexibleBitfield(IntSupplier getLengthSupplier) {
    super(true);
    this.getLengthSupplier = getLengthSupplier;
  }

  public EncodableFlexibleBitfield(IntSupplier getLengthSupplier, List<Boolean> value) {
    super(true);
    this.getLengthSupplier = getLengthSupplier;
    this.setValue(value);
  }

  public EncodableFlexibleBitfield(IntSupplier getLengthSupplier, List<Boolean> value, boolean hardFailIfMissing) {
    super(hardFailIfMissing);
    this.getLengthSupplier = getLengthSupplier;
    this.setValue(value);
  }

  public String encode() {
    try {
      return FixedBitfieldEncoder.encode(this.value, this.getLengthSupplier.getAsInt());
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
      return bitString.substring(fromIndex, fromIndex + this.getLengthSupplier.getAsInt());
    } catch (Exception e) {
      throw new SubstringException(e);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public void setValue(Object value) {
    int numElements = this.getLengthSupplier.getAsInt();
    List<Boolean> v = new ArrayList<>((List<Boolean>) value);
    for (int i = v.size(); i < numElements; i++) {
      v.add(false);
    }
    if (v.size() > numElements) {
      v = v.subList(0, numElements);
    }
    super.setValue(v);
  }

  @Override
  public List<Boolean> getValue() {
    return new ArrayList<>(super.getValue());
  }
}
