package com.iab.gpp.encoder.datatype;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class DirtyableListTest {

  @Test
  void test() {
    RangeEntry item1 = new RangeEntry(1, 2, Set.of(3,4,5));
    RangeEntry item2 = new RangeEntry(6, 7, Set.of(8,9,10));
    RangeEntry item3 = new RangeEntry(11, 12, Set.of(13,14,15));
    DirtyableList<RangeEntry> list = new DirtyableList<>();
    list.add(item1);
    list.addAll(List.of(item2, item3));
    assertEquals(List.of(item1, item2, item3), list);
    assertEquals(item1, list.get(0));
    assertEquals(item2, list.get(1));
    assertEquals(item3, list.get(2));
    assertEquals(3, list.size());
    assertFalse(list.isEmpty());
    assertTrue(list.isDirty());
    list.setDirty(false);
    assertFalse(list.isDirty());
    item2.getIds().remove(9);
    assertTrue(list.isDirty());
    RangeEntry item2a = new RangeEntry(100, 200, Set.of(300,400,500));
    assertEquals(item2, list.set(1, item2a));
    assertEquals(item2a, list.get(1));
    assertEquals(item3, list.remove(2));
    assertEquals(2, list.size());
    list.add(item3);
    assertEquals(List.of(item1, item2a, item3), list);
  }

}
