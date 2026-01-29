package com.iab.gpp.encoder.segment;

import java.util.function.IntSupplier;
import com.iab.gpp.encoder.datatype.EncodableFixedBitfield;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFlexibleBitfield;
import com.iab.gpp.encoder.field.TcfCaV1Field;

public final class TcfCaV1PublisherPurposesSegment extends AbstractBase64Segment<TcfCaV1Field> {

  public TcfCaV1PublisherPurposesSegment() {
    super(TcfCaV1Field.TCFCAV1_PUBLISHER_PURPOSES_SEGMENT_FIELD_NAMES);
    initialize(TcfCaV1Field.PUB_PURPOSES_SEGMENT_TYPE, new EncodableFixedInteger(3, 3));
    initialize(TcfCaV1Field.PUB_PURPOSES_EXPRESS_CONSENT, new EncodableFixedBitfield(24));
    initialize(TcfCaV1Field.PUB_PURPOSES_IMPLIED_CONSENT, new EncodableFixedBitfield(24));

    EncodableFixedInteger numCustomPurposes = new EncodableFixedInteger(6, 0);
    initialize(TcfCaV1Field.NUM_CUSTOM_PURPOSES, numCustomPurposes);

    IntSupplier getLengthSupplier = new IntSupplier() {

      @Override
      public int getAsInt() {
        return numCustomPurposes.getValue();
      }

    };

    initialize(TcfCaV1Field.CUSTOM_PURPOSES_EXPRESS_CONSENT,
        new EncodableFlexibleBitfield(getLengthSupplier));

    initialize(TcfCaV1Field.CUSTOM_PURPOSES_IMPLIED_CONSENT,
        new EncodableFlexibleBitfield(getLengthSupplier));
  }

}
