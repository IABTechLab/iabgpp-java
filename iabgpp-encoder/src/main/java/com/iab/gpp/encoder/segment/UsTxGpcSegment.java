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
import com.iab.gpp.encoder.field.UsTxField;

public final class UsTxGpcSegment extends AbstractLazilyEncodableSegment<UsTxField, EncodableBitStringFields<UsTxField>> {

  private static final AbstractBase64UrlEncoder base64UrlEncoder = CompressedBase64UrlEncoder.getInstance();

  public UsTxGpcSegment() {
    super();
  }

  public UsTxGpcSegment(CharSequence encodedString) {
    super();
    this.decode(encodedString);
  }

  @Override
  protected EncodableBitStringFields<UsTxField> initializeFields() {
    EncodableBitStringFields<UsTxField> fields = new EncodableBitStringFields<>(UsTxField.USTX_GPC_SEGMENT_FIELD_NAMES);
    fields.put(UsTxField.GPC_SEGMENT_TYPE, new EncodableFixedInteger(2, 1));
    fields.put(UsTxField.GPC_SEGMENT_INCLUDED, new UnencodableBoolean(true));
    fields.put(UsTxField.GPC, new EncodableBoolean(false));
    return fields;
  }

  @Override
  protected StringBuilder encodeSegment(EncodableBitStringFields<UsTxField> fields) {
    BitStringBuilder bitString = fields.encode();
    return base64UrlEncoder.encode(bitString);
  }

  @Override
  protected void decodeSegment(CharSequence encodedString, EncodableBitStringFields<UsTxField> fields) {
    if(encodedString == null || encodedString.length() == 0) {
      this.fields.reset(fields);
    }
    try {
      BitString bitString = base64UrlEncoder.decode(encodedString);
      this.fields.decode(bitString);
    } catch (Exception e) {
      throw new DecodingException("Unable to decode UsTxGpcSegment '" + encodedString + "'", e);
    }
  }
}
