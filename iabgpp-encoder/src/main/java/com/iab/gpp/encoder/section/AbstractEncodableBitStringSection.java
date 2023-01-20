package com.iab.gpp.encoder.section;

import java.util.Map;
import com.iab.gpp.encoder.datatype.AbstractEncodableBitStringDataType;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;

public abstract class AbstractEncodableBitStringSection implements EncodableSection {
  protected Map<String, AbstractEncodableBitStringDataType<?>> fields;
  protected String[] fieldOrder;

  @Override
  public boolean hasField(String fieldName) {
    return this.fields.containsKey(fieldName);
  }

  @Override
  public Object getFieldValue(String fieldName) {
    if (this.fields.containsKey(fieldName)) {
      return this.fields.get(fieldName).getValue();
    } else {
      return null;
    }
  }

  @Override
  public void setFieldValue(String fieldName, Object value) throws InvalidFieldException {
    if (this.fields.containsKey(fieldName)) {
      this.fields.get(fieldName).setValue(value);
    } else {
      throw new InvalidFieldException(fieldName + " not found");
    }
  }

  public String[] getFieldOrder() {
    return this.fieldOrder;
  }

  public String encodeToBitString() throws EncodingException {
    String bitString = "";
    for (int i = 0; i < this.fieldOrder.length; i++) {
      String fieldName = this.fieldOrder[i];
      if (this.fields.containsKey(fieldName)) {
        AbstractEncodableBitStringDataType<?> field = this.fields.get(fieldName);
        bitString += field.encode();
      } else {
        throw new EncodingException("Field not found: '" + fieldName + "'");
      }
    }

    return bitString;
  }

  public void decodeFromBitString(String bitString) throws DecodingException {
    int index = 0;
    for (int i = 0; i < this.fieldOrder.length; i++) {
      String fieldName = this.fieldOrder[i];
      if (this.fields.containsKey(fieldName)) {
        AbstractEncodableBitStringDataType<?> field = this.fields.get(fieldName);
        String substring = field.substring(bitString, index);
        field.decode(substring);
        index += substring.length();
      } else {
        throw new DecodingException("Field not found: '" + fieldName + "'");
      }
    }
  }

  @Override
  public abstract String encode() throws EncodingException;

  @Override
  public abstract void decode(String encodedString) throws DecodingException;

  @Override
  public abstract int getId();

  @Override
  public abstract String getName();
}
