package com.iab.gpp.encoder.segment;

import java.util.ArrayList;
import java.util.List;
import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.bitstring.BitStringEncoder;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableOptimizedFibonacciRange;
import com.iab.gpp.encoder.datatype.EncodableOptimizedFixedRange;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.field.EncodableBitStringFields;
import com.iab.gpp.encoder.field.TcfCaV1Field;

public class TcfCaV1DisclosedVendorsSegment extends AbstractLazilyEncodableSegment<EncodableBitStringFields> {

  private AbstractBase64UrlEncoder base64UrlEncoder = CompressedBase64UrlEncoder.getInstance();
  private BitStringEncoder bitStringEncoder = BitStringEncoder.getInstance();

  public TcfCaV1DisclosedVendorsSegment() {
    super();
  }

  public TcfCaV1DisclosedVendorsSegment(String encodedString) {
    super();
    this.decode(encodedString);
  }

  @Override
  public List<String> getFieldNames() {
    return TcfCaV1Field.TCFCAV1_DISCLOSED_VENDORS_SEGMENT_FIELD_NAMES;
  }

  @Override
  protected EncodableBitStringFields initializeFields() {
    return buildFields(false);
  }

  /**
   * Builds the disclosed-vendors field set. When {@code legacy} is true the OptimizedRange field
   * uses the pre-fix fixed-integer encoder; otherwise it uses the spec-compliant Fibonacci encoder.
   * The legacy field set is only used to decode strings produced by the older encoder (see
   * {@link #decodeSegment}).
   */
  private EncodableBitStringFields buildFields(boolean legacy) {
    EncodableBitStringFields fields = new EncodableBitStringFields();
    fields.put(TcfCaV1Field.DISCLOSED_VENDORS_SEGMENT_TYPE, new EncodableFixedInteger(3, 1));
    if (legacy) {
      fields.put(TcfCaV1Field.DISCLOSED_VENDORS, new EncodableOptimizedFixedRange(new ArrayList<>()));
    } else {
      fields.put(TcfCaV1Field.DISCLOSED_VENDORS, new EncodableOptimizedFibonacciRange(new ArrayList<>()));
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

      // Prefer the spec-compliant (Fibonacci OptimizedRange) interpretation, falling back to the
      // legacy (fixed-range) interpretation used by the pre-fix encoder. Re-encoding always
      // migrates to the spec-compliant format because the values decode into the Fibonacci datatype.
      if (tryDecode(bitString, fields, false)) {
        return;
      }
      if (tryDecode(bitString, fields, true)) {
        return;
      }

      bitStringEncoder.decode(bitString, getFieldNames(), fields);
    } catch (Exception e) {
      throw new DecodingException("Unable to decode TcfCaV1DisclosedVendorsSegment '" + encodedString + "'", e);
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
