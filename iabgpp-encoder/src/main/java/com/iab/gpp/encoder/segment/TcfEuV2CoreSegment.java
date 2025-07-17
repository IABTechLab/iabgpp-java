package com.iab.gpp.encoder.segment;

import java.time.Instant;
import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.TraditionalBase64UrlEncoder;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringEncoder;
import com.iab.gpp.encoder.datatype.EncodableArrayOfFixedIntegerRanges;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableDatetime;
import com.iab.gpp.encoder.datatype.EncodableFixedBitfield;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedString;
import com.iab.gpp.encoder.datatype.EncodableOptimizedFixedRange;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.field.EncodableBitStringFields;
import com.iab.gpp.encoder.field.TcfEuV2Field;
import com.iab.gpp.encoder.section.TcfEuV2;

public final class TcfEuV2CoreSegment extends AbstractLazilyEncodableSegment<EncodableBitStringFields> {

  private static final AbstractBase64UrlEncoder base64UrlEncoder = TraditionalBase64UrlEncoder.getInstance();
  private static final BitStringEncoder bitStringEncoder = BitStringEncoder.getInstance();

  public TcfEuV2CoreSegment() {
    super();
  }

  public TcfEuV2CoreSegment(String encodedString) {
    super();
    this.decode(encodedString);
  }

  @Override
  protected EncodableBitStringFields initializeFields() {
    // NOTE: TcfEuV2.setFieldValue records modifications
    Instant date = Instant.EPOCH;

    EncodableBitStringFields fields = new EncodableBitStringFields(TcfEuV2Field.TCFEUV2_CORE_SEGMENT_FIELD_NAMES);
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
      throw new DecodingException("Unable to decode TcfEuV2CoreSegment '" + encodedString + "'", e);
    }
  }
}
