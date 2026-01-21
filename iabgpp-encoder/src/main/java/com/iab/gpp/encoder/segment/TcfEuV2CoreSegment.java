package com.iab.gpp.encoder.segment;

import java.time.Instant;
import com.iab.gpp.encoder.base64.TraditionalBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.EncodableArrayOfFixedIntegerRanges;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableDatetime;
import com.iab.gpp.encoder.datatype.EncodableFixedBitfield;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedString;
import com.iab.gpp.encoder.datatype.EncodableOptimizedFixedRange;
import com.iab.gpp.encoder.field.TcfEuV2Field;
import com.iab.gpp.encoder.section.TcfEuV2;

public final class TcfEuV2CoreSegment extends AbstractBase64Segment<TcfEuV2Field> {

  public TcfEuV2CoreSegment() {
    super(TcfEuV2Field.TCFEUV2_CORE_SEGMENT_FIELD_NAMES, TraditionalBase64UrlEncoder.getInstance());
    // NOTE: TcfEuV2.setFieldValue records modifications
    Instant date = Instant.EPOCH;

    fields.put(TcfEuV2Field.VERSION, new EncodableFixedInteger(6, TcfEuV2.VERSION));
    fields.put(TcfEuV2Field.CREATED, new EncodableDatetime(date));
    fields.put(TcfEuV2Field.LAST_UPDATED, new EncodableDatetime(date));
    fields.put(TcfEuV2Field.CMP_ID, new EncodableFixedInteger(12, 0));
    fields.put(TcfEuV2Field.CMP_VERSION, new EncodableFixedInteger(12, 0));
    fields.put(TcfEuV2Field.CONSENT_SCREEN, new EncodableFixedInteger(6, 0));
    fields.put(TcfEuV2Field.CONSENT_LANGUAGE, new EncodableFixedString(2, "EN"));
    fields.put(TcfEuV2Field.VENDOR_LIST_VERSION, new EncodableFixedInteger(12, 0));
    fields.put(TcfEuV2Field.POLICY_VERSION, new EncodableFixedInteger(6, 2));
    fields.put(TcfEuV2Field.IS_SERVICE_SPECIFIC, new EncodableBoolean(false));
    fields.put(TcfEuV2Field.USE_NON_STANDARD_STACKS, new EncodableBoolean(false));
    fields.put(TcfEuV2Field.SPECIAL_FEATURE_OPTINS, new EncodableFixedBitfield(12));
    fields.put(TcfEuV2Field.PURPOSE_CONSENTS, new EncodableFixedBitfield(24));
    fields.put(TcfEuV2Field.PURPOSE_LEGITIMATE_INTERESTS, new EncodableFixedBitfield(24));
    fields.put(TcfEuV2Field.PURPOSE_ONE_TREATMENT, new EncodableBoolean(false));
    fields.put(TcfEuV2Field.PUBLISHER_COUNTRY_CODE, new EncodableFixedString(2, "AA"));
    fields.put(TcfEuV2Field.VENDOR_CONSENTS, new EncodableOptimizedFixedRange());
    fields.put(TcfEuV2Field.VENDOR_LEGITIMATE_INTERESTS, new EncodableOptimizedFixedRange());

    fields.put(TcfEuV2Field.PUBLISHER_RESTRICTIONS, new EncodableArrayOfFixedIntegerRanges(6, 2, false));
  }

}
