package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.field.UsNeField;
import com.iab.gpp.encoder.section.UsNe;

public final class UsNeCoreSegment extends AbstractBase64Segment<UsNeField> {

  public UsNeCoreSegment() {
    super(UsNeField.USNE_CORE_SEGMENT_FIELD_NAMES);
    initialize(UsNeField.VERSION, new EncodableFixedInteger(6, UsNe.VERSION));
    initialize(UsNeField.PROCESSING_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNeField.SALE_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNeField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNeField.SALE_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNeField.TARGETED_ADVERTISING_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNeField.SENSITIVE_DATA_PROCESSING,
        new EncodableFixedIntegerList(2, 8)
            .withValidator(nullableBooleanAsTwoBitIntegerListValidator));
    initialize(UsNeField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNeField.ADDITIONAL_DATA_PROCESSING_CONSENT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNeField.MSPA_COVERED_TRANSACTION,
        new EncodableFixedInteger(2, 1).withValidator(nonNullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNeField.MSPA_OPT_OUT_OPTION_MODE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNeField.MSPA_SERVICE_PROVIDER_MODE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
  }

}
