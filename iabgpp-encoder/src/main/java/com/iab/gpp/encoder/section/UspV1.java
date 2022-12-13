package com.iab.gpp.encoder.section;

import java.util.HashMap;
import com.iab.gpp.encoder.datatype.encoder.Base64UrlEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.field.UspV1Field;

public class UspV1 extends AbstractEncodableBitStringSection {
  public static int ID = 6;
  public static int VERSION = 1;
  public static String NAME = "uspv1";

  public UspV1() {
    initFields();
  }

  public UspV1(String encodedString) throws DecodingException {
    initFields();

    if (encodedString != null && encodedString.length() > 0) {
      this.decode(encodedString);
    }
  }

  private void initFields() {
    fields = new HashMap<>();
    fields.put(UspV1Field.VERSION, new EncodableFixedInteger(6, UspV1.VERSION));
    fields.put(UspV1Field.NOTICE, new EncodableFixedInteger(2, 0));
    fields.put(UspV1Field.OPT_OUT_SALE, new EncodableFixedInteger(2, 0));
    fields.put(UspV1Field.LSPA_COVERED, new EncodableFixedInteger(2, 0));

    //@formatter:off
    fieldOrder = new String[] {
        UspV1Field.VERSION,
        UspV1Field.NOTICE,
        UspV1Field.OPT_OUT_SALE,
        UspV1Field.LSPA_COVERED,
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
    return UspV1.ID;
  }

  @Override
  public String getName() {
    return UspV1.NAME;
  }

  public Integer getVersion() {
    return (Integer) this.fields.get(UspV1Field.VERSION).getValue();
  }

  public Integer getNotice() {
    return (Integer) fields.get(UspV1Field.NOTICE).getValue();
  }

  public Integer getOptOutSale() {
    return (Integer) fields.get(UspV1Field.OPT_OUT_SALE).getValue();
  }

  public Integer getLspaCovered() {
    return (Integer) fields.get(UspV1Field.LSPA_COVERED).getValue();
  }
}
