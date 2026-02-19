package com.iab.gpp.encoder.segment;

import com.iab.gpp.encoder.field.FieldKey;

public class SegmentValueProvider<E extends Enum<E> & FieldKey> {
  private final E key;

  public SegmentValueProvider(E key) {
    this.key = key;
  }

  public int modify(int original) {
    return original;
  }

  public final int extract(EncodableSegment<E> segment) {
    return (Integer) segment.getFieldValueUnsafe(key);
  }
}
