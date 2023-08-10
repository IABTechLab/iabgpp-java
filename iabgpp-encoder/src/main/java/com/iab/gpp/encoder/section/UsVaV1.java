package com.iab.gpp.encoder.section;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.datatype.encoder.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.encoder.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.UspV1Field;
import com.iab.gpp.encoder.field.UsVaV1Field;

public class UsVaV1 extends AbstractEncodableBitStringSection {
  public static int ID = 9;
  public static int VERSION = 1;
  public static String NAME = "usvav1";

  private AbstractBase64UrlEncoder base64UrlEncoder = new CompressedBase64UrlEncoder();

  public UsVaV1() {
    initFields();
  }

  public UsVaV1(String encodedString) throws DecodingException {
    initFields();

    if (encodedString != null && encodedString.length() > 0) {
      this.decode(encodedString);
    }
  }

  private void initFields() {
    fields = new HashMap<>();

    fields.put(UsVaV1Field.VERSION, new EncodableFixedInteger(6, UsVaV1.VERSION));
    fields.put(UsVaV1Field.SHARING_NOTICE, new EncodableFixedInteger(2, 0));
    fields.put(UsVaV1Field.SALE_OPT_OUT_NOTICE, new EncodableFixedInteger(2, 0));
    fields.put(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, new EncodableFixedInteger(2, 0));
    fields.put(UsVaV1Field.SALE_OPT_OUT, new EncodableFixedInteger(2, 0));
    fields.put(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT, new EncodableFixedInteger(2, 0));
    fields.put(UsVaV1Field.SENSITIVE_DATA_PROCESSING,
        new EncodableFixedIntegerList(2, Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0)));
    fields.put(UsVaV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, new EncodableFixedInteger(2, 0));
    fields.put(UsVaV1Field.MSPA_COVERED_TRANSACTION, new EncodableFixedInteger(2, 0));
    fields.put(UsVaV1Field.MSPA_OPT_OUT_OPTION_MODE, new EncodableFixedInteger(2, 0));
    fields.put(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE, new EncodableFixedInteger(2, 0));

    //@formatter:off
    fieldOrder = new String[] {
        UsVaV1Field.VERSION,
        UsVaV1Field.SHARING_NOTICE,
        UsVaV1Field.SALE_OPT_OUT_NOTICE,
        UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
        UsVaV1Field.SALE_OPT_OUT,
        UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT,
        UsVaV1Field.SENSITIVE_DATA_PROCESSING,
        UsVaV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
        UsVaV1Field.MSPA_COVERED_TRANSACTION,
        UsVaV1Field.MSPA_OPT_OUT_OPTION_MODE,
        UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE
    };
    //@formatter:on
  }

  @Override
  public String encode() throws EncodingException {
    String bitString = this.encodeToBitString();
    String encodedString = base64UrlEncoder.encode(bitString);
    return encodedString;
  }

  @Override
  public void decode(String encodedString) throws DecodingException {
    String bitString = base64UrlEncoder.decode(encodedString);
    this.decodeFromBitString(bitString);
  }

  @Override
  public int getId() {
    return UsVaV1.ID;
  }

  @Override
  public String getName() {
    return UsVaV1.NAME;
  }

  public Integer getVersion() {
    return (Integer) this.fields.get(UspV1Field.VERSION).getValue();
  }

  public Integer getSharingNotice() {
    return (Integer) this.fields.get(UsVaV1Field.SHARING_NOTICE).getValue();
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.fields.get(UsVaV1Field.SALE_OPT_OUT_NOTICE).getValue();
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.fields.get(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE).getValue();
  }

  public Integer getSaleOptOut() {
    return (Integer) this.fields.get(UsVaV1Field.SALE_OPT_OUT).getValue();
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.fields.get(UsVaV1Field.TARGETED_ADVERTISING_OPT_OUT).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getSensitiveDataProcessing() {
    return (List<Integer>) this.fields.get(UsVaV1Field.SENSITIVE_DATA_PROCESSING).getValue();
  }

  public Integer getKnownChildSensitiveDataConsents() {
    return (Integer) this.fields.get(UsVaV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS).getValue();
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.fields.get(UsVaV1Field.MSPA_COVERED_TRANSACTION).getValue();
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.fields.get(UsVaV1Field.MSPA_OPT_OUT_OPTION_MODE).getValue();
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.fields.get(UsVaV1Field.MSPA_SERVICE_PROVIDER_MODE).getValue();
  }

}
