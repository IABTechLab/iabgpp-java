package com.iab.gpp.encoder.bitstring;

import java.util.List;
import com.iab.gpp.encoder.datatype.AbstractEncodableBitStringDataType;
import com.iab.gpp.encoder.datatype.SubstringException;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.EncodableBitStringFields;

public class BitStringEncoder {

  private static final BitStringEncoder instance = new BitStringEncoder();
  
  private BitStringEncoder() {
    
  }
  
  public static BitStringEncoder getInstance() {
    return instance;
  }
  
  public String encode(EncodableBitStringFields fields, List<String> fieldNames) {
    StringBuilder bitString = new StringBuilder();
    for (int i = 0; i < fieldNames.size(); i++) {
      String fieldName = fieldNames.get(i);
      AbstractEncodableBitStringDataType<?> field = fields.get(fieldName);
      if (field != null) {
        bitString.append(field.encode());
      } else {
        throw new EncodingException("Field not found: '" + fieldName + "'");
      }
    }

    return bitString.toString();
  }

  public void decode(BitString bitString, List<String> fieldNames, EncodableBitStringFields fields) {
    int index = 0;
    for (int i = 0; i < fieldNames.size(); i++) {
      String fieldName = fieldNames.get(i);
      AbstractEncodableBitStringDataType<?> field = fields.get(fieldName);
      if (field != null) {
        try {
          BitString substring = field.substring(bitString, index);
          field.decode(substring);
          index += substring.length();
        } catch (SubstringException e) {
          if(field.getHardFailIfMissing()) {
            throw new DecodingException("Unable to decode " + fieldName, e);
          } else {
            return;
          }
        } catch (Exception e) {
          throw new DecodingException("Unable to decode " + fieldName, e);
        }
      } else {
        throw new DecodingException("Field not found: '" + fieldName + "'");
      }
    }
  }
}
