package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringReader;
import com.iab.gpp.encoder.datatype.AbstractEncodableBitStringDataType;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.field.FieldNames;

abstract class AbstractBase64Segment<E extends Enum<E> & FieldKey> extends AbstractLazilyEncodableSegment<E, AbstractEncodableBitStringDataType<?>> {
  
  private final AbstractBase64UrlEncoder base64UrlEncoder;
  protected AbstractBase64Segment(FieldNames<E> fieldNames, AbstractBase64UrlEncoder base64UrlEncoder) {
    super(fieldNames);
    this.base64UrlEncoder = base64UrlEncoder;
  }
  
  @Override
  protected final StringBuilder encodeSegment() {
    BitStringBuilder bitString = new BitStringBuilder();
    int size = fieldNames.size();
    for (int i = 0; i < size; i++) {
      AbstractEncodableBitStringDataType<?> field = get(i);
      if (field != null) {
        field.encode(bitString);
      } else {
        throw new EncodingException("Field not found: '" + fieldNames.get(i) + "'");
      }
    }

    return base64UrlEncoder.encode(bitString);
  }

  @Override
  protected final void decodeSegment(CharSequence encodedString) {
    try {
      BitString bitString = decodeBitString(encodedString);
      int size = fieldNames.size();
      BitStringReader reader = new BitStringReader(bitString);
      for (int i = 0; i < size; i++) {
        AbstractEncodableBitStringDataType<?> field = get(i);
        if (field != null) {
          try {
            field.decode(reader);
          } catch (Exception e) {
            throw new DecodingException("Unable to decode " + fieldNames.get(i), e);
          }
        } else {
          throw new DecodingException("Field not found: '" + fieldNames.get(i) + "'");
        }
      }
    } catch (Exception e) {
      throw new DecodingException("Unable to decode "+ getClass().getSimpleName() +" '" + encodedString + "'", e);
    }
  }

  protected BitString decodeBitString(CharSequence encodedString) {
    return base64UrlEncoder.decode(encodedString);
  }

}
