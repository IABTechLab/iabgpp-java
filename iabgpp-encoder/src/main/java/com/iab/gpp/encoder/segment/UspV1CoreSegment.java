package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.UnencodableCharacter;
import com.iab.gpp.encoder.datatype.UnencodableInteger;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.field.GenericFields;
import com.iab.gpp.encoder.field.UspV1Field;
import com.iab.gpp.encoder.section.UspV1;

public class UspV1CoreSegment extends AbstractLazilyEncodableSegment<GenericFields> {

  public UspV1CoreSegment() {
    super();
  }

  public UspV1CoreSegment(String encodedString) {
    super();
    this.decode(encodedString);
  }

  @Override
  protected GenericFields initializeFields() {
    GenericFields fields = new GenericFields(UspV1Field.USPV1_CORE_SEGMENT_FIELD_NAMES);
    fields.put(UspV1Field.VERSION, new UnencodableInteger(UspV1.VERSION));
    fields.put(UspV1Field.NOTICE, new UnencodableCharacter('-', (v -> v == 'Y' || v == 'N' || v == '-')));
    fields.put(UspV1Field.OPT_OUT_SALE, new UnencodableCharacter('-', (v -> v == 'Y' || v == 'N' || v == '-')));
    fields.put(UspV1Field.LSPA_COVERED, new UnencodableCharacter('-', (v -> v == 'Y' || v == 'N' || v == '-')));
    return fields;
  }

  @Override
  protected String encodeSegment(GenericFields fields) {
    StringBuilder str = new StringBuilder();
    str.append(fields.get(UspV1Field.VERSION).getValue());
    str.append(fields.get(UspV1Field.NOTICE).getValue());
    str.append(fields.get(UspV1Field.OPT_OUT_SALE).getValue());
    str.append(fields.get(UspV1Field.LSPA_COVERED).getValue());
    return str.toString();
  }

  @Override
  protected void decodeSegment(CharSequence encodedString, GenericFields fields) {
    if (encodedString == null || encodedString.length() != 4) {
      throw new DecodingException("Invalid uspv1 string: '" + encodedString + "'");
    }

    try {
      fields.get(UspV1Field.VERSION).setValue((int)(encodedString.charAt(0) - '0'));
      fields.get(UspV1Field.NOTICE).setValue(encodedString.charAt(1));
      fields.get(UspV1Field.OPT_OUT_SALE).setValue(encodedString.charAt(2));
      fields.get(UspV1Field.LSPA_COVERED).setValue(encodedString.charAt(3));
    } catch (Exception e) {
      throw new DecodingException("Unable to decode UspV1CoreSegment '" + encodedString + "'", e);
    }
  }

}

