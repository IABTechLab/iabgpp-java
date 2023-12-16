package com.iab.gpp.encoder.datatype;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntSupplier;
import com.iab.gpp.encoder.datatype.encoder.FixedBitfieldEncoder;

public class EncodableFlexibleBitfield extends AbstractEncodableBitStringDataType<List<Boolean>> {

  private IntSupplier getLengthSupplier;

  protected EncodableFlexibleBitfield(IntSupplier getLengthSupplier) {
    super();
    this.getLengthSupplier = getLengthSupplier;
  }

  public EncodableFlexibleBitfield(IntSupplier getLengthSupplier, List<Boolean> value) {
    super();
    this.getLengthSupplier = getLengthSupplier;
    this.setValue(value);
  }

  public String encode() {
    return FixedBitfieldEncoder.encode(this.value, this.getLengthSupplier.getAsInt());
  }

  public void decode(String bitString) {
    this.value = FixedBitfieldEncoder.decode(bitString);
  }

  public String substring(String bitString, int fromIndex) {
    // TODO: validate
    return bitString.substring(fromIndex, fromIndex + this.getLengthSupplier.getAsInt());
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
