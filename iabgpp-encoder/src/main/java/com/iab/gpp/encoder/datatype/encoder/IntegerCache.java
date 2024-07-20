package com.iab.gpp.encoder.datatype.encoder;

final class IntegerCache {
  // should be greater than the greatest vendor id in the global vendor list
  private static final int CACHE_SIZE = 8096;
  private static final Integer[] CACHE = new Integer[CACHE_SIZE];
  static {
    for (int i = 0; i < CACHE_SIZE; i++) {
      CACHE[i] = i;
    }
  }

  static Integer valueOf(int i) {
    if (i >=0 && i < CACHE_SIZE) {
      return CACHE[i];
    }
    return i;
  }

}
