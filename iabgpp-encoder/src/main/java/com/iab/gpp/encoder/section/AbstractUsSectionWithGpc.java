package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.List;
import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.field.UsNeField;
import com.iab.gpp.encoder.segment.EncodableSegment;

public abstract class AbstractUsSectionWithGpc<E extends Enum<E> & FieldKey> extends AbstractUsSection<E> {

  protected AbstractUsSectionWithGpc(EncodableSegment<E> coreSegment, EncodableSegment<E> gpcSegment) {
    super(coreSegment, gpcSegment);
  }

  protected abstract E getGpcSegmentIncludedKey();

  @Override
  protected final void doDecode(CharSequence encodedString) {
    List<CharSequence> encodedSegments = SlicedCharSequence.split(encodedString, '.');
    int numEncodedSegments = encodedSegments.size();

    if (numEncodedSegments > 0) {
      getSegment(0).decode(encodedSegments.get(0));
    }

    E gpcSegmentIncludedKey = getGpcSegmentIncludedKey();
    if (numEncodedSegments > 1) {
      getSegment(1).setFieldValue(gpcSegmentIncludedKey, Boolean.TRUE);
      getSegment(1).decode(encodedSegments.get(1));
    } else {
      getSegment(1).setFieldValue(gpcSegmentIncludedKey, Boolean.FALSE);
    }
  }

  @Override
  protected final CharSequence doEncode() {
    int size = size();
    List<CharSequence> encodedSegments = new ArrayList<>(size);

    encodedSegments.add(getSegment(0).encodeCharSequence());
    if(size >= 2 && getGpcSegmentIncluded()) {
      encodedSegments.add(getSegment(1).encodeCharSequence());
    }

    return SlicedCharSequence.join('.',  encodedSegments);
  }

  public abstract Integer getGpcSegmentType();

  public Boolean getGpcSegmentIncluded() {
    return (Boolean) getSegment(1).getFieldValue(getGpcSegmentIncludedKey());
  }

  public abstract Boolean getGpc();
}
