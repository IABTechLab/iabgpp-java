package com.iab.gpp.encoder.segment;

import java.util.function.IntSupplier;
import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringEncoder;
import com.iab.gpp.encoder.datatype.EncodableFixedBitfield;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFlexibleBitfield;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.field.EncodableBitStringFields;
import com.iab.gpp.encoder.field.TcfCaV1Field;

public class TcfCaV1PublisherPurposesSegment extends AbstractLazilyEncodableSegment<EncodableBitStringFields> {

  private static final AbstractBase64UrlEncoder base64UrlEncoder = CompressedBase64UrlEncoder.getInstance();
  private static final BitStringEncoder bitStringEncoder = BitStringEncoder.getInstance();

  public TcfCaV1PublisherPurposesSegment() {
    super();
  }

  public TcfCaV1PublisherPurposesSegment(String encodedString) {
    super();
    this.decode(encodedString);
  }

  @Override
  protected EncodableBitStringFields initializeFields() {
    EncodableBitStringFields fields = new EncodableBitStringFields(TcfCaV1Field.TCFCAV1_PUBLISHER_PURPOSES_SEGMENT_FIELD_NAMES);
    fields.put(TcfCaV1Field.PUB_PURPOSES_SEGMENT_TYPE, new EncodableFixedInteger(3, 3));
    fields.put(TcfCaV1Field.PUB_PURPOSES_EXPRESS_CONSENT, new EncodableFixedBitfield(24));
    fields.put(TcfCaV1Field.PUB_PURPOSES_IMPLIED_CONSENT, new EncodableFixedBitfield(24));

    EncodableFixedInteger numCustomPurposes = new EncodableFixedInteger(6, 0);
    fields.put(TcfCaV1Field.NUM_CUSTOM_PURPOSES, numCustomPurposes);

    IntSupplier getLengthSupplier = new IntSupplier() {

      @Override
      public int getAsInt() {
        return numCustomPurposes.getValue();
      }

    };

    fields.put(TcfCaV1Field.CUSTOM_PURPOSES_EXPRESS_CONSENT,
        new EncodableFlexibleBitfield(getLengthSupplier));

    fields.put(TcfCaV1Field.CUSTOM_PURPOSES_IMPLIED_CONSENT,
        new EncodableFlexibleBitfield(getLengthSupplier));
    return fields;
  }

  @Override
  protected StringBuilder encodeSegment(EncodableBitStringFields fields) {
    BitStringBuilder bitString = bitStringEncoder.encode(fields);
    return base64UrlEncoder.encode(bitString);
  }

  @Override
  protected void decodeSegment(CharSequence encodedString, EncodableBitStringFields fields) {
    if(encodedString == null || encodedString.length() == 0) {
      this.fields.reset(fields);
    }
    try {
      BitString bitString = base64UrlEncoder.decode(encodedString);
      bitStringEncoder.decode(bitString, fields);
    } catch (Exception e) {
      throw new DecodingException("Unable to decode TcfCaV1PublisherPurposesSegment '" + encodedString + "'", e);
    }
  }
}
