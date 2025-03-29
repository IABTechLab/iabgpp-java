package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;

public class HeaderV1Test {

  @Test
  public void testEncode1() {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.setFieldValue("SectionIds", new ArrayList<>());
    Assertions.assertEquals("DBAA", headerV1.encode());
  }

  @Test
  public void testEncode2() {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.setFieldValue("SectionIds", Arrays.asList(2));
    Assertions.assertEquals("DBABMA", headerV1.encode());
  }

  @Test
  public void testEncode3() {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.setFieldValue("SectionIds", Arrays.asList(2, 6));
    Assertions.assertEquals("DBACNYA", headerV1.encode());
  }

  @Test
  public void testDecode1() {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.decode("DBAA");
    Assertions.assertEquals(Set.of(), headerV1.getFieldValue("SectionIds"));
    Assertions.assertEquals(headerV1.getFieldValue("Version"), headerV1.getVersion());
    Assertions.assertEquals(headerV1.getFieldValue("SectionIds"), headerV1.getSectionsIds());
  }

  @Test
  public void testDecode2() {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.decode("DBABMA");
    Assertions.assertEquals(Set.of(2), headerV1.getFieldValue("SectionIds"));
    Assertions.assertEquals(headerV1.getFieldValue("Version"), headerV1.getVersion());
    Assertions.assertEquals(headerV1.getFieldValue("SectionIds"), headerV1.getSectionsIds());
  }

  @Test
  public void testDecode3() {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.decode("DBACNYA");
    Assertions.assertEquals(Set.of(2, 6), headerV1.getFieldValue("SectionIds"));
    Assertions.assertEquals(headerV1.getFieldValue("Version"), headerV1.getVersion());
    Assertions.assertEquals(headerV1.getFieldValue("SectionIds"), headerV1.getSectionsIds());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new HeaderV1("z").getSectionsIds();
    });
  }
}
