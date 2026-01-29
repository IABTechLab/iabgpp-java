package com.iab.gpp.encoder.datatype.encoder;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class IntegerSetTest {

  @Test
  void test() {
    IntegerSet set = new IntegerSet(5);
    assertFalse(set.isDirty());
    assertTrue(set.isEmpty());
    set.add(0);
    set.add(2);
    set.add(3);
    assertEquals(Set.of(0,2,3), set);
    assertTrue(set.isDirty());
    assertFalse(set.isEmpty());
    assertEquals(3, set.size());
    assertFalse(set.remove(1));
    assertTrue(set.remove(2));
    assertEquals(2, set.size());
    set.addAll(List.of(3,4));
    assertEquals(Set.of(0,3,4), set);
    List<Integer> out = new ArrayList<>();
    Iterator<Integer> it = set.iterator();
    it.forEachRemaining(out::add);
    assertEquals(List.of(0,3,4), out);
    assertFalse(set.contains(2));
    assertTrue(set.contains(3));
    assertTrue(set.isDirty());
    set.setDirty(false);
    assertFalse(set.isDirty());
    set.retainAll(Set.of(1,2,3));
    assertEquals(Set.of(3), set);
    assertTrue(set.addAll(List.of(1,3,4)));
    assertFalse(set.addAll(List.of(1,3,4)));
    assertTrue(set.removeAll(List.of(3,4)));
    assertEquals(Set.of(1), set);
    set.clear();
    assertTrue(set.isEmpty());
    set.addRange(1, 3);
    assertEquals(Set.of(1,2), set);
  }

}
