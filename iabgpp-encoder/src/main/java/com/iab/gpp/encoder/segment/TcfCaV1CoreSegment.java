package com.iab.gpp.encoder.segment;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.bitstring.BitStringEncoder;
import com.iab.gpp.encoder.datatype.EncodableArrayOfFixedIntegerRanges;
import com.iab.gpp.encoder.datatype.EncodableArrayOfOptimizedFibonacciRanges;
import com.iab.gpp.encoder.datatype.EncodableBoolean;
import com.iab.gpp.encoder.datatype.EncodableDatetime;
import com.iab.gpp.encoder.datatype.EncodableFixedBitfield;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedString;
import com.iab.gpp.encoder.datatype.EncodableOptimizedFibonacciRange;
import com.iab.gpp.encoder.datatype.EncodableOptimizedFixedRange;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.field.EncodableBitStringFields;
import com.iab.gpp.encoder.field.TcfCaV1Field;
import com.iab.gpp.encoder.section.TcfCaV1;

public class TcfCaV1CoreSegment extends AbstractLazilyEncodableSegment<EncodableBitStringFields> {

  private AbstractBase64UrlEncoder base64UrlEncoder = CompressedBase64UrlEncoder.getInstance();
  private BitStringEncoder bitStringEncoder = BitStringEncoder.getInstance();

  public TcfCaV1CoreSegment() {
    super();
  }

  public TcfCaV1CoreSegment(String encodedString) {
    super();
    this.decode(encodedString);
  }

  @Override
  public List<String> getFieldNames() {
    return TcfCaV1Field.TCFCAV1_CORE_SEGMENT_FIELD_NAMES;
  }

  @Override
  protected EncodableBitStringFields initializeFields() {
    return buildFields(false);
  }

  /**
   * Builds the core field set. When {@code legacy} is true the OptimizedRange / N-ArrayOfRanges
   * fields use the pre-fix fixed-integer encoders; otherwise they use the spec-compliant Fibonacci
   * encoders. The legacy field set is only used to decode strings produced by the older encoder
   * (see {@link #decodeSegment}).
   */
  private EncodableBitStringFields buildFields(boolean legacy) {
    ZonedDateTime date = ZonedDateTime.now();

    EncodableBitStringFields fields = new EncodableBitStringFields();
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
    fields.put(TcfCaV1Field.SPECIAL_FEATURE_EXPRESS_CONSENT, new EncodableFixedBitfield(
        Arrays.asList(false, false, false, false, false, false, false, false, false, false, false, false)));
    fields.put(TcfCaV1Field.PURPOSES_EXPRESS_CONSENT,
        new EncodableFixedBitfield(Arrays.asList(false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false, false, false)));
    fields.put(TcfCaV1Field.PURPOSES_IMPLIED_CONSENT,
        new EncodableFixedBitfield(Arrays.asList(false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false, false, false, false, false, false, false)));

    if (legacy) {
      fields.put(TcfCaV1Field.VENDOR_EXPRESS_CONSENT, new EncodableOptimizedFixedRange(new ArrayList<>()));
      fields.put(TcfCaV1Field.VENDOR_IMPLIED_CONSENT, new EncodableOptimizedFixedRange(new ArrayList<>()));
      fields.put(TcfCaV1Field.PUB_RESTRICTIONS, new EncodableArrayOfFixedIntegerRanges(6, 2, new ArrayList<>(), false));
    } else {
      fields.put(TcfCaV1Field.VENDOR_EXPRESS_CONSENT, new EncodableOptimizedFibonacciRange(new ArrayList<>()));
      fields.put(TcfCaV1Field.VENDOR_IMPLIED_CONSENT, new EncodableOptimizedFibonacciRange(new ArrayList<>()));
      fields.put(TcfCaV1Field.PUB_RESTRICTIONS,
          new EncodableArrayOfOptimizedFibonacciRanges(6, 2, new ArrayList<>(), false));
    }
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
    if (encodedString == null || encodedString.isEmpty()) {
      this.fields.reset(fields);
    }
    try {
      String bitString = base64UrlEncoder.decode(encodedString);

      // Prefer the spec-compliant (Fibonacci OptimizedRange) interpretation. If that doesn't
      // round-trip back to the input, fall back to the legacy (fixed-range) interpretation used by
      // the pre-fix encoder. This keeps older strings decodable; re-encoding always migrates them
      // to the spec-compliant format because the fields decode into the Fibonacci datatypes.
      if (tryDecode(bitString, fields, false)) {
        return;
      }
      if (tryDecode(bitString, fields, true)) {
        return;
      }

      // Neither interpretation round-trips cleanly; decode with the current interpretation as a
      // best effort and surface any decoding error.
      bitStringEncoder.decode(bitString, getFieldNames(), fields);
    } catch (Exception e) {
      throw new DecodingException("Unable to decode TcfCaV1CoreSegment '" + encodedString + "'", e);
    }
  }

  /**
   * Attempts to decode {@code bitString} using either the current or legacy field set and verifies
   * the result by re-encoding it: if the re-encoded bits are a prefix of the decoded bits (the tail
   * being base64 padding), the interpretation produced the string. On success the decoded values
   * are copied into {@code targetFields} (which always use the current encoders) so that any
   * subsequent re-encode emits the spec-compliant format.
   */
  private boolean tryDecode(String bitString, EncodableBitStringFields targetFields, boolean legacy) {
    try {
      EncodableBitStringFields candidate = buildFields(legacy);
      bitStringEncoder.decode(bitString, getFieldNames(), candidate);
      String reEncoded = bitStringEncoder.encode(candidate, getFieldNames());
      if (bitString.startsWith(reEncoded)) {
        for (String fieldName : getFieldNames()) {
          targetFields.get(fieldName).setValue(candidate.get(fieldName).getValue());
        }
        return true;
      }
    } catch (Exception e) {
      // This interpretation does not apply; the caller will try the next one.
    }
    return false;
  }
}
