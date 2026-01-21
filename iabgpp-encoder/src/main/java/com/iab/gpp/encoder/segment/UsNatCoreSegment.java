package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.CompressedBase64UrlEncoder;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.field.UsNatField;
import com.iab.gpp.encoder.section.UsNat;

public final class UsNatCoreSegment extends AbstractBase64Segment<UsNatField> {

  public UsNatCoreSegment() {
    super(UsNatField.USNAT_CORE_SEGMENT_FIELD_NAMES, CompressedBase64UrlEncoder.getInstance());
    fields.put(UsNatField.VERSION, new EncodableFixedInteger(6, UsNat.VERSION));
    fields.put(UsNatField.SHARING_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsNatField.SALE_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsNatField.SHARING_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsNatField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsNatField.SENSITIVE_DATA_PROCESSING_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsNatField.SENSITIVE_DATA_LIMIT_USE_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsNatField.SALE_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsNatField.SHARING_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsNatField.TARGETED_ADVERTISING_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsNatField.SENSITIVE_DATA_PROCESSING,
        new EncodableFixedIntegerList(2, 16)
            .withValidator(nullableBooleanAsTwoBitIntegerListValidator));
    fields.put(UsNatField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, new EncodableFixedIntegerList(2, 3)
        .withValidator(nullableBooleanAsTwoBitIntegerListValidator));
    fields.put(UsNatField.PERSONAL_DATA_CONSENTS,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsNatField.MSPA_COVERED_TRANSACTION,
        new EncodableFixedInteger(2, 1).withValidator(nonNullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsNatField.MSPA_OPT_OUT_OPTION_MODE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    fields.put(UsNatField.MSPA_SERVICE_PROVIDER_MODE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
  }

  @Override
  protected BitString decodeBitString(CharSequence encodedString) {
    BitString bitString = super.decodeBitString(encodedString);
    // Necessary to maintain backwards compatibility when sensitive data processing changed from a
    // length of 12 to 16 and known child sensitive data consents changed from a length of 2 to 3 in the
    // DE, IA, NE, NH, NJ, TN release
    if (bitString.length() == 66) {
      BitStringBuilder builder = new BitStringBuilder();
      
      builder.append(bitString, 0, 48);
      builder.extend(8);
      builder.append(bitString, 48, 52);
      builder.extend(2);
      builder.append(bitString, 52, 62);
      bitString = builder.build();
    }
    return bitString;
  }
  
}
