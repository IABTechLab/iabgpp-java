package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.field.UsVaField;
import com.iab.gpp.encoder.section.UsVa;

public final class UsVaCoreSegment extends AbstractBase64Segment<UsVaField> {

  public UsVaCoreSegment() {
    super(UsVaField.USVA_CORE_SEGMENT_FIELD_NAMES);
    initialize(UsVaField.VERSION, new EncodableFixedInteger(6, UsVa.VERSION));
    initialize(UsVaField.SHARING_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsVaField.SALE_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsVaField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsVaField.SALE_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsVaField.TARGETED_ADVERTISING_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsVaField.SENSITIVE_DATA_PROCESSING,
        new EncodableFixedIntegerList(2, 8)
            .withValidator(nullableBooleanAsTwoBitIntegerListValidator));
    initialize(UsVaField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsVaField.MSPA_COVERED_TRANSACTION,
        new EncodableFixedInteger(2, 1).withValidator(nonNullableBooleanAsTwoBitIntegerValidator));
    initialize(UsVaField.MSPA_OPT_OUT_OPTION_MODE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsVaField.MSPA_SERVICE_PROVIDER_MODE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
  }

}
