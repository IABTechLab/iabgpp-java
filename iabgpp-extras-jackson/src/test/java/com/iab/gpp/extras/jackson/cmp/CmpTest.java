package com.iab.gpp.extras.jackson.cmp;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

import com.iab.gpp.extras.cmp.Cmp;
import com.iab.gpp.extras.jackson.Loader;
import com.iab.gpp.extras.jackson.TestUtil;

public class CmpTest {

    private static Cmp cmpThree;
    private static Cmp cmpTwentyThree;
    private static final int CMP_ID_SELECTED_FOR_TEST = 3;
    private static final int DELETED_CMP_ID_SELECTED_FOR_TEST = 23;

    @BeforeAll
    public static void setupBeforeClass() throws IOException {
        Loader loader = new Loader();
        List<Cmp> cmps = loader.cmpList(TestUtil.getCmpList()).getCmps();
        cmpThree = cmps.stream().filter(o -> o.getId() == CMP_ID_SELECTED_FOR_TEST).findFirst().orElse(null);
        cmpTwentyThree =
            cmps.stream().filter(o -> o.getId() == DELETED_CMP_ID_SELECTED_FOR_TEST).findFirst().orElse(null);
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