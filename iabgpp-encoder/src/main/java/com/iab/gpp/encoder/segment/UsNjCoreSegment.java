package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.field.UsNjField;
import com.iab.gpp.encoder.section.UsNj;

public final class UsNjCoreSegment extends AbstractBase64Segment<UsNjField> {

  public UsNjCoreSegment() {
    super(UsNjField.USNJ_CORE_SEGMENT_FIELD_NAMES);
    initialize(UsNjField.VERSION, new EncodableFixedInteger(6, UsNj.VERSION));
    initialize(UsNjField.PROCESSING_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNjField.SALE_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNjField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNjField.SALE_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNjField.TARGETED_ADVERTISING_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNjField.SENSITIVE_DATA_PROCESSING,
        new EncodableFixedIntegerList(2, 10)
            .withValidator(nullableBooleanAsTwoBitIntegerListValidator));
    initialize(UsNjField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, new EncodableFixedIntegerList(2, 5)
        .withValidator(nullableBooleanAsTwoBitIntegerListValidator));
    initialize(UsNjField.ADDITIONAL_DATA_PROCESSING_CONSENT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNjField.MSPA_COVERED_TRANSACTION,
        new EncodableFixedInteger(2, 1).withValidator(nonNullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNjField.MSPA_OPT_OUT_OPTION_MODE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsNjField.MSPA_SERVICE_PROVIDER_MODE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
  }

}
