package com.iab.gpp.encoder.datatype;

import com.iab.gpp.encoder.bitstring.BitStringBuilder;
import com.iab.gpp.encoder.bitstring.BitStringReader;

public interface EncodableDataType<T> extends DataType<T> {
  void encode(BitStringBuilder builder);

  void decode(BitStringReader reader);
}
