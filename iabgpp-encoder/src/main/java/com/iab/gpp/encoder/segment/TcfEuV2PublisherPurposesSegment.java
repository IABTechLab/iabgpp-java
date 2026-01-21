package com.iab.gpp.encoder.segment;

import java.util.function.IntSupplier;
import com.iab.gpp.encoder.base64.TraditionalBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.EncodableFixedBitfield;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFlexibleBitfield;
import com.iab.gpp.encoder.field.TcfEuV2Field;

public final class TcfEuV2PublisherPurposesSegment extends AbstractBase64Segment<TcfEuV2Field> {

  public TcfEuV2PublisherPurposesSegment() {
    super(TcfEuV2Field.TCFEUV2_PUBLISHER_PURPOSES_SEGMENT_FIELD_NAMES, TraditionalBase64UrlEncoder.getInstance());
    fields.put(TcfEuV2Field.PUBLISHER_PURPOSES_SEGMENT_TYPE, new EncodableFixedInteger(3, 3));
    fields.put(TcfEuV2Field.PUBLISHER_CONSENTS, new EncodableFixedBitfield(24));
    fields.put(TcfEuV2Field.PUBLISHER_LEGITIMATE_INTERESTS, new EncodableFixedBitfield(24));

    EncodableFixedInteger numCustomPurposes = new EncodableFixedInteger(6, 0);
    fields.put(TcfEuV2Field.NUM_CUSTOM_PURPOSES, numCustomPurposes);

    IntSupplier getLengthSupplier = new IntSupplier() {

      @Override
      public int getAsInt() {
        return numCustomPurposes.getValue();
      }

    };

    fields.put(TcfEuV2Field.PUBLISHER_CUSTOM_CONSENTS,
        new EncodableFlexibleBitfield(getLengthSupplier));

    fields.put(TcfEuV2Field.PUBLISHER_CUSTOM_LEGITIMATE_INTERESTS,
        new EncodableFlexibleBitfield(getLengthSupplier));
  }

}
