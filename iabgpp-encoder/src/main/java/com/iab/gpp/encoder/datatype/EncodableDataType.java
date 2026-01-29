package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringReader;

public abstract class EncodableDataType<T> extends DataType<T> {
  public abstract void encode(BitStringBuilder builder);

  public abstract void decode(BitStringReader reader);
}
