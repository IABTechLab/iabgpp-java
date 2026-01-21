package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.field.EncodableBitStringFields;
import com.iab.gpp.encoder.field.UsNeField;
import com.iab.gpp.encoder.section.UsNe;

public final class UsNeCoreSegment extends AbstractLazilyEncodableSegment<UsNeField, EncodableBitStringFields<UsNeField>> {

  private static final AbstractBase64UrlEncoder base64UrlEncoder = CompressedBase64UrlEncoder.getInstance();

  public UsNeCoreSegment() {
    super();
  }

  public UsNeCoreSegment(String encodedString) {
    super();
    this.decode(encodedString);
  }

  @Override
  protected EncodableBitStringFields<UsNeField> initializeFields() {
    EncodableBitStringFields<UsNeField> fields = new EncodableBitStringFields<>(UsNeField.USNE_CORE_SEGMENT_FIELD_NAMES);
    fields.put(UsNeField.VERSION, new EncodableFixedInteger(6, UsNe.VERSION));
    fields.put(UsNeField.PROCESSING_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsNeField.SALE_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsNeField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsNeField.SALE_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsNeField.TARGETED_ADVERTISING_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsNeField.SENSITIVE_DATA_PROCESSING,
        new EncodableFixedIntegerList(2, 8)
            .withValidator(nullableBooleanAsTwoBitIntegerListValidator));
    fields.put(UsNeField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsNeField.ADDITIONAL_DATA_PROCESSING_CONSENT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsNeField.MSPA_COVERED_TRANSACTION,
        new EncodableFixedInteger(2, 1).withValidator(nonNullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsNeField.MSPA_OPT_OUT_OPTION_MODE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsNeField.MSPA_SERVICE_PROVIDER_MODE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    return fields;
  }

  @Override
  protected StringBuilder encodeSegment(EncodableBitStringFields<UsNeField> fields) {
    BitStringBuilder bitString = fields.encode();
    return base64UrlEncoder.encode(bitString);
  }

  @Override
  protected void decodeSegment(CharSequence encodedString, EncodableBitStringFields<UsNeField> fields) {
    if (encodedString == null || encodedString.length() == 0) {
      this.fields.reset(fields);
    }
    try {
      BitString bitString = base64UrlEncoder.decode(encodedString);
      this.fields.decode(bitString);
    } catch (Exception e) {
      throw new DecodingException("Unable to decode UsNeCoreSegment '" + encodedString + "'", e);
    }
  }

}
