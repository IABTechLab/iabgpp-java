package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;

public abstract class AbstractUsSection<E extends Enum<E> & FieldKey> extends EncodableSection<E> {

  @SafeVarargs
  protected AbstractUsSection(EncodableSegment<E>... segments) {
    super(segments);
  }
}
