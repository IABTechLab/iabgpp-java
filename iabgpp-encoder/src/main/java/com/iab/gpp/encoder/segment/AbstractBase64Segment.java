package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.DataType;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.field.FieldNames;

abstract class AbstractBase64Segment<E extends Enum<E> & FieldKey>
    extends AbstractLazilyEncodableSegment<E> {

  protected AbstractBase64Segment(FieldNames<E> fieldNames) {
    super(fieldNames);
  }

  protected abstract AbstractBase64UrlEncoder getBase64UrlEncoder();

  @Override
  protected final CharSequence doEncode() {
    BitString bitString = new BitString();
    int size = fieldNames.size();
    for (int i = 0; i < size; i++) {
      DataType<E, ?> field = fieldNames.get(i);
      try {
        field.encode(bitString, values, i, this);
      } catch (Exception e) {
        throw new EncodingException("Unable to decode " + field.getName(), e);
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
        DataType<E, ?> field = fieldNames.get(i);
        try {
          field.decode(bitString, values, i, this);
        } catch (Exception e) {
          throw new DecodingException("Unable to decode " + field.getName(), e);
        }
      }
    } catch (Exception e) {
      throw new DecodingException(
          "Unable to decode " + getClass().getSimpleName() + " '" + encodedString + "'", e);
    }
  }

  protected BitString decodeBitString(CharSequence encodedString) {
    return getBase64UrlEncoder().decode(encodedString);
  }
}
