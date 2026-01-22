package com.iab.gpp.encoder.segment;

import java.time.Instant;
import com.iab.gpp.encoder.datatype.EncodableArrayOfFixedIntegerRanges;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableDatetime;
import com.iab.gpp.encoder.datatype.EncodableFixedBitfield;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedString;
import com.iab.gpp.encoder.datatype.EncodableOptimizedFixedRange;
import com.iab.gpp.encoder.field.TcfCaV1Field;
import com.iab.gpp.encoder.section.TcfCaV1;

public final class TcfCaV1CoreSegment extends AbstractBase64Segment<TcfCaV1Field> {

  public TcfCaV1CoreSegment() {
    super(TcfCaV1Field.TCFCAV1_CORE_SEGMENT_FIELD_NAMES);
    // NOTE: TcfCaV1.setFieldValue records modifications
    Instant date = Instant.EPOCH;
    initialize(TcfCaV1Field.VERSION, new EncodableFixedInteger(6, TcfCaV1.VERSION));
    initialize(TcfCaV1Field.CREATED, new EncodableDatetime(date));
    initialize(TcfCaV1Field.LAST_UPDATED, new EncodableDatetime(date));
    initialize(TcfCaV1Field.CMP_ID, new EncodableFixedInteger(12, 0));
    initialize(TcfCaV1Field.CMP_VERSION, new EncodableFixedInteger(12, 0));
    initialize(TcfCaV1Field.CONSENT_SCREEN, new EncodableFixedInteger(6, 0));
    initialize(TcfCaV1Field.CONSENT_LANGUAGE, new EncodableFixedString(2, "EN"));
    initialize(TcfCaV1Field.VENDOR_LIST_VERSION, new EncodableFixedInteger(12, 0));
    initialize(TcfCaV1Field.TCF_POLICY_VERSION, new EncodableFixedInteger(6, 2));
    initialize(TcfCaV1Field.USE_NON_STANDARD_STACKS, new EncodableBoolean(false));
    initialize(TcfCaV1Field.SPECIAL_FEATURE_EXPRESS_CONSENT, new EncodableFixedBitfield(12));
    initialize(TcfCaV1Field.PURPOSES_EXPRESS_CONSENT, new EncodableFixedBitfield(24));
    initialize(TcfCaV1Field.PURPOSES_IMPLIED_CONSENT, new EncodableFixedBitfield(24));
    initialize(TcfCaV1Field.VENDOR_EXPRESS_CONSENT, new EncodableOptimizedFixedRange());
    initialize(TcfCaV1Field.VENDOR_IMPLIED_CONSENT, new EncodableOptimizedFixedRange());
    initialize(TcfCaV1Field.PUB_RESTRICTIONS, new EncodableArrayOfFixedIntegerRanges(6, 2, false));
  }

}
