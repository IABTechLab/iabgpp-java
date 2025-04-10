package com.iab.gpp.encoder.datatype.encoder;

import static com.iab.gpp.encoder.datatype.encoder.IntegerBitSet.MAX_COLLECTION_SIZE;

final class IntegerCache {
  private IntegerCache() {}
  private static final Integer[] CACHE = new Integer[MAX_COLLECTION_SIZE];
  static {
    for (int i = 0; i < MAX_COLLECTION_SIZE; i++) {
      CACHE[i] = i;
    }
  }

  static Integer valueOf(int i) {
    if (i >=0 && i < MAX_COLLECTION_SIZE) {
      return CACHE[i];
    }
    return i;
  }

}
