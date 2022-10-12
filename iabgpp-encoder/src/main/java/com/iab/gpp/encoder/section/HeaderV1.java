package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.iab.gpp.encoder.datatype.encoder.Base64UrlEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.datatype.EncodableFibonacciIntegerRange;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.field.HeaderV1Field;

public class HeaderV1 extends AbstractEncodableBitStringSection {
  public static int ID = 3;
  public static int VERSION = 1;
  public static String NAME = "header";

  public HeaderV1() {
    initFields();
  }

  public HeaderV1(String encodedString) throws DecodingException {
    initFields();

    if (encodedString != null && encodedString.length() > 0) {
      this.decode(encodedString);
    }
  }

  private void initFields() {
    fields = new HashMap<>();
    fields.put(HeaderV1Field.ID, new EncodableFixedInteger(6, HeaderV1.ID));
    fields.put(HeaderV1Field.VERSION, new EncodableFixedInteger(6, HeaderV1.VERSION));
    fields.put(HeaderV1Field.SECTION_IDS, new EncodableFibonacciIntegerRange(new ArrayList<>()));

    //@formatter:off
    fieldOrder = new String[] {
        HeaderV1Field.ID, 
        HeaderV1Field.VERSION,
        HeaderV1Field.SECTION_IDS
    };
    //@formatter:on
  }

  @Override
  public String encode() throws EncodingException {
    String bitString = this.encodeToBitString();
    String encodedString = Base64UrlEncoder.encode(bitString);
    return encodedString;
  }

  @Override
  public void decode(String encodedString) throws DecodingException {
    String bitString = Base64UrlEncoder.decode(encodedString);
    this.decodeFromBitString(bitString);
  }

  @Override
  public int getId() {
    return HeaderV1.ID;
  }

  @Override
  public String getName() {
    return HeaderV1.NAME;
  }

  public Integer getVersion() {
    return (Integer) this.fields.get(HeaderV1Field.VERSION).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getSectionsIds() {
    return (List<Integer>) this.fields.get(HeaderV1Field.SECTION_IDS).getValue();
  }
}
