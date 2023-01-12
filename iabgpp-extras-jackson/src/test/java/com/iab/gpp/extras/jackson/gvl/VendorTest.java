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

import java.time.Instant;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.iab.gpp.extras.gvl.Gvl;
import com.iab.gpp.extras.gvl.Vendor;
import com.iab.gpp.extras.jackson.Loader;
import com.iab.gpp.extras.jackson.TestUtil;

public class VendorTest {

    private static Vendor vendorEight;
    private static Vendor vendorTwo;
    private static final int VENDOR_ID_SELECTED_FOR_TEST = 8;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        Loader loader = new Loader();
        Gvl gvl = loader.globalVendorList(TestUtil.getGlobalVendorList());
        vendorEight = gvl.getVendor(VENDOR_ID_SELECTED_FOR_TEST);
        vendorTwo = gvl.getVendor(2);
    }

    @Test
    public void testGetId() {
        Assertions.assertEquals(8, vendorEight.getId());
    }

    @Test
    public void testGetName() {
        String expectedName = "Emerse Sverige AB";
        Assertions.assertEquals(expectedName, vendorEight.getName());
    }

    @Test
    public void testGetPurposes() {
        Assertions.assertNotNull(vendorEight.getPurposes());
        Assertions.assertEquals(3, vendorEight.getPurposes().size());
        Assertions.assertEquals(Arrays.asList(1, 3, 4), vendorEight.getPurposes());
    }

    @Test
    public void testGetLegIntPurposes() {
        Assertions.assertNotNull(vendorEight.getLegIntPurposes());
        Assertions.assertEquals(4, vendorEight.getLegIntPurposes().size());
        Assertions.assertEquals(Arrays.asList(2, 7, 8, 9), vendorEight.getLegIntPurposes());
    }

    @Test
    public void testGetFlexiblePurposes() {
        Assertions.assertNotNull(vendorEight.getFlexiblePurposes());
        Assertions.assertEquals(2, vendorEight.getFlexiblePurposes().size());
        Assertions.assertEquals(Arrays.asList(2, 9), vendorEight.getFlexiblePurposes());
    }

    @Test
    public void testGetSpecialPurposes() {
        Assertions.assertNotNull(vendorEight.getSpecialPurposes());
        Assertions.assertEquals(2, vendorEight.getSpecialPurposes().size());
        Assertions.assertEquals(Arrays.asList(1, 2), vendorEight.getSpecialPurposes());
    }

    @Test
    public void testGetFeatures() {
        Assertions.assertNotNull(vendorEight.getFeatures());
        Assertions.assertEquals(2, vendorEight.getFeatures().size());
        Assertions.assertEquals(Arrays.asList(1, 2), vendorEight.getFeatures());
    }

    @Test
    public void testGetSpecialFeatures() {
        Assertions.assertNotNull(vendorEight.getSpecialFeatures());
        Assertions.assertEquals(0, vendorEight.getSpecialFeatures().size());
    }

    @Test
    public void testGetPolicyUrl() {
        String expectedPolicyUrl = "https://www.emerse.com/privacy-policy/";
        Assertions.assertEquals(expectedPolicyUrl, vendorEight.getPolicyUrl());
    }

    @Test
    public void testGetDeletedDate() {
        Assertions.assertEquals(Instant.parse("2020-06-28T00:00:00Z"), vendorEight.getDeletedDate().get());
    }

    @Test
    public void testGetOverflow() {
        Assertions.assertNotNull(vendorEight.getOverflow());
    }

    @Test
    public void testIsDeleted() {
        Assertions.assertTrue(vendorEight.isDeleted());
    }

    @Test
    public void testCookieMaxAgeSeconds() {
        long expectedCookieMaxAgeSeconds = 31557600000L;
        Assertions.assertTrue(vendorEight.getCookieMaxAgeSeconds().isPresent());
        Assertions.assertEquals(expectedCookieMaxAgeSeconds, vendorEight.getCookieMaxAgeSeconds().get().longValue());
    }

    @Test
    public void testUsesCookies() {
        Assertions.assertTrue(vendorEight.getUsesCookies());
    }

    @Test
    public void testCookieRefresh() {
        Assertions.assertFalse(vendorEight.getHasCookieRefresh());
    }

    @Test
    public void testUsesNonCookieAccess() {
        Assertions.assertTrue(vendorEight.getUsesNonCookieAccess());
    }

    @Test
    public void testNullDeviceStorageDisclosureUrl() {
        Assertions.assertFalse(vendorEight.getDeviceStorageDisclosureUrl().isPresent());
    }

    @Test
    public void testNullCookieMaxAgeSeconds() {
        Assertions.assertFalse(vendorTwo.getUsesCookies());
        Assertions.assertFalse(vendorTwo.getCookieMaxAgeSeconds().isPresent());
    }

    @Test
    public void testDeviceStorageDisclosureUrl() {
        String expectedDeviceStorageDisclosureUrl = "https://privacy.blismedia.com/.well-known/deviceStorage.json";
        Assertions.assertTrue(vendorTwo.getDeviceStorageDisclosureUrl().isPresent());
        Assertions.assertEquals(expectedDeviceStorageDisclosureUrl, vendorTwo.getDeviceStorageDisclosureUrl().get());
    }
}
