package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.datatype.EncodableFixedInteger;
import com.iab.gpp.encoder.datatype.EncodableFixedIntegerList;
import com.iab.gpp.encoder.field.UsTnField;
import com.iab.gpp.encoder.section.UsTn;

public final class UsTnCoreSegment extends AbstractBase64Segment<UsTnField> {

  public UsTnCoreSegment() {
    super(UsTnField.USTN_CORE_SEGMENT_FIELD_NAMES);
    initialize(UsTnField.VERSION, new EncodableFixedInteger(6, UsTn.VERSION));
    initialize(UsTnField.PROCESSING_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsTnField.SALE_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsTnField.TARGETED_ADVERTISING_OPT_OUT_NOTICE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsTnField.SALE_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsTnField.TARGETED_ADVERTISING_OPT_OUT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsTnField.SENSITIVE_DATA_PROCESSING,
        new EncodableFixedIntegerList(2, 8)
            .withValidator(nullableBooleanAsTwoBitIntegerListValidator));
    initialize(UsTnField.KNOWN_CHILD_SENSITIVE_DATA_CONSENTS,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsTnField.ADDITIONAL_DATA_PROCESSING_CONSENT,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsTnField.MSPA_COVERED_TRANSACTION,
        new EncodableFixedInteger(2, 1).withValidator(nonNullableBooleanAsTwoBitIntegerValidator));
    initialize(UsTnField.MSPA_OPT_OUT_OPTION_MODE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
    initialize(UsTnField.MSPA_SERVICE_PROVIDER_MODE,
        new EncodableFixedInteger(2, 0).withValidator(nullableBooleanAsTwoBitIntegerValidator));
  }

}
