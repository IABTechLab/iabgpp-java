package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.datatype.EncodableFibonacciIntegerRange;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.field.EncodableBitStringFields;
import com.iab.gpp.encoder.field.HeaderV1Field;
import com.iab.gpp.encoder.section.HeaderV1;

public final class HeaderV1CoreSegment extends AbstractLazilyEncodableSegment<HeaderV1Field, EncodableBitStringFields<HeaderV1Field>> {

  private static final AbstractBase64UrlEncoder base64UrlEncoder = CompressedBase64UrlEncoder.getInstance();

  public HeaderV1CoreSegment() {
    super();
  }

  public HeaderV1CoreSegment(String encodedString) {
    super();
    this.decode(encodedString);
  }

  @Override
  protected EncodableBitStringFields<HeaderV1Field> initializeFields() {
    EncodableBitStringFields<HeaderV1Field> fields = new EncodableBitStringFields<>(HeaderV1Field.HEADER_CORE_SEGMENT_FIELD_NAMES);
    fields.put(HeaderV1Field.ID, new EncodableFixedInteger(6, HeaderV1.ID));
    fields.put(HeaderV1Field.VERSION, new EncodableFixedInteger(6, HeaderV1.VERSION));
    fields.put(HeaderV1Field.SECTION_IDS, new EncodableFibonacciIntegerRange());
    return fields;
  }

  @Override
  protected StringBuilder encodeSegment(EncodableBitStringFields<HeaderV1Field> fields) {
    BitStringBuilder bitString = fields.encode();
    return base64UrlEncoder.encode(bitString);
  }

  @Override
  protected void decodeSegment(CharSequence encodedString, EncodableBitStringFields<HeaderV1Field> fields) {
    if(encodedString == null || encodedString.length() == 0) {
      this.fields.reset(fields);
    }
    try {
      BitString bitString = base64UrlEncoder.decode(encodedString);
      this.fields.decode(bitString);
    } catch (Exception e) {
      throw new DecodingException("Unable to decode HeaderV1CoreSegment '" + encodedString + "'", e);
    }
  }

}
