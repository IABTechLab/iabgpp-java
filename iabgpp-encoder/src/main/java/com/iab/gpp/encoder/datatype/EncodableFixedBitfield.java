package com.iab.gpp.encoder.datatype;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.datatype.encoder.FixedBitfieldEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableFixedBitfield extends AbstractEncodableBitStringDataType<List<Boolean>> {

  private int numElements;

  public EncodableFixedBitfield(int numElements) {
    super(true);
    this.numElements = numElements;
    this.value = BitString.empty(numElements);
  }

  protected EncodableFixedBitfield(int numElements, boolean hardFailIfMissing) {
    super(hardFailIfMissing);
    this.numElements = numElements;
  }

  public EncodableFixedBitfield(List<Boolean> value, boolean hardFailIfMissing) {
    super(hardFailIfMissing);
    this.numElements = value.size();
    setValue(value);
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
    return Collections.unmodifiableList(super.getValue());
  }
}
