package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.field.FieldNames;

abstract class AbstractBase64Segment<E extends Enum<E> & FieldKey> extends AbstractLazilyEncodableSegment<E> {
  
  protected AbstractBase64Segment(FieldNames<E> fieldNames) {
    super(fieldNames);
  }

  protected AbstractBase64UrlEncoder getBase64UrlEncoder() {
    return CompressedBase64UrlEncoder.getInstance();
  }
  
  @Override
  protected final CharSequence doEncode() {
    BitString bitString = new BitString();
    int size = fieldNames.size();
    for (int i = 0; i < size; i++) {
      DataType<?> field = fieldNames.getType(i);
      if (field != null) {
        field.encode(bitString, values, i, this);
      } else {
        throw new EncodingException("Field not found: '" + fieldNames.get(i) + "'");
      }
    }

    return getBase64UrlEncoder().encode(bitString);
  }

  @Override
  protected final void doDecode(CharSequence encodedString) {
    try {
      BitString bitString = decodeBitString(encodedString);
      int size = fieldNames.size();
      for (int i = 0; i < size; i++) {
        DataType<?> field = fieldNames.getType(i);
        if (field != null) {
          try {
            field.decode(bitString, values, i, this);
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
    return getBase64UrlEncoder().decode(encodedString);
  }

}
