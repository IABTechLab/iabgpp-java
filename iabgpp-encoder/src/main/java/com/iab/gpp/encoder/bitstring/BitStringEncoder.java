package com.iab.gpp.encoder.bitstring;

import java.util.List;
import com.iab.gpp.encoder.datatype.AbstractEncodableBitStringDataType;
import com.iab.gpp.encoder.datatype.SubstringException;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.EncodableBitStringFields;

public final class BitStringEncoder {

  private static final BitStringEncoder instance = new BitStringEncoder();

  private BitStringEncoder() {

  }

  public static BitStringEncoder getInstance() {
    return instance;
  }

  public BitStringBuilder encode(EncodableBitStringFields fields) {
    BitStringBuilder bitString = new BitStringBuilder();
    List<String> fieldNames = fields.getNames();
    for (int i = 0; i < fieldNames.size(); i++) {
      AbstractEncodableBitStringDataType<?> field = fields.get(i);
      if (field != null) {
        field.encode(bitString);
      } else {
        throw new EncodingException("Field not found: '" + fieldNames.get(i) + "'");
      }
    }

    return bitString;
  }

  public void decode(BitString bitString, EncodableBitStringFields fields) {
    int index = 0;
    List<String> fieldNames = fields.getNames();
    for (int i = 0; i < fieldNames.size(); i++) {
      AbstractEncodableBitStringDataType<?> field = fields.get(i);
      if (field != null) {
        try {
          BitString substring = field.substring(bitString, index);
          field.decode(substring);
          index += substring.length();
        } catch (SubstringException e) {
          if(field.getHardFailIfMissing()) {
            throw new DecodingException("Unable to decode " + fieldNames.get(i), e);
          } else {
            return;
          }
        } catch (Exception e) {
          throw new DecodingException("Unable to decode " + fieldNames.get(i), e);
        }
      } else {
        throw new DecodingException("Field not found: '" + fieldNames.get(i) + "'");
      }
    }
  }
}
