package com.iab.gpp.encoder.datatype;

import java.time.ZonedDateTime;

import com.iab.gpp.encoder.bitstring.BitString;
import com.iab.gpp.encoder.datatype.encoder.DatetimeEncoder;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.error.EncodingException;

public class EncodableDatetime extends AbstractEncodableBitStringDataType<ZonedDateTime> {

  protected EncodableDatetime() {
    super(true);
  }

  public EncodableDatetime(ZonedDateTime value) {
    super(true);
    setValue(value);
  }

  public EncodableDatetime(ZonedDateTime value, boolean hardFailIfMissing) {
    super(hardFailIfMissing);
    setValue(value);
  }

  public String encode() {
    try {
      return DatetimeEncoder.encode(this.value);
    } catch (Exception e) {
      throw new EncodingException(e);
    }
  }

  public void decode(BitString bitString) {
    try {
      this.value = DatetimeEncoder.decode(bitString);
    } catch (Exception e) {
      throw new DecodingException(e);
    }
  }

  public BitString substring(BitString bitString, int fromIndex) throws SubstringException {
    try {
      return bitString.substring(fromIndex, fromIndex + 36);
    } catch (Exception e) {
      throw new SubstringException(e);
    }
  }
}
