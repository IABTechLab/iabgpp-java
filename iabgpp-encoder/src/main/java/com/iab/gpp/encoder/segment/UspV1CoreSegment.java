package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.field.UspV1Field;

public final class UspV1CoreSegment extends AbstractLazilyEncodableSegment<UspV1Field> {

  public UspV1CoreSegment() {
    super(UspV1Field.USPV1_CORE_SEGMENT_FIELD_NAMES, false);
  }

  @Override
  protected CharSequence doEncode() {
    StringBuilder str = new StringBuilder();
    str.append(getFieldValue(UspV1Field.VERSION));
    str.append(getFieldValue(UspV1Field.NOTICE));
    str.append(getFieldValue(UspV1Field.OPT_OUT_SALE));
    str.append(getFieldValue(UspV1Field.LSPA_COVERED));
    return str;
  }

  @Override
  protected void doDecode(CharSequence encodedString) {
    if (encodedString == null || encodedString.length() != 4) {
      throw new DecodingException("Invalid uspv1 string: '" + encodedString + "'");
    }

    try {
      setFieldValueUnsafe(UspV1Field.VERSION, (int) (encodedString.charAt(0) - '0'));
      setFieldValueUnsafe(UspV1Field.NOTICE, encodedString.charAt(1));
      setFieldValueUnsafe(UspV1Field.OPT_OUT_SALE, encodedString.charAt(2));
      setFieldValueUnsafe(UspV1Field.LSPA_COVERED, encodedString.charAt(3));
    } catch (Exception e) {
      throw new DecodingException("Unable to decode UspV1CoreSegment '" + encodedString + "'", e);
    }
  }
}
