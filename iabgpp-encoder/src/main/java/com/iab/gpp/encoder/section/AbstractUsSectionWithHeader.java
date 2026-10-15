package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.datatype.IntegerSet;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public abstract class AbstractUsSectionWithHeader<E extends Enum<E> & FieldKey>
    extends AbstractUsSection<E> {

  @SafeVarargs
  protected AbstractUsSectionWithHeader(EncodableSegment<E>... segments) {
    super(segments);
  }

  protected abstract E getSubSectionsKey();

  public IntegerSet getSubSections() {
    return (IntegerSet) getSegment(0).getFieldValue(getSubSectionsKey());
  }

  @Override
  protected final void doDecode(CharSequence encodedString) {
    List<CharSequence> encodedSegments = SlicedCharSequence.split(encodedString, '.');
    if (encodedSegments.size() < 2) {
      throw new DecodingException("Section contains too few segments");
    }
    getSegment(0).decode(encodedSegments.get(0));
    getSegment(1).decode(encodedSegments.get(1));
    IntegerSet subSections = getSubSections();
    PrimitiveIterator.OfInt it = subSections.iterator();
    while (it.hasNext()) {
      int subSectionIndex = 1 + it.nextInt();
      getSegment(subSectionIndex).decode(encodedSegments.get(subSectionIndex));
    }
  }

  @Override
  protected final CharSequence doEncode() {
    int size = size();
    List<CharSequence> encodedSegments = new ArrayList<>(size);
    IntegerSet subSections = getSubSections();
    for (int i = 2; i < size(); i++) {
      EncodableSegment<E> segment = getSegment(i);
      if (segment.shouldEncode()) {
        subSections.add(i - 1);
      }
    }
    encodedSegments.add(getSegment(0).encodeCharSequence());
    encodedSegments.add(getSegment(1).encodeCharSequence());
    PrimitiveIterator.OfInt it = subSections.iterator();
    while (it.hasNext()) {
      int subSectionIndex = 1 + it.nextInt();
      encodedSegments.add(getSegment(subSectionIndex).encodeCharSequence());
    }
    return SlicedCharSequence.join('.', encodedSegments);
  }
}
