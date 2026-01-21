package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.field.EncodableBitStringFields;
import com.iab.gpp.encoder.field.UsCoField;
import com.iab.gpp.encoder.section.UsCo;

public final class UsCoCoreSegment extends AbstractLazilyEncodableSegment<UsCoField, EncodableBitStringFields<UsCoField>> {

  private static final AbstractBase64UrlEncoder base64UrlEncoder = CompressedBase64UrlEncoder.getInstance();

  public UsCoCoreSegment() {
    super();
  }

  public UsCoCoreSegment(String encodedString) {
    super();
    this.decode(encodedString);
  }

  @Override
  protected EncodableBitStringFields<UsCoField> initializeFields() {
    EncodableBitStringFields<UsCoField> fields = new EncodableBitStringFields<>(UsCoField.USCO_CORE_SEGMENT_FIELD_NAMES);
    fields.put(UsCoField.VERSION, new EncodableFixedInteger(6, UsCo.VERSION));
    fields.put(UsCoField.SHARING_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsCoField.SALE_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsCoField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsCoField.SALE_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsCoField.TARGETED_ADVERTISING_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsCoField.SENSITIVE_DATA_PROCESSING, new EncodableFixedIntegerList(2, 7)
        .withValidator(nullableBooleanAsTwoBitIntegerListValidator));
    fields.put(UsCoField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsCoField.MSPA_COVERED_TRANSACTION,
        new EncodableFixedInteger(2, 1).withValidator(nonNullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsCoField.MSPA_OPT_OUT_OPTION_MODE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsCoField.MSPA_SERVICE_PROVIDER_MODE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    return fields;
  }

  @Override
  protected StringBuilder encodeSegment(EncodableBitStringFields<UsCoField> fields) {
    BitStringBuilder bitString = fields.encode();
    return base64UrlEncoder.encode(bitString);
  }

  @Override
  protected void decodeSegment(CharSequence encodedString, EncodableBitStringFields<UsCoField> fields) {
    if (encodedString == null || encodedString.length() == 0) {
      this.fields.reset(fields);
    }
    try {
      BitString bitString = base64UrlEncoder.decode(encodedString);
      this.fields.decode(bitString);
    } catch (Exception e) {
      throw new DecodingException("Unable to decode UsCoCoreSegment '" + encodedString + "'", e);
    }
  }

}
