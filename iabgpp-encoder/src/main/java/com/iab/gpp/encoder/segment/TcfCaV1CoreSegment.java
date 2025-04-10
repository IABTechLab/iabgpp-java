package com.iab.gpp.encoder.segment;

import java.time.ZonedDateTime;
import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
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
import com.iab.gpp.encoder.field.TcfCaV1Field;
import com.iab.gpp.encoder.section.TcfCaV1;

public class TcfCaV1CoreSegment extends AbstractLazilyEncodableSegment<EncodableBitStringFields> {

  private static final AbstractBase64UrlEncoder base64UrlEncoder = CompressedBase64UrlEncoder.getInstance();
  private static final BitStringEncoder bitStringEncoder = BitStringEncoder.getInstance();

  public TcfCaV1CoreSegment() {
    super();
  }

  public TcfCaV1CoreSegment(String encodedString) {
    super();
    this.decode(encodedString);
  }

  @Override
  protected EncodableBitStringFields initializeFields() {
    ZonedDateTime date = ZonedDateTime.now();

    EncodableBitStringFields fields = new EncodableBitStringFields(TcfCaV1Field.TCFCAV1_CORE_SEGMENT_FIELD_NAMES);
    fields.put(TcfCaV1Field.VERSION, new EncodableFixedInteger(6, TcfCaV1.VERSION));
    fields.put(TcfCaV1Field.CREATED, new EncodableDatetime(date));
    fields.put(TcfCaV1Field.LAST_UPDATED, new EncodableDatetime(date));
    fields.put(TcfCaV1Field.CMP_ID, new EncodableFixedInteger(12, 0));
    fields.put(TcfCaV1Field.CMP_VERSION, new EncodableFixedInteger(12, 0));
    fields.put(TcfCaV1Field.CONSENT_SCREEN, new EncodableFixedInteger(6, 0));
    fields.put(TcfCaV1Field.CONSENT_LANGUAGE, new EncodableFixedString(2, "EN"));
    fields.put(TcfCaV1Field.VENDOR_LIST_VERSION, new EncodableFixedInteger(12, 0));
    fields.put(TcfCaV1Field.TCF_POLICY_VERSION, new EncodableFixedInteger(6, 2));
    fields.put(TcfCaV1Field.USE_NON_STANDARD_STACKS, new EncodableBoolean(false));
    fields.put(TcfCaV1Field.SPECIAL_FEATURE_EXPRESS_CONSENT, new EncodableFixedBitfield(12));
    fields.put(TcfCaV1Field.PURPOSES_EXPRESS_CONSENT, new EncodableFixedBitfield(24));
    fields.put(TcfCaV1Field.PURPOSES_IMPLIED_CONSENT, new EncodableFixedBitfield(24));
    fields.put(TcfCaV1Field.VENDOR_EXPRESS_CONSENT, new EncodableOptimizedFixedRange());
    fields.put(TcfCaV1Field.VENDOR_IMPLIED_CONSENT, new EncodableOptimizedFixedRange());
    fields.put(TcfCaV1Field.PUB_RESTRICTIONS, new EncodableArrayOfFixedIntegerRanges(6, 2, false));
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
      throw new DecodingException("Unable to decode TcfCaV1CoreSegment '" + encodedString + "'", e);
    }
  }
}
