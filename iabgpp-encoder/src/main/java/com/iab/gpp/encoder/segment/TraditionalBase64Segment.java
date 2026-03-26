package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.base64.AbstractBase64UrlEncoder;
import com.iab.gpp.encoder.base64.TraditionalBase64UrlEncoder;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.field.FieldNames;

public final class TraditionalBase64Segment<E extends Enum<E> & FieldKey>
    extends AbstractBase64Segment<E> {

  public TraditionalBase64Segment(FieldNames<E> fieldNames, boolean optional) {
    super(fieldNames, optional);
  }

  public TraditionalBase64Segment(FieldNames<E> fieldNames) {
    this(fieldNames, false);
  }

  protected final AbstractBase64UrlEncoder getBase64UrlEncoder() {
    return TraditionalBase64UrlEncoder.getInstance();
  }
}
