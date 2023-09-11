package com.iab.gpp.encoder.datatype;

import java.time.ZonedDateTime;
import java.util.function.Predicate;
import com.iab.gpp.encoder.datatype.encoder.DatetimeEncoder;

public class EncodableDatetime extends AbstractEncodableBitStringDataType<ZonedDateTime> {

  protected EncodableDatetime() {
    super();
  }

  public EncodableDatetime(ZonedDateTime value) {
    super();
    setValue(value);
  }
  
  public EncodableDatetime(ZonedDateTime value, Predicate<ZonedDateTime> validator) {
    super(validator);
    setValue(value);
  }

  public String encode() {
    return DatetimeEncoder.encode(this.value);
  }

  public void decode(String bitString) {
    this.value = DatetimeEncoder.decode(bitString);
  }

  public String substring(String bitString, int fromIndex) {
    return bitString.substring(fromIndex, fromIndex + 36);
  }
}
