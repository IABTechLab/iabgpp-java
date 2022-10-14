package com.iab.gpp.encoder.section;

import java.util.HashMap;
import java.util.List;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.encoder.Base64UrlEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;
import com.iab.gpp.encoder.field.UspV1Field;
import com.iab.gpp.encoder.field.UspVaV1Field;

public class UspVaV1 extends AbstractEncodableBitStringSection {
  public static int ID = 9;
  public static int VERSION = 1;
  public static String NAME = "uspva";

  public UspVaV1() {
    initFields();
  }

  public UspVaV1(String encodedString) throws DecodingException {
    initFields();

    if (encodedString != null && encodedString.length() > 0) {
      this.decode(encodedString);
    }
  }

  private void initFields() {
    fields = new HashMap<>();

    fields.put(UspVaV1Field.VERSION, new EncodableFixedInteger(6, UspVaV1.VERSION));
    fields.put(UspVaV1Field.SHARING_NOTICE, new EncodableFixedInteger(2));
    fields.put(UspVaV1Field.SALE_OPT_OUT_NOTICE, new EncodableFixedInteger(2));
    fields.put(UspVaV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE, new EncodableFixedInteger(2));
    fields.put(UspVaV1Field.SALE_OPT_OUT, new EncodableFixedInteger(2));
    fields.put(UspVaV1Field.TARGETED_ADVERTISING_OPT_OUT, new EncodableFixedInteger(2));
    fields.put(UspVaV1Field.SENSITIVE_DATA_PROCESSING, new EncodableFixedInteger(8));
    fields.put(UspVaV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, new EncodableFixedInteger(2));
    fields.put(UspVaV1Field.MSPA_COVERED_TRANSACTION, new EncodableFixedInteger(2));
    fields.put(UspVaV1Field.MSPA_OPT_OUT_OPTION_MODE, new EncodableFixedInteger(2));
    fields.put(UspVaV1Field.MSPA_SERVICE_PROVIDER_MODE, new EncodableFixedInteger(2));

    //@formatter:off
    fieldOrder = new String[] {
        UspVaV1Field.VERSION,
        UspVaV1Field.SHARING_NOTICE,
        UspVaV1Field.SALE_OPT_OUT_NOTICE,
        UspVaV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
        UspVaV1Field.SALE_OPT_OUT,
        UspVaV1Field.TARGETED_ADVERTISING_OPT_OUT,
        UspVaV1Field.SENSITIVE_DATA_PROCESSING,
        UspVaV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
        UspVaV1Field.MSPA_COVERED_TRANSACTION,
        UspVaV1Field.MSPA_OPT_OUT_OPTION_MODE,
        UspVaV1Field.MSPA_SERVICE_PROVIDER_MODE
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
    return UspVaV1.ID;
  }

  @Override
  public String getName() {
    return UspVaV1.NAME;
  }

  public Integer getVersion() {
    return (Integer) this.fields.get(UspV1Field.VERSION).getValue();
  }

  public Integer getSharingNotice() {
    return (Integer) this.fields.get(UspVaV1Field.SHARING_NOTICE).getValue();
  }

  public Integer getSaleOptOutNotice() {
    return (Integer) this.fields.get(UspVaV1Field.SALE_OPT_OUT_NOTICE).getValue();
  }

  public Integer getTargetedAdvertisingOptOutNotice() {
    return (Integer) this.fields.get(UspVaV1Field.TARGETED_ADVERTISING_OPT_OUT_NOTICE).getValue();
  }

  public Integer getSaleOptOut() {
    return (Integer) this.fields.get(UspVaV1Field.SALE_OPT_OUT).getValue();
  }

  public Integer getTargetedAdvertisingOptOut() {
    return (Integer) this.fields.get(UspVaV1Field.TARGETED_ADVERTISING_OPT_OUT).getValue();
  }

  @SuppressWarnings("unchecked")
  public List<Boolean> getSensitiveDataProcessing() {
    return (List<Boolean>) this.fields.get(UspVaV1Field.SENSITIVE_DATA_PROCESSING).getValue();
  }

  public Integer getKnownChildSensitiveDataConsents() {
    return (Integer) this.fields.get(UspVaV1Field.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS).getValue();
  }

  public Integer getMspaCoveredtransaction() {
    return (Integer) this.fields.get(UspVaV1Field.MSPA_COVERED_TRANSACTION).getValue();
  }

  public Integer getMspaOptOutOptionMode() {
    return (Integer) this.fields.get(UspVaV1Field.MSPA_OPT_OUT_OPTION_MODE).getValue();
  }

  public Integer getMspaServiceProviderMode() {
    return (Integer) this.fields.get(UspVaV1Field.MSPA_SERVICE_PROVIDER_MODE).getValue();
  }

}
