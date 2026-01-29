package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.field.UsFlField;
import com.iab.gpp.encoder.section.UsFl;

public final class UsFlCoreSegment extends AbstractBase64Segment<UsFlField> {

  public UsFlCoreSegment() {
    super(UsFlField.USFL_CORE_SEGMENT_FIELD_NAMES);
    initialize(UsFlField.VERSION, new EncodableFixedInteger(6, UsFl.VERSION));
    initialize(UsFlField.PROCESSING_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsFlField.SALE_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsFlField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsFlField.SALE_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsFlField.TARGETED_ADVERTISING_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsFlField.SENSITIVE_DATA_PROCESSING,
        new EncodableFixedIntegerList(2, 8)
            .withValidator(nullableBooleanAsTwoBitIntegerListValidator));
    initialize(UsFlField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS, new EncodableFixedIntegerList(2, 3)
        .withValidator(nullableBooleanAsTwoBitIntegerListValidator));
    initialize(UsFlField.ADDITIONAL_DATA_PROCESSING_CONSENT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsFlField.MSPA_COVERED_TRANSACTION,
        new EncodableFixedInteger(2, 1).withValidator(nonNullableBooleanAsTwoBitIntegerValidator));
    initialize(UsFlField.MSPA_OPT_OUT_OPTION_MODE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsFlField.MSPA_SERVICE_PROVIDER_MODE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
  }

}
