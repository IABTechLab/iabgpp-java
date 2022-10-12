package com.iab.gpp.encoder.datatype;

import java.time.ZonedDateTime;
import com.iab.gpp.encoder.datatype.encoder.DatetimeEncoder;
import com.iab.gpp.encoder.error.DecodingException;

public class EncodableDatetime extends AbstractEncodableBitStringDataType<ZonedDateTime> {

  public EncodableDatetime() {
    super();
  }

  public EncodableDatetime(ZonedDateTime value) {
    super(value);
  }

  public String encode() {
    return DatetimeEncoder.encode(this.value);
  }

  public void decode(String bitString) throws DecodingException {
    this.value = DatetimeEncoder.decode(bitString);
  }

  public String substring(String bitString, int fromIndex) {
    // TODO: validate
    return bitString.substring(fromIndex, fromIndex + 36);
  }
}
