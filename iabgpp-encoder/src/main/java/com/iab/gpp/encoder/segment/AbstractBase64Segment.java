package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.datatype.AbstractEncodableBitStringDataType;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.field.EncodableBitStringFields;
import com.iab.gpp.encoder.field.FieldNames;
import com.iab.gpp.encoder.field.UsCaField;
import com.iab.gpp.encoder.section.FieldKey;

public abstract class AbstractBase64Segment<E extends Enum<E> & FieldKey> extends AbstractLazilyEncodableSegment<E, AbstractEncodableBitStringDataType<?>> {
  
  private final AbstractBase64UrlEncoder base64UrlEncoder;
  protected AbstractBase64Segment(FieldNames<E> fieldNames, AbstractBase64UrlEncoder base64UrlEncoder) {
    super(fieldNames);
    this.base64UrlEncoder = base64UrlEncoder;
  }
  
  @Override
  protected final StringBuilder encodeSegment() {
    BitStringBuilder bitString = fields.encode();
    return base64UrlEncoder.encode(bitString);
  }

  @Override
  protected final void decodeSegment(CharSequence encodedString) {
    if (encodedString == null || encodedString.length() == 0) {
      this.fields.reset(fields);
    }
    try {
      BitString bitString = decodeBitString(encodedString);
      this.fields.decode(bitString);
    } catch (Exception e) {
      throw new DecodingException("Unable to decode UsCaCoreSegment '" + encodedString + "'", e);
    }
  }

  protected BitString decodeBitString(CharSequence encodedString) {
    return base64UrlEncoder.decode(encodedString);
  }

}
