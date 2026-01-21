package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.TraditionalBase64UrlEncoder;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableOptimizedFixedRange;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.field.EncodableBitStringFields;
import com.iab.gpp.encoder.field.TcfEuV2Field;

public final class TcfEuV2VendorsAllowedSegment extends AbstractLazilyEncodableSegment<TcfEuV2Field, EncodableBitStringFields<TcfEuV2Field>> {

  private static final AbstractBase64UrlEncoder base64UrlEncoder = TraditionalBase64UrlEncoder.getInstance();

  public TcfEuV2VendorsAllowedSegment() {
    super();
  }

  public TcfEuV2VendorsAllowedSegment(String encodedString) {
    super();
    this.decode(encodedString);
  }

  @Override
  protected EncodableBitStringFields<TcfEuV2Field> initializeFields() {
    EncodableBitStringFields<TcfEuV2Field> fields = new EncodableBitStringFields<>(TcfEuV2Field.TCFEUV2_VENDORS_ALLOWED_SEGMENT_FIELD_NAMES);
    fields.put(TcfEuV2Field.VENDORS_ALLOWED_SEGMENT_TYPE, new EncodableFixedInteger(3, 2));
    fields.put(TcfEuV2Field.VENDORS_ALLOWED, new EncodableOptimizedFixedRange());
    return fields;
  }

  @Override
  protected StringBuilder encodeSegment(EncodableBitStringFields<TcfEuV2Field> fields) {
    BitStringBuilder bitString = fields.encode();
    return base64UrlEncoder.encode(bitString);
  }

  @Override
  protected void decodeSegment(CharSequence encodedString, EncodableBitStringFields<TcfEuV2Field> fields) {
    if(encodedString == null || encodedString.length() == 0) {
      this.fields.reset(fields);
    }
    try {
      BitString bitString = base64UrlEncoder.decode(encodedString);
      fields.decode(bitString);
    } catch (Exception e) {
      throw new DecodingException("Unable to decode TcfEuV2VendorsAllowedSegment '" + encodedString + "'", e);
    }
  }
}
