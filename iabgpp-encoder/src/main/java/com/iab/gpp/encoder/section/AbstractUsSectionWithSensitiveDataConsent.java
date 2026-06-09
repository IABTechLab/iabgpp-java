package com.iab.gpp.encoder.section;

import com.iab.gpp.encoder.field.FieldKey;
import com.iab.gpp.encoder.segment.EncodableSegment;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class for US sections whose core segment is optionally followed by a "Sensitive Data
 * Consents" segment (e.g. Indiana, Kentucky, Rhode Island). Mirrors {@link
 * AbstractUsSectionWithGpc}, but the optional second segment carries sensitive data consents rather
 * than GPC.
 */
public abstract class AbstractUsSectionWithSensitiveDataConsent<E extends Enum<E> & FieldKey>
    extends AbstractUsSection<E> {

  protected AbstractUsSectionWithSensitiveDataConsent(
      EncodableSegment<E> coreSegment, EncodableSegment<E> sensitiveDataConsentSegment) {
    super(coreSegment, sensitiveDataConsentSegment);
  }

  protected abstract E getSensitiveDataConsentSegmentIncludedKey();

  @Override
  protected final void doDecode(CharSequence encodedString) {
    List<CharSequence> encodedSegments = SlicedCharSequence.split(encodedString, '.');
    int numEncodedSegments = encodedSegments.size();

    if (numEncodedSegments > 0) {
      getSegment(0).decode(encodedSegments.get(0));
    }

    E sensitiveDataConsentSegmentIncludedKey = getSensitiveDataConsentSegmentIncludedKey();
    if (numEncodedSegments > 1) {
      getSegment(1).setFieldValue(sensitiveDataConsentSegmentIncludedKey, Boolean.TRUE);
      getSegment(1).decode(encodedSegments.get(1));
    } else {
      getSegment(1).setFieldValue(sensitiveDataConsentSegmentIncludedKey, Boolean.FALSE);
    }
  }

  @Override
  protected final CharSequence doEncode() {
    int size = size();
    List<CharSequence> encodedSegments = new ArrayList<>(size);

    encodedSegments.add(getSegment(0).encodeCharSequence());
    if (size >= 2 && getSensitiveDataConsentSegmentIncluded()) {
      encodedSegments.add(getSegment(1).encodeCharSequence());
    }

    return SlicedCharSequence.join('.', encodedSegments);
  }

  public Boolean getSensitiveDataConsentSegmentIncluded() {
    return (Boolean) getSegment(1).getFieldValue(getSensitiveDataConsentSegmentIncludedKey());
  }
}
