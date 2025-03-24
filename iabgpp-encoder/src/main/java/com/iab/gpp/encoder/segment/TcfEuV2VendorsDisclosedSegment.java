package com.iab.gpp.encoder.segment;

import java.util.ArrayList;
import java.util.List;
import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.TraditionalBase64UrlEncoder;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringEncoder;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableOptimizedFixedRange;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.field.EncodableBitStringFields;
import com.iab.gpp.encoder.field.TcfEuV2Field;

public class TcfEuV2VendorsDisclosedSegment extends AbstractLazilyEncodableSegment<EncodableBitStringFields> {

  private static final AbstractBase64UrlEncoder base64UrlEncoder = TraditionalBase64UrlEncoder.getInstance();
  private static final BitStringEncoder bitStringEncoder = BitStringEncoder.getInstance();

  public TcfEuV2VendorsDisclosedSegment() {
    super();
  }

  public TcfEuV2VendorsDisclosedSegment(String encodedString) {
    super();
    this.decode(encodedString);
  }

  @Override
  public List<String> getFieldNames() {
    return TcfEuV2Field.TCFEUV2_VENDORS_DISCLOSED_SEGMENT_FIELD_NAMES;
  }

  @Override
  protected EncodableBitStringFields initializeFields() {
    EncodableBitStringFields fields = new EncodableBitStringFields();
    fields.put(TcfEuV2Field.VENDORS_DISCLOSED_SEGMENT_TYPE, new EncodableFixedInteger(3, 1));
    fields.put(TcfEuV2Field.VENDORS_DISCLOSED, new EncodableOptimizedFixedRange(new ArrayList<>(0)));
    return fields;
  }

  @Override
  protected String encodeSegment(EncodableBitStringFields fields) {
    String bitString = bitStringEncoder.encode(fields, getFieldNames());
    String encodedString = base64UrlEncoder.encode(bitString);
    return encodedString;
  }

  @Override
  protected void decodeSegment(CharSequence encodedString, EncodableBitStringFields fields) {
    if(encodedString == null || encodedString.length() == 0) {
      this.fields.reset(fields);
    }
    try {
      BitString bitString = base64UrlEncoder.decode(encodedString);
      bitStringEncoder.decode(bitString, getFieldNames(), fields);
    } catch (Exception e) {
      throw new DecodingException("Unable to decode TcfEuV2VendorsDisclosedSegment '" + encodedString + "'", e);
    }
  }
}
