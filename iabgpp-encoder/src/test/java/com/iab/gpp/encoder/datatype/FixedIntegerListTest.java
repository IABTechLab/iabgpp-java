package com.iab.gpp.encoder.datatype;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;

class FixedIntegerListTest {

  @Test
  void test() {
    FixedIntegerList list = new FixedIntegerList(5);
    assertFalse(list.isDirty());
    list.set(0, 2);
    list.set(1, 1);
    list.set(2, 5);
    list.set(3, 8);
    list.set(4, 3);
    assertThrows(IllegalArgumentException.class, () -> list.set(4, 128));
    assertThrows(IllegalArgumentException.class, () -> list.set(4, -1));
    assertEquals(5, list.size());
    assertEquals(List.of(2,1,5,8,3), list);
    assertEquals(2, list.get(0));
    assertEquals(1, list.get(1));
    assertEquals(5, list.get(2));
    assertEquals(8, list.get(3));
    assertEquals(3, list.get(4));
    assertTrue(list.isDirty());
    list.setDirty(false);
    assertFalse(list.isDirty());
  }

}
