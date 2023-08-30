package com.iab.gpp.extras.jackson.cmp;

import java.io.IOException;
import java.time.Instant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.iab.gpp.extras.cmp.Cmp;

/*-
 * #%L
 * IAB TCF Java CMP List Jackson
 * %%
 * Copyright (C) 2020 IAB Technology Laboratory, Inc
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.iab.gpp.extras.cmp.CmpList;
import com.iab.gpp.extras.jackson.Loader;

public class CmpListTest {

  private static CmpList cmpList;
  private static Cmp cmpThree;
  private static Cmp cmpTwentyThree;

  @BeforeAll
  public static void setUpBeforeClass() throws IOException {
    cmpList = new Loader().cmpList(CmpListTest.class.getClassLoader().getResourceAsStream("cmpList.json"));
    cmpThree = cmpList.getCmps().stream().filter(o -> o.getId() == 3).findFirst().orElse(null);
    cmpTwentyThree = cmpList.getCmps().stream().filter(o -> o.getId() == 23).findFirst().orElse(null);
  }

  @Test
  public void getLastUpdated() {
    Assertions.assertEquals(Instant.parse("2020-04-09T17:03:06Z"), cmpList.getLastUpdated());
  }

  @Test
  public void getCmps() {
    Assertions.assertEquals(12, cmpList.getCmps().size());
  }

  @Test
  public void getId() {
    Assertions.assertEquals(3, cmpThree.getId());
  }

  @Test
  public void getName() {
    String name = "LiveRamp";
    Assertions.assertEquals(name, cmpThree.getName());
  }

  @Test
  public void isCommercial() {
    Assertions.assertTrue(cmpThree.isCommercial());
  }

  @Test
  public void getDeletedDate() {
    Assertions.assertNull(cmpThree.getDeletedDate().orElse(null));
    Assertions.assertNotNull(cmpTwentyThree.getDeletedDate().orElse(null));
  }

  @Test
  public void isDeleted() {
    Assertions.assertFalse(cmpThree.isDeleted());
    Assertions.assertTrue(cmpTwentyThree.isDeleted());
  }
}
