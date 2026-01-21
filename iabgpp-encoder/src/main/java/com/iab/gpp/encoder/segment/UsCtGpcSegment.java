package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.UnencodableBoolean;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.field.EncodableBitStringFields;
import com.iab.gpp.encoder.field.UsCtField;

public final class UsCtGpcSegment extends AbstractLazilyEncodableSegment<UsCtField, EncodableBitStringFields<UsCtField>> {

  private static final AbstractBase64UrlEncoder base64UrlEncoder = CompressedBase64UrlEncoder.getInstance();

  public UsCtGpcSegment() {
    super();
  }

  public UsCtGpcSegment(String encodedString) {
    super();
    this.decode(encodedString);
  }

  @Override
  protected EncodableBitStringFields<UsCtField> initializeFields() {
    EncodableBitStringFields<UsCtField> fields = new EncodableBitStringFields<>(UsCtField.USCT_GPC_SEGMENT_FIELD_NAMES);
    fields.put(UsCtField.GPC_SEGMENT_TYPE, new EncodableFixedInteger(2, 1));
    fields.put(UsCtField.GPC_SEGMENT_INCLUDED, new UnencodableBoolean(true));
    fields.put(UsCtField.GPC, new EncodableBoolean(false));
    return fields;
  }

  @Override
  protected StringBuilder encodeSegment(EncodableBitStringFields<UsCtField> fields) {
    BitStringBuilder bitString = fields.encode();
    return base64UrlEncoder.encode(bitString);
  }

  @Override
  protected void decodeSegment(CharSequence encodedString, EncodableBitStringFields<UsCtField> fields) {
    if(encodedString == null || encodedString.length() == 0) {
      this.fields.reset(fields);
    }
    try {
      BitString bitString = base64UrlEncoder.decode(encodedString);
      this.fields.decode(bitString);
    } catch (Exception e) {
      throw new DecodingException("Unable to decode UsCtGpcSegment '" + encodedString + "'", e);
    }
  }
}
