package com.iab.gpp.encoder.bitstring;

import java.util.Arrays;
import java.util.Base64;
import com.iab.gpp.encoder.base64.TraditionalBase64UrlEncoder;
import com.iab.gpp.encoder.datatype.encoder.IntegerSet;
import com.iab.gpp.encoder.error.DecodingException;

// a thin version of java.util.BitSet
public final class BitSet {
  
  private static final int ADDRESS_BITS_PER_WORD = 3;
  public static final int BITS_PER_WORD = 1 << ADDRESS_BITS_PER_WORD;

  /* Used to shift left or right for a partial word mask */
  private static final int WORD_MASK = 0xffffffff;
  
  private byte[] words;

  public BitSet(byte[] words) {
    this.words = words;
  }
  
  public BitSet(int initialCapacity) {
    this(new byte[wordIndex(initialCapacity) + 1]);
  }

  public BitSet() {
    this(new byte[0]);
  }
  
  public static int wordIndex(int index) {
    if (index < 0) {
      throw new DecodingException("got negative word index");
    }
    return index >> ADDRESS_BITS_PER_WORD;
  }
  
  private byte[] ensureIndex(int wordIndex) {
    byte[] words = this.words;
    int wordsUsed = words.length;
    if (wordIndex >= wordsUsed) {
      int request = Math.max(2 * wordsUsed, wordIndex + 1);
      words = Arrays.copyOf(words, request);
      this.words = words;
    }
    return words;
  }

  public boolean get(int bitIndex) {
    int wordIndex = wordIndex(bitIndex);
    byte[] words = this.words;
    int bit = bitIndex % BITS_PER_WORD;
    return (wordIndex < words.length)
        && ((words[wordIndex] >>> bit) & 1) == 1;
  }

  public void clear(int from, int to) {
    for (int i = from; i < to; i++) {
      clear(i);
    }
  }

  public void clear(int bitIndex) {
    int wordIndex = wordIndex(bitIndex);
    byte[] words = this.words;
    if (wordIndex < words.length) {
      int bit = bitIndex % BITS_PER_WORD;
      words[wordIndex] &= ~(1 << bit);
    }
  }

  public int nextSetBit(int fromIndex) {
    byte[] words = this.words;
    int wordsInUse = words.length;
    int u = wordIndex(fromIndex);
    if (u >= wordsInUse) {
        return -1;
    }

    int bit = fromIndex % BITS_PER_WORD;
    int word = words[u] & (WORD_MASK << bit);

    while (true) {
        if (word != 0) {
            return (u * BITS_PER_WORD) + Integer.numberOfTrailingZeros(word);
        }
        if (++u == wordsInUse) {
            return -1;
        }
        word = words[u];
    }
  }

  public void set(int from, int to) {
    for (int i = from; i < to; i++) {
      set(i);
    }
  }

  public void set(int bitIndex) {
    int wordIndex = wordIndex(bitIndex);
    byte[] words = ensureIndex(wordIndex);
    int bit = bitIndex % BITS_PER_WORD;
    words[wordIndex] |= (1 << bit);
  }
}
