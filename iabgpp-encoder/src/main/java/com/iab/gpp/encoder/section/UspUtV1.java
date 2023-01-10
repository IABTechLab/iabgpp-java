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
import com.iab.gpp.encoder.field.UspUtV1Field;
import com.iab.gpp.encoder.field.UspV1Field;

public class UspUtV1 extends AbstractEncodableBitStringSection {
  public static int ID = 11;
  public static int VERSION = 1;
  public static String NAME = "usputv1";

  private AbstractBase64UrlEncoder base64UrlEncoder = new CompressedBase64UrlEncoder();

  public UspUtV1() {
    initFields();
  }

  public UspUtV1(String encodedString) throws DecodingException {
    initFields();

    if (encodedString != null && encodedString.length() > 0) {
      this.decode(encodedString);
    }
  }

  private void initFields() {
    fields = new HashMap<>();

    fields.put(UspUtV1Field.VERSION, new EncodableFixedInteger(6, UspUtV1.VERSION));
    fields.put(UspUtV1Field.SHARING_NOTICE, new EncodableFixedInteger(2, 0));
    fields.put(UspUtV1Field.SALE_OPT_OUT_NOTICE, new EncodableFixedInteger(2, 0));
    fields.put(UspUtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, new EncodableFixedInteger(2, 0));
    fields.put(UspUtV1Field.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE, new EncodableFixedInteger(2, 0));
    fields.put(UspUtV1Field.SALE_OPT_OUT, new EncodableFixedInteger(2, 0));
    fields.put(UspUtV1Field.TARGETED_ADVERTISING_OPT_OUT, new EncodableFixedInteger(2, 0));
    fields.put(UspUtV1Field.SENSITIVE_DATA_PROCESSING,
        new EncodableFixedIntegerList(2, Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0)));
    fields.put(UspUtV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, new EncodableFixedInteger(2, 0));
    fields.put(UspUtV1Field.MSPA_COVERED_TRANSACTION, new EncodableFixedInteger(2, 0));
    fields.put(UspUtV1Field.MSPA_OPT_OUT_OPTION_MODE, new EncodableFixedInteger(2, 0));
    fields.put(UspUtV1Field.MSPA_SERVICE_PROVIDER_MODE, new EncodableFixedInteger(2, 0));

    //@formatter:off
    fieldOrder = new String[] {
        UspUtV1Field.VERSION,
        UspUtV1Field.SHARING_NOTICE,
        UspUtV1Field.SALE_OPT_OUT_NOTICE,
        UspUtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
        UspUtV1Field.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE,
        UspUtV1Field.SALE_OPT_OUT,
        UspUtV1Field.TARGETED_ADVERTISING_OPT_OUT,
        UspUtV1Field.SENSITIVE_DATA_PROCESSING,
        UspUtV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
        UspUtV1Field.MSPA_COVERED_TRANSACTION,
        UspUtV1Field.MSPA_OPT_OUT_OPTION_MODE,
        UspUtV1Field.MSPA_SERVICE_PROVIDER_MODE
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
    return UspUtV1.ID;
  }

  @Override
  public String getName() {
    return UspUtV1.NAME;
  }

  public Integer getVersion() {
    return (Integer) this.fields.get(UspV1Field.VERSION).getValue();
  }

  public Integer getSharingNotice() {
    return (Integer) this.fields.get(UspUtV1Field.SHARING_NOTICE).getValue();
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.fields.get(UspUtV1Field.SALE_OPT_OUT_NOTICE).getValue();
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.fields.get(UspUtV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE).getValue();
  }

  public Integer getSensitiveDataProcessingOptOutNotice() {
    return (Integer) this.fields.get(UspUtV1Field.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE).getValue();
  }

  public Integer getSaleOptOut() {
    return (Integer) this.fields.get(UspUtV1Field.SALE_OPT_OUT).getValue();
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.fields.get(UspUtV1Field.TARGETED_ADVERTISING_OPT_OUT).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Integer> getSensitiveDataProcessing() {
    return (List<Integer>) this.fields.get(UspUtV1Field.SENSITIVE_DATA_PROCESSING).getValue();
  }

  public Integer getKnownChildSensitiveDataConsents() {
    return (Integer) this.fields.get(UspUtV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS).getValue();
  }

  public Integer getMspaCoveredTransaction() {
    return (Integer) this.fields.get(UspUtV1Field.MSPA_COVERED_TRANSACTION).getValue();
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.fields.get(UspUtV1Field.MSPA_OPT_OUT_OPTION_MODE).getValue();
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.fields.get(UspUtV1Field.MSPA_SERVICE_PROVIDER_MODE).getValue();
  }

}
