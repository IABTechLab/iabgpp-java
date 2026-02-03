package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;

public abstract class EncodableDataType<T> extends DataType<T> {
  public abstract void encode(BitString writer);

  public abstract void decode(BitString reader);
}
