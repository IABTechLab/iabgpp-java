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
import com.iab.gpp.encoder.field.UsNhField;

public final class UsNhGpcSegment extends AbstractLazilyEncodableSegment<UsNhField, EncodableBitStringFields<UsNhField>> {

  private static final AbstractBase64UrlEncoder base64UrlEncoder = CompressedBase64UrlEncoder.getInstance();

  public UsNhGpcSegment() {
    super();
  }

  public UsNhGpcSegment(String encodedString) {
    super();
    this.decode(encodedString);
  }

  @Override
  protected EncodableBitStringFields<UsNhField> initializeFields() {
    EncodableBitStringFields<UsNhField> fields = new EncodableBitStringFields<>(UsNhField.USNH_GPC_SEGMENT_FIELD_NAMES);
    fields.put(UsNhField.GPC_SEGMENT_TYPE, new EncodableFixedInteger(2, 1));
    fields.put(UsNhField.GPC_SEGMENT_INCLUDED, new UnencodableBoolean(true));
    fields.put(UsNhField.GPC, new EncodableBoolean(false));
    return fields;
  }

  @Override
  protected StringBuilder encodeSegment(EncodableBitStringFields<UsNhField> fields) {
    BitStringBuilder bitString = fields.encode();
    return base64UrlEncoder.encode(bitString);
  }

  @Override
  protected void decodeSegment(CharSequence encodedString, EncodableBitStringFields<UsNhField> fields) {
    if(encodedString == null || encodedString.length() == 0) {
      this.fields.reset(fields);
    }
    try {
      BitString bitString = base64UrlEncoder.decode(encodedString);
      this.fields.decode(bitString);
    } catch (Exception e) {
      throw new DecodingException("Unable to decode UsNhGpcSegment '" + encodedString + "'", e);
    }
  }
}
