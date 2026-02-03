package com.iab.gpp.encoder.datatype;

import java.time.Instant;
import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.DatetimeEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public final class EncodableDatetime extends AbstractEncodableBitStringDataType<Instant> {

  protected EncodableDatetime() {
    super(true);
  }

  public EncodableDatetime(Instant value) {
    super(true);
    setValue(value, false);
  }

  public void encode(BitString builder) {
    try {
      DatetimeEncoder.encode(builder, this.value);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitString reader) {
    try {
      this.value = DatetimeEncoder.decode(reader);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }
}
