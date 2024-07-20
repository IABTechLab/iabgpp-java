package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitString;

public interface EncodableDataType<T> extends DataType<T> {
  String encode();

  void decode(BitString str);
}
