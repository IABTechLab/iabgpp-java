package com.iab.gpp.encoder.section;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.encoder.error.DecodingException;
import com.iab.gpp.encoder.field.HeaderV1Field;

public class HeaderV1Test {

  @Test
  public void testEncode1() {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.setFieldValue(HeaderV1Field.SECTION_IDS, new ArrayList<>());
    Assertions.assertEquals("DBAA", headerV1.encode());
  }

  @Test
  public void testEncode2() {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.setFieldValue(HeaderV1Field.SECTION_IDS, Arrays.asList(2));
    Assertions.assertEquals("DBABMA", headerV1.encode());
  }

  @Test
  public void testEncode3() {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.setFieldValue(HeaderV1Field.SECTION_IDS, Arrays.asList(2, 6));
    Assertions.assertEquals("DBACNYA", headerV1.encode());
  }

  @Test
  public void testDecode1() {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.decode("DBAA");
    Assertions.assertEquals(Set.of(), headerV1.getFieldValue(HeaderV1Field.SECTION_IDS));
    Assertions.assertEquals(headerV1.getFieldValue(HeaderV1Field.VERSION), headerV1.getVersion());
    Assertions.assertEquals(headerV1.getFieldValue(HeaderV1Field.SECTION_IDS), headerV1.getSectionsIds());
  }

  @Test
  public void testDecode2() {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.decode("DBABMA");
    Assertions.assertEquals(Set.of(2), headerV1.getFieldValue(HeaderV1Field.SECTION_IDS));
    Assertions.assertEquals(headerV1.getFieldValue(HeaderV1Field.VERSION), headerV1.getVersion());
    Assertions.assertEquals(headerV1.getFieldValue(HeaderV1Field.SECTION_IDS), headerV1.getSectionsIds());
  }

  @Test
  public void testDecode3() {
    HeaderV1 headerV1 = new HeaderV1();
    headerV1.decode("DBACNYA");
    Assertions.assertEquals(Set.of(2, 6), headerV1.getFieldValue(HeaderV1Field.SECTION_IDS));
    Assertions.assertEquals(headerV1.getFieldValue(HeaderV1Field.VERSION), headerV1.getVersion());
    Assertions.assertEquals(headerV1.getFieldValue(HeaderV1Field.SECTION_IDS), headerV1.getSectionsIds());
  }
  
  @Test()
  public void testDecodeGarbage() {
    Assertions.assertThrows(DecodingException.class, () -> {
      new HeaderV1("z").getSectionsIds();
    });
  }
}
