package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.field.UsNatField;

public final class UsNatCoreSegment extends AbstractBase64Segment<UsNatField> {

  private static final int VERSION_LENGTH = 6;

  public UsNatCoreSegment() {
    super(UsNatField.USNAT_CORE_SEGMENT_FIELD_NAMES, false);
  }

  protected AbstractBase64UrlEncoder getBase64UrlEncoder() {
    return CompressedBase64UrlEncoder.getInstance();
  }

  @Override
  protected BitString decodeBitString(CharSequence encodedString) {
    BitString bitString = super.decodeBitString(encodedString);
    // An encoder implemented the v2 spec (more fields) while still sending the version as 1.
    // Necessary to maintain backwards compatibility when sensitive data processing changed from a
    // length of 12 to 16 and known child sensitive data consents changed from a length of 2 to 3 in
    // the DE, IA, NE, NH, NJ, TN release.
    // We use a heuristic of length to determine the v2 strings masquerading as v1 string.
    if (bitString.peekInt(VERSION_LENGTH) == 1 && bitString.length() >= 72) {
      // Upgrade to version 2
      bitString.putInt(2, VERSION_LENGTH);
    }
    return bitString;
  }
}
