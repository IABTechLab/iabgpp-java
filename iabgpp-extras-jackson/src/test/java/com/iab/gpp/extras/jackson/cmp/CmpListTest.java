package com.iab.gpp.extras.jackson.cmp;

import java.io.IOException;
import java.time.Instant;
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

import com.iab.gpp.extras.cmp.CmpList;
import com.iab.gpp.extras.jackson.Loader;
import com.iab.gpp.extras.jackson.TestUtil;

public class CmpListTest {

    private static CmpList cmpList;

    @BeforeAll
    public static void setUpBeforeClass() throws IOException {
        Loader loader = new Loader();
        cmpList = loader.cmpList(TestUtil.getCmpList());
    }

    @Test
    public void getLastUpdated() {
        Assertions.assertEquals(Instant.parse("2020-04-09T17:03:06Z"), cmpList.getLastUpdated());
    }

    @Test
    public void getCmps() {
        Assertions.assertEquals(12, cmpList.getCmps().size());
    }
}