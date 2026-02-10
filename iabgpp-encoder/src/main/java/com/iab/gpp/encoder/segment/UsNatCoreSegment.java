package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.field.UsNatField;

public final class UsNatCoreSegment extends AbstractBase64Segment<UsNatField> {

  public UsNatCoreSegment() {
    super(UsNatField.USNAT_CORE_SEGMENT_FIELD_NAMES);
  }

  @Override
  protected BitString decodeBitString(CharSequence encodedString) {
    BitString bitString = super.decodeBitString(encodedString);
    // Necessary to maintain backwards compatibility when sensitive data processing changed from a
    // length of 12 to 16 and known child sensitive data consents changed from a length of 2 to 3 in the
    // DE, IA, NE, NH, NJ, TN release
    if (bitString.length() == 66) {
      BitString builder = new BitString();
      
      builder.write(bitString, 0, 48);
      builder.writeEmpty(8);
      builder.write(bitString, 48, 52);
      builder.writeEmpty(2);
      builder.write(bitString, 52, 62);
      bitString = builder;
    }
    return bitString;
  }
  
}
