package com.iab.gpp.encoder.datatype;

import java.util.List;
import java.util.function.IntSupplier;
import com.iab.gpp.encoder.datatype.encoder.FixedBitfieldEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableFlexibleBitfield extends AbstractEncodableBitStringDataType<List<Boolean>> {
  
  private IntSupplier getLengthSupplier;
  
  public EncodableFlexibleBitfield(IntSupplier getLengthSupplier) {
    super();
    this.getLengthSupplier = getLengthSupplier;
  }
  
  public EncodableFlexibleBitfield(IntSupplier getLengthSupplier, List<Boolean> value) {
    super(value);
    this.getLengthSupplier = getLengthSupplier;
  }

  public String encode() throws EncodingException {
    return FixedBitfieldEncoder.encode(this.value, this.getLengthSupplier.getAsInt());
  }

  public void decode(String bitString) throws DecodingException {
    this.value = FixedBitfieldEncoder.decode(bitString);
  }

  public String substring(String bitString, int fromIndex) {
    //TODO: validate
    return bitString.substring(fromIndex, fromIndex + this.getLengthSupplier.getAsInt());
  }
}
