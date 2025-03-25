package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.datatype.AbstractEncodableBitStringDataType;
import com.iab.gpp.encoder.datatype.SubstringException;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.error.InvalidFieldException;

public abstract class AbstractEncodableSegmentedBitStringSection implements EncodableSection {
  protected Map<String, AbstractEncodableBitStringDataType<?>> fields;
  protected String[][] segments;

  @Override
  public boolean hasField(String fieldName) {
    return this.fields.containsKey(fieldName);
  }

  @Override
  public Object getFieldValue(String fieldName) {
    AbstractEncodableBitStringDataType<?> field = this.fields.get(fieldName);
    if (field != null) {
      return field.getValue();
    } else {
      return null;
    }
  }

  @Override
  public void setFieldValue(String fieldName, Object value) throws InvalidFieldException {
    AbstractEncodableBitStringDataType<?> field = this.fields.get(fieldName);
    if (field != null) {
      field.setValue(value);
    } else {
      throw new InvalidFieldException(fieldName + " not found");
    }
  }

  public String[][] getSegments() {
    return this.segments;
  }

  public List<String> encodeSegmentsToBitStrings() throws EncodingException {
    int length = this.segments.length;
    List<String> segmentBitStrings = new ArrayList<>(length);
    for (int i = 0; i < length; i++) {
      BitStringBuilder segmentBitString = new BitStringBuilder();
      for (int j = 0; j < this.segments[i].length; j++) {
        String fieldName = this.segments[i][j];
        AbstractEncodableBitStringDataType<?> field = this.fields.get(fieldName);
        if (field != null) {
          try {
            field.encode(segmentBitString);
          } catch (Exception e) {
            throw new EncodingException("Unable to encode " + fieldName, e);
          }
        } else {
          throw new EncodingException("Field not found: '" + fieldName + "'");
        }
      }
      segmentBitStrings.add(segmentBitString.build().toString());
    }

    return segmentBitStrings;
  }

  public void decodeSegmentsFromBitStrings(List<BitString> segmentBitStrings) throws DecodingException {
    for (int i = 0; i < this.segments.length && i < segmentBitStrings.size(); i++) {
      decodeSegmentFromBitString(segments[i], segmentBitStrings.get(i));
    }
  }

  private void decodeSegmentFromBitString(String[] segment, BitString segmentBitString) throws DecodingException {
    if (segmentBitString != null && segmentBitString.length() > 0) {
      int index = 0;
      for (int j = 0; j < segment.length; j++) {
        String fieldName = segment[j];
        AbstractEncodableBitStringDataType<?> field = this.fields.get(fieldName);
        if (field != null) {
          try {
            BitString substring = field.substring(segmentBitString, index);
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
  
  @Override
  public abstract String encode() throws EncodingException;

  @Override
  public abstract void decode(CharSequence encodedString) throws DecodingException;

  @Override
  public abstract int getId();

  @Override
  public abstract String getName();
}
