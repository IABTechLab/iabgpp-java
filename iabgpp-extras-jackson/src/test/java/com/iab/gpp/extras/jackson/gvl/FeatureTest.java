package com.iab.gpp.extras.jackson.gvl;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

import com.iab.gpp.extras.gvl.Feature;
import com.iab.gpp.extras.jackson.Loader;
import com.iab.gpp.extras.jackson.TestUtil;

public class FeatureTest {

    private static Feature featureTwo;
    private static final int FEATURE_ID_SELECTED_FOR_TEST = 2;

    @BeforeAll
    public static void setupBeforeClass() throws IOException {
        Loader loader = new Loader();
        List<Feature> features = loader.globalVendorList(TestUtil.getGlobalVendorList()).getFeatures();
        featureTwo = features.stream().filter(o -> o.getId() == FEATURE_ID_SELECTED_FOR_TEST).findFirst().orElse(null);
    }

    @Test
    public void testGetId() {
        Assertions.assertEquals(2, featureTwo.getId());
    }

    @Test
    public void testGetName() {
        String expectedName = "Link different devices";
        Assertions.assertEquals(expectedName, featureTwo.getName());
    }

    @Test
    public void testGetDescription() {
        String expectedDescription =
                "Different devices can be determined as belonging to you or your household in support of one or more of purposes.";
        Assertions.assertEquals(expectedDescription, featureTwo.getDescription());
    }

    @Test
    public void testGetDescriptionLegal() {
        String expectedDescriptionLegal =
                "Vendors can:\n* Deterministically determine that two or more devices belong to the same user or household\n* Probabilistically determine that two or more devices belong to the same user or household\n* Actively scan device characteristics for identification for probabilistic identification if users have allowed vendors to actively scan device characteristics for identification (Special Feature 2)";
        Assertions.assertEquals(expectedDescriptionLegal, featureTwo.getDescriptionLegal());
    }
}
