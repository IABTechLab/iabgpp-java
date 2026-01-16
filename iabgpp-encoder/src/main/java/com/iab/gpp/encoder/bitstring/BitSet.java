package com.iab.gpp.encoder.bitstring;

import java.util.Arrays;
import com.iab.gpp.encoder.error.DecodingException;

// a thin version of java.util.BitSet
public final class BitSet {
  
  private static final int ADDRESS_BITS_PER_WORD = 6;
  private static final int BITS_PER_WORD = 1 << ADDRESS_BITS_PER_WORD;

  /* Used to shift left or right for a partial word mask */
  private static final long WORD_MASK = 0xffffffffffffffffL;
  
  private long[] words;

  public BitSet(int initialCapacity) {
    this.words = new long[wordIndex(initialCapacity) + 1];
  }

  public BitSet() {
    this(0);
  }
  
  private static int wordIndex(int index) {
    if (index < 0) {
      throw new DecodingException("got negative word index");
    }
    return index >> ADDRESS_BITS_PER_WORD;
  }
  
  private long[] ensureIndex(int wordIndex) {
    long[] words = this.words;
    int wordsUsed = words.length;
    if (wordIndex >= wordsUsed) {
      int request = Math.max(2 * wordsUsed, wordIndex + 1);
      words = Arrays.copyOf(words, request);
      this.words = words;
    }
    return words;
  }

  public boolean get(int index) {
    int wordIndex = wordIndex(index);
    long[] words = this.words;
    return (wordIndex < words.length)
        && ((words[wordIndex] & (1L << index)) != 0);
  }

  public void clear(int from, int to) {
    for (int i = from; i < to; i++) {
      clear(i);
    }
  }

  public void clear(int bitIndex) {
    int wordIndex = wordIndex(bitIndex);
    long[] words = this.words;
    if (wordIndex < words.length) {
      words[wordIndex] &= ~(1L << bitIndex);
    }
  }

  public int nextSetBit(int fromIndex) {
    long[] words = this.words;
    int wordsInUse = words.length;
    int u = wordIndex(fromIndex);
    if (u >= wordsInUse) {
        return -1;
    }

    long word = words[u] & (WORD_MASK << fromIndex);

    while (true) {
        if (word != 0) {
            return (u * BITS_PER_WORD) + Long.numberOfTrailingZeros(word);
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
    long[] words = ensureIndex(wordIndex);
    words[wordIndex] |= (1L << bitIndex);
  }

  public void appendLong(int bitIndex, long value, int bits) {
    
  }

}
