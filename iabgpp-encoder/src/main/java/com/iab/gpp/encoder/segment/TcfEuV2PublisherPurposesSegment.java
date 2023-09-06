package com.iab.gpp.encoder.segment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;
import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.TraditionalBase64UrlEncoder;
import com.iab.gpp.encoder.bitstring.BitStringEncoder;
import com.iab.gpp.encoder.datatype.EncodableFixedBitfield;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFlexibleBitfield;
import com.iab.gpp.encoder.field.EncodableBitStringFields;
import com.iab.gpp.encoder.field.TcfEuV2Field;

public class TcfEuV2PublisherPurposesSegment extends AbstractLazilyEncodableSegment<EncodableBitStringFields> {

  private AbstractBase64UrlEncoder base64UrlEncoder = TraditionalBase64UrlEncoder.getInstance();
  private BitStringEncoder bitStringEncoder = BitStringEncoder.getInstance();

  public TcfEuV2PublisherPurposesSegment() {
    super();
  }

  public TcfEuV2PublisherPurposesSegment(String encodedString) {
    super();
    this.decode(encodedString);
  }

  @Override
  public List<String> getFieldNames() {
    return TcfEuV2Field.TCFEUV2_PUBLISHER_PURPOSES_SEGMENT_FIELD_NAMES;
  }

  @Override
  protected EncodableBitStringFields initializeFields() {
    EncodableBitStringFields fields = new EncodableBitStringFields();
    fields.put(TcfEuV2Field.PUBLISHER_PURPOSES_SEGMENT_TYPE, new EncodableFixedInteger(3, 3));
    fields.put(TcfEuV2Field.PUBLISHER_CONSENTS,
        new EncodableFixedBitfield(Arrays.asList(false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false, false, false)));
    fields.put(TcfEuV2Field.PUBLISHER_LEGITIMATE_INTERESTS,
        new EncodableFixedBitfield(Arrays.asList(false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false, false, false)));

    EncodableFixedInteger numCustomPurposes = new EncodableFixedInteger(6, 0);
    fields.put(TcfEuV2Field.NUM_CUSTOM_PURPOSES, numCustomPurposes);

    IntSupplier getLengthSupplier = new IntSupplier() {

      @Override
      public int getAsInt() {
        return numCustomPurposes.getValue();
      }

    };

    fields.put(TcfEuV2Field.PUBLISHER_CUSTOM_CONSENTS,
        new EncodableFlexibleBitfield(getLengthSupplier, new ArrayList<>()));

    fields.put(TcfEuV2Field.PUBLISHER_CUSTOM_LEGITIMATE_INTERESTS,
        new EncodableFlexibleBitfield(getLengthSupplier, new ArrayList<>()));
    return fields;
  }

  @Override
  protected String encodeSegment(EncodableBitStringFields fields) {
    String bitString = bitStringEncoder.encode(fields, getFieldNames());
    String encodedString = base64UrlEncoder.encode(bitString);
    return encodedString;
  }

  @Override
  protected void decodeSegment(String encodedString, EncodableBitStringFields fields) {
    if(encodedString == null || encodedString.isEmpty()) {
      this.fields.reset(fields);
    }
    String bitString = base64UrlEncoder.decode(encodedString);
    bitStringEncoder.decode(bitString, getFieldNames(), fields);
  }
}
