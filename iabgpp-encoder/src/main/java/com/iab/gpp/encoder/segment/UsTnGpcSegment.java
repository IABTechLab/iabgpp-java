package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringEncoder;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.field.EncodableBitStringFields;
import com.iab.gpp.encoder.field.UsTnField;

public final class UsTnGpcSegment extends AbstractLazilyEncodableSegment<EncodableBitStringFields> {

  private static final AbstractBase64UrlEncoder base64UrlEncoder = CompressedBase64UrlEncoder.getInstance();
  private static final BitStringEncoder bitStringEncoder = BitStringEncoder.getInstance();

  public UsTnGpcSegment() {
    super();
  }

  public UsTnGpcSegment(String encodedString) {
    super();
    this.decode(encodedString);
  }

  @Override
  protected EncodableBitStringFields initializeFields() {
    EncodableBitStringFields fields = new EncodableBitStringFields(UsTnField.USTN_GPC_SEGMENT_FIELD_NAMES);
    fields.put(UsTnField.GPC_SEGMENT_TYPE, new EncodableFixedInteger(2, 1));
    fields.put(UsTnField.GPC_SEGMENT_INCLUDED, new UnencodableBoolean(true));
    fields.put(UsTnField.GPC, new EncodableBoolean(false));
    return fields;
  }

  @Override
  protected StringBuilder encodeSegment(EncodableBitStringFields fields) {
    BitStringBuilder bitString = bitStringEncoder.encode(fields);
    return base64UrlEncoder.encode(bitString);
  }

  @Override
  protected void decodeSegment(CharSequence encodedString, EncodableBitStringFields fields) {
    if(encodedString == null || encodedString.length() == 0) {
      this.fields.reset(fields);
    }
    try {
      BitString bitString = base64UrlEncoder.decode(encodedString);
      bitStringEncoder.decode(bitString, fields);
    } catch (Exception e) {
      throw new DecodingException("Unable to decode UsTnGpcSegment '" + encodedString + "'", e);
    }
  }
}
