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
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.iab.gpp.extras.gvl.Gvl;
import com.iab.gpp.extras.jackson.Loader;

public class GvlV2_2Test {

  @Test
  public void test() throws IOException {
    Gvl gvl = new Loader()
        .globalVendorList(GvlV2_2Test.class.getClassLoader().getResourceAsStream("vendorlist/v2.2/vendor-list.json"));
    Assertions.assertTrue(gvl.getDataCategories().isPresent());
    Assertions.assertEquals(3, gvl.getFeatures().size());
    Assertions.assertEquals(3, gvl.getGvlSpecificationVersion());
    Assertions.assertNotNull(gvl.getLastUpdated());
    Assertions.assertEquals(11, gvl.getPurposes().size());
    Assertions.assertEquals(2, gvl.getSpecialFeatures().size());
    Assertions.assertEquals(2, gvl.getSpecialPurposes().size());
    Assertions.assertEquals(43, gvl.getStacks().size());
    Assertions.assertEquals(4, gvl.getTcfPolicyVersion());
    Assertions.assertEquals(7, gvl.getVendorListVersion());
    Assertions.assertEquals(376, gvl.getVendors().size());
    
    Assertions.assertEquals(1, gvl.getPurposes().get(0).getId());
    Assertions.assertEquals("Store and/or access information on a device", gvl.getPurposes().get(0).getName());
    Assertions.assertEquals("Cookies, device or similar online identifiers (e.g. login-based identifiers, randomly assigned identifiers, network based identifiers) together with other information (e.g. browser type and information, language, screen size, supported technologies etc.) can be stored or read on your device to recognise it each time it connects to an app or to a website, for one or several of the purposes presented here. ", gvl.getPurposes().get(0).getDescription());
    Assertions.assertTrue(gvl.getPurposes().get(0).getDescriptionLegal().isEmpty());
    Assertions.assertTrue(gvl.getPurposes().get(0).getIllustrations().isPresent());
    Assertions.assertEquals(1, gvl.getPurposes().get(0).getIllustrations().get().size());
    
    Assertions.assertEquals(1, gvl.getSpecialPurposes().get(0).getId());
    Assertions.assertEquals("Ensure security, prevent and detect fraud, and fix errors\n", gvl.getSpecialPurposes().get(0).getName());
    Assertions.assertEquals("Your data can be used to monitor for and prevent unusual and possibly fraudulent activity (for example, regarding advertising, ad clicks by bots), and ensure systems and processes work properly and securely. It can also be used to correct any problems you, the publisher or the advertiser may encounter in the delivery of content and ads and in your interaction with them.", gvl.getSpecialPurposes().get(0).getDescription());
    Assertions.assertTrue(gvl.getSpecialPurposes().get(0).getDescriptionLegal().isEmpty());
    Assertions.assertTrue(gvl.getSpecialPurposes().get(0).getIllustrations().isPresent());
    Assertions.assertEquals(1, gvl.getSpecialPurposes().get(0).getIllustrations().get().size());
    
    Assertions.assertEquals(1, gvl.getFeatures().get(0).getId());
    Assertions.assertEquals("Match and combine data from other data sources", gvl.getFeatures().get(0).getName());
    Assertions.assertEquals("Information about your activity on this service may be matched and combined with other information relating to you and originating from various sources (for instance your activity on a separate online service, your use of a loyalty card in-store, or your answers to a survey), in support of the purposes explained in this notice.", gvl.getFeatures().get(0).getDescription());
    Assertions.assertTrue(gvl.getFeatures().get(0).getDescriptionLegal().isEmpty());
    Assertions.assertTrue(gvl.getFeatures().get(0).getIllustrations().isPresent());
    Assertions.assertEquals(0, gvl.getFeatures().get(0).getIllustrations().get().size());
    
    Assertions.assertEquals(1, gvl.getSpecialFeatures().get(0).getId());
    Assertions.assertEquals("Use precise geolocation data", gvl.getSpecialFeatures().get(0).getName());
    Assertions.assertEquals("With your acceptance, your precise location (within a radius of less than 500 metres) may be used in support of the purposes explained in this notice.", gvl.getSpecialFeatures().get(0).getDescription());
    Assertions.assertTrue(gvl.getSpecialFeatures().get(0).getDescriptionLegal().isEmpty());
    Assertions.assertTrue(gvl.getSpecialFeatures().get(0).getIllustrations().isPresent());
    Assertions.assertEquals(0, gvl.getSpecialFeatures().get(0).getIllustrations().get().size());
    
    Assertions.assertEquals(1, gvl.getStacks().get(0).getId());
    Assertions.assertEquals("Precise geolocation data, and identification through device scanning", gvl.getStacks().get(0).getName());
    Assertions.assertEquals("Precise geolocation and information about device characteristics can be used.\n\n", gvl.getStacks().get(0).getDescription());
    Assertions.assertTrue(gvl.getStacks().get(0).getPurposes().isEmpty());
    Assertions.assertEquals(List.of(1, 2), gvl.getStacks().get(0).getSpecialFeatures());
    
    Assertions.assertEquals(2, gvl.getStacks().get(1).getId());
    Assertions.assertEquals("Advertising based on limited data and advertising measurement", gvl.getStacks().get(1).getName());
    Assertions.assertEquals("Advertising can be presented based on limited data. Advertising performance can be measured.", gvl.getStacks().get(1).getDescription());
    Assertions.assertEquals(List.of(2, 7), gvl.getStacks().get(1).getPurposes());
    Assertions.assertTrue(gvl.getStacks().get(1).getSpecialFeatures().isEmpty());
    
    Assertions.assertEquals(1, gvl.getDataCategories().get().get(0).getId());
    Assertions.assertEquals("IP addresses", gvl.getDataCategories().get().get(0).getName());
    Assertions.assertEquals("Your IP address is a number assigned by your Internet Service Provider to any Internet connection. It is not always specific to your device and is not always a stable identifier.\nIt is used to route information on the Internet and display online content (including ads) on your connected device.", gvl.getDataCategories().get().get(0).getDescription());
    
    Assertions.assertEquals(1,  gvl.getVendors().get(0).getId());
    Assertions.assertFalse(gvl.getVendors().get(0).getDeletedDate().isPresent());
    Assertions.assertEquals("Exponential Interactive, Inc d/b/a VDX.tv", gvl.getVendors().get(0).getName());
    Assertions.assertEquals(List.of(1, 2, 3, 4, 7, 8, 9, 10),  gvl.getVendors().get(0).getPurposes());
    Assertions.assertEquals(List.of(),  gvl.getVendors().get(0).getLegIntPurposes());
    Assertions.assertEquals(List.of(7, 8, 9, 10),  gvl.getVendors().get(0).getFlexiblePurposes());
    Assertions.assertEquals(List.of(1, 2),  gvl.getVendors().get(0).getSpecialPurposes());
    Assertions.assertEquals(List.of(1, 2, 3),  gvl.getVendors().get(0).getFeatures());
    Assertions.assertTrue(gvl.getVendors().get(0).getSpecialFeatures().isEmpty());
    Assertions.assertTrue(gvl.getVendors().get(0).getPolicyUrl().isEmpty());
    Assertions.assertTrue(gvl.getVendors().get(0).getCookieMaxAgeSeconds().isPresent());
    Assertions.assertEquals(7776000,  gvl.getVendors().get(0).getCookieMaxAgeSeconds().get());
    Assertions.assertTrue(gvl.getVendors().get(0).getUsesCookies().isPresent());
    Assertions.assertTrue(gvl.getVendors().get(0).getUsesCookies().get());
    Assertions.assertTrue(gvl.getVendors().get(0).getCookieRefresh().isPresent());
    Assertions.assertTrue(gvl.getVendors().get(0).getCookieRefresh().get());
    Assertions.assertTrue(gvl.getVendors().get(0).getUsesNonCookieAccess().isPresent());
    Assertions.assertFalse(gvl.getVendors().get(0).getUsesNonCookieAccess().get());
    Assertions.assertTrue(gvl.getVendors().get(0).getDataRetention().isPresent());
    Assertions.assertTrue(gvl.getVendors().get(0).getDataRetention().get().getStdRetention().isPresent());
    Assertions.assertEquals(397, gvl.getVendors().get(0).getDataRetention().get().getStdRetention().get());
    Assertions.assertTrue(gvl.getVendors().get(0).getDataRetention().get().getPurposes().isEmpty());
    Assertions.assertTrue(gvl.getVendors().get(0).getDataRetention().get().getSpecialPurposes().isEmpty());
    Assertions.assertTrue(gvl.getVendors().get(0).getUrls().isPresent());
    Assertions.assertEquals(1, gvl.getVendors().get(0).getUrls().get().size());
    Assertions.assertEquals("en", gvl.getVendors().get(0).getUrls().get().get(0).getLangId());
    Assertions.assertEquals("https://vdx.tv/privacy/", gvl.getVendors().get(0).getUrls().get().get(0).getPrivacy());
    Assertions.assertTrue(gvl.getVendors().get(0).getUrls().get().get(0).getLegIntClaim().isPresent());
    Assertions.assertEquals("https://cdnx.exponential.com/wp-content/uploads/2018/04/Balancing-Assessment-for-Legitimate-Interest-Publishers-v2.pdf", gvl.getVendors().get(0).getUrls().get().get(0).getLegIntClaim().get());
    Assertions.assertTrue(gvl.getVendors().get(0).getDataDeclaration().isPresent());
    Assertions.assertEquals(List.of(1, 3, 4, 6, 8, 10, 11), gvl.getVendors().get(0).getDataDeclaration().get());
    Assertions.assertTrue(gvl.getVendors().get(0).getDeviceStorageDisclosureUrl().isPresent());
    Assertions.assertEquals("https://vdxtv.expo.workers.dev", gvl.getVendors().get(0).getDeviceStorageDisclosureUrl().get());
    
    Assertions.assertEquals(12,  gvl.getVendors().get(4).getId());
    Assertions.assertFalse(gvl.getVendors().get(4).getDeletedDate().isPresent());
    Assertions.assertEquals("BeeswaxIO Corporation", gvl.getVendors().get(4).getName());
    Assertions.assertEquals(List.of(1, 2, 3, 4, 7),  gvl.getVendors().get(4).getPurposes());
    Assertions.assertEquals(List.of(),  gvl.getVendors().get(4).getLegIntPurposes());
    Assertions.assertEquals(List.of(),  gvl.getVendors().get(4).getFlexiblePurposes());
    Assertions.assertEquals(List.of(1, 2),  gvl.getVendors().get(4).getSpecialPurposes());
    Assertions.assertEquals(List.of(1, 3),  gvl.getVendors().get(4).getFeatures());
    Assertions.assertEquals(List.of(1),  gvl.getVendors().get(4).getSpecialFeatures());
    Assertions.assertTrue(gvl.getVendors().get(4).getPolicyUrl().isEmpty());
    Assertions.assertTrue(gvl.getVendors().get(4).getCookieMaxAgeSeconds().isPresent());
    Assertions.assertEquals(34109999,  gvl.getVendors().get(4).getCookieMaxAgeSeconds().get());
    Assertions.assertTrue(gvl.getVendors().get(4).getUsesCookies().isPresent());
    Assertions.assertTrue(gvl.getVendors().get(4).getUsesCookies().get());
    Assertions.assertTrue(gvl.getVendors().get(4).getCookieRefresh().isPresent());
    Assertions.assertTrue(gvl.getVendors().get(4).getCookieRefresh().get());
    Assertions.assertTrue(gvl.getVendors().get(4).getUsesNonCookieAccess().isPresent());
    Assertions.assertTrue(gvl.getVendors().get(4).getUsesNonCookieAccess().get());
    Assertions.assertTrue(gvl.getVendors().get(4).getDataRetention().isPresent());
    Assertions.assertTrue(gvl.getVendors().get(4).getDataRetention().get().getStdRetention().isPresent());
    Assertions.assertEquals(0, gvl.getVendors().get(4).getDataRetention().get().getStdRetention().get());
    Assertions.assertEquals(1, gvl.getVendors().get(4).getDataRetention().get().getPurposes().size());
    Assertions.assertEquals(4320, gvl.getVendors().get(4).getDataRetention().get().getPurposes().get(7));
    Assertions.assertEquals(2, gvl.getVendors().get(4).getDataRetention().get().getSpecialPurposes().size());
    Assertions.assertEquals(392, gvl.getVendors().get(4).getDataRetention().get().getSpecialPurposes().get(1));
    Assertions.assertEquals(4320, gvl.getVendors().get(4).getDataRetention().get().getSpecialPurposes().get(2));
    Assertions.assertTrue(gvl.getVendors().get(4).getUrls().isPresent());
    Assertions.assertEquals(1, gvl.getVendors().get(4).getUrls().get().size());
    Assertions.assertEquals("en", gvl.getVendors().get(4).getUrls().get().get(0).getLangId());
    Assertions.assertEquals("https://www.beeswax.com/privacy/", gvl.getVendors().get(4).getUrls().get().get(0).getPrivacy());
    Assertions.assertTrue(gvl.getVendors().get(4).getUrls().get().get(0).getLegIntClaim().isPresent());
    Assertions.assertEquals("https://www.beeswax.com/privacy/", gvl.getVendors().get(4).getUrls().get().get(0).getLegIntClaim().get());
    Assertions.assertTrue(gvl.getVendors().get(4).getDataDeclaration().isPresent());
    Assertions.assertEquals(List.of(1, 2, 3, 4, 6, 8, 9, 11), gvl.getVendors().get(4).getDataDeclaration().get());
    Assertions.assertTrue(gvl.getVendors().get(4).getDeviceStorageDisclosureUrl().isPresent());
    Assertions.assertEquals("https://beeswax.com/devicedisclosure.json", gvl.getVendors().get(4).getDeviceStorageDisclosureUrl().get());
    
    Assertions.assertTrue(gvl.getVendor(26).getCookieRefresh().isPresent());
    Assertions.assertFalse(gvl.getVendor(26).getCookieRefresh().get());
  }

}
