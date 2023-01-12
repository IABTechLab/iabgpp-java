package com.iab.gpp.extras.jackson.gvl;

/*-
 * #%L
 * IAB TCF Java GVL Jackson
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

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.iab.gpp.extras.gvl.Gvl;
import com.iab.gpp.extras.jackson.Loader;
import com.iab.gpp.extras.jackson.TestUtil;

public class GvlTest {
    private static Gvl gvl;

    @BeforeAll
    public static void setUpBeforeClass() throws IOException {
        Loader loader = new Loader();
        gvl = loader.globalVendorList(TestUtil.getGlobalVendorList());
    }

    @Test
    public void testGetGvlSpecificationVersion() {
        Assertions.assertEquals(2, gvl.getGvlSpecificationVersion());
    }

    @Test
    public void testGetVendorListVersion() {
        Assertions.assertEquals(26, gvl.getVendorListVersion());
    }

    @Test
    public void testGetTcfPolicyVersion() {
        Assertions.assertEquals(2, gvl.getTcfPolicyVersion());
    }

    @Test
    public void testGetLastUpdated() throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        parser.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date parsed = parser.parse("2020-02-20T16:05:20");
        Assertions.assertEquals(parsed.toInstant(), gvl.getLastUpdated());
    }

    @Test
    public void testGetPurposes() {
        Assertions.assertEquals(10, gvl.getPurposes().size());
    }

    @Test
    public void testGetSpecialPurposes() {
        Assertions.assertEquals(2, gvl.getSpecialPurposes().size());
    }

    @Test
    public void testGetFeatures() {
        Assertions.assertEquals(3, gvl.getFeatures().size());
    }

    @Test
    public void testGetSpecialFeatures() {
        Assertions.assertEquals(2, gvl.getSpecialFeatures().size());
    }

    @Test
    public void testGetStacks() {
        Assertions.assertEquals(37, gvl.getStacks().size());
    }

    @Test
    public void testGetVendors() {
        Assertions.assertEquals(3, gvl.getVendors().size());
    }
}
