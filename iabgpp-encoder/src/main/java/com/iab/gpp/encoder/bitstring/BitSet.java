package com.iab.gpp.encoder.bitstring;

import java.util.Arrays;
import com.iab.gpp.encoder.error.DecodingException;

// a thin version of java.util.BitSet
public final class BitSet {
  
  private static final byte[] EMPTY = new byte[0];
  private static final int ADDRESS_BITS_PER_WORD = 3;
  private static final int BITS_PER_WORD = 1 << ADDRESS_BITS_PER_WORD;
  private static final int MASK = 1 << (BITS_PER_WORD - 1);
  private static final int MODULO = BITS_PER_WORD - 1;
  private static final int CORRECTION = Integer.SIZE - BITS_PER_WORD;
  private static final int WORD_MASK = 0xffffffff;
  
  private byte[] words;

  public BitSet(byte[] words) {
    this.words = words;
  }
  
  public BitSet(int initialCapacity) {
    this(new byte[wordIndex(initialCapacity) + 1]);
  }

  public BitSet() {
    this(EMPTY);
  }

  private static int wordIndex(int index) {
    if (index < 0) {
      throw new DecodingException("got negative word index");
    }
    return index >> ADDRESS_BITS_PER_WORD;
  }

  private static int wordMask(int index) {
    int bit = index & MODULO;
    return MASK >> bit;
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
    return (wordIndex < words.length)
        && (words[wordIndex] & wordMask(bitIndex)) != 0;
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
      words[wordIndex] &= ~wordMask(bitIndex);
    }
  }

  public int nextSetBit(int fromIndex) {
    byte[] words = this.words;
    int wordsInUse = words.length;
    int u = wordIndex(fromIndex);
    if (u >= wordsInUse) {
        return -1;
    }

    int bit = fromIndex & MODULO;
    int word = (words[u] << CORRECTION) & (WORD_MASK >>> bit);

    while (true) {
        if (word != 0) {
            return (u * BITS_PER_WORD) + Integer.numberOfLeadingZeros(word);
        }
        if (++u == wordsInUse) {
            return -1;
        }
        word = (words[u] << CORRECTION);
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
    words[wordIndex] |= wordMask(bitIndex);
  }

  public boolean set(int bitIndex, boolean value) {
    int wordIndex = wordIndex(bitIndex);
    int wordMask = wordMask(bitIndex);
    byte[] words = ensureIndex(wordIndex);
    boolean prior = (words[wordIndex] & wordMask) != 0;
    if (prior != value) {
      if (value) {
        words[wordIndex] |= wordMask;
      } else {
        words[wordIndex] &= ~wordMask;
      }
    }
    return prior;
  }
  
  
  public int readInt(int from, int to) {
    int startWordIndex = wordIndex(from);
    int startBit = from & MODULO;
    int endWordIndex = wordIndex(to);
    int endBit = to & MODULO;
    // TODO: is this needed if the caller checks range?
    byte[] words = ensureIndex(endWordIndex);
    int out = 0;
    for(int wordIndex = startWordIndex; wordIndex <= endWordIndex; wordIndex++) {
      int word = words[wordIndex] & 0xff;
      if (wordIndex == startWordIndex) {
        int mask = (0xff >>> startBit);
        word &= mask;
      }
      if (wordIndex == endWordIndex) {
        int mask = ~(0xff >> endBit);
        word &= mask;
        out |= word >>> (BITS_PER_WORD - endBit);
        break;
      }
      int remaining = to - ((wordIndex + 1) << ADDRESS_BITS_PER_WORD);
      out |= word << remaining;
    }
    return out;
  }
  
  public long readLong(int from, int to) {
    int startWordIndex = wordIndex(from);
    int startBit = from & MODULO;
    int endWordIndex = wordIndex(to);
    int endBit = to & MODULO;
    // TODO: is this needed if the caller checks range?
    byte[] words = ensureIndex(endWordIndex);
    long out = 0;
    for(int wordIndex = startWordIndex; wordIndex <= endWordIndex; wordIndex++) {
      long word = words[wordIndex] & 0xff;
      if (wordIndex == startWordIndex) {
        int mask = (0xff >>> startBit);
        word &= mask;
      }
      if (wordIndex == endWordIndex) {
        int mask = ~(0xff >> endBit);
        word &= mask;
        out |= word >>> (BITS_PER_WORD - endBit);
        break;
      }
      int remaining = to - ((wordIndex + 1) << ADDRESS_BITS_PER_WORD);
      out |= word << remaining;
    }
    return out;
  }
}
