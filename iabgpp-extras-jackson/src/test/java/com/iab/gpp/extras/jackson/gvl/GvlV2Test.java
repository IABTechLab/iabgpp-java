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

public class GvlV2Test {

  @Test
  public void test() throws IOException {
    Gvl gvl = new Loader()
        .globalVendorList(GvlV2Test.class.getClassLoader().getResourceAsStream("vendorlist/v2/vendor-list.json"));
    Assertions.assertTrue(gvl.getDataCategories().isEmpty());
    Assertions.assertEquals(3, gvl.getFeatures().size());
    Assertions.assertEquals(2, gvl.getGvlSpecificationVersion());
    Assertions.assertNotNull(gvl.getLastUpdated());
    Assertions.assertEquals(10, gvl.getPurposes().size());
    Assertions.assertEquals(2, gvl.getSpecialFeatures().size());
    Assertions.assertEquals(2, gvl.getSpecialPurposes().size());
    Assertions.assertEquals(42, gvl.getStacks().size());
    Assertions.assertEquals(2, gvl.getTcfPolicyVersion());
    Assertions.assertEquals(51, gvl.getVendorListVersion());
    Assertions.assertEquals(496, gvl.getVendors().size());

    Assertions.assertEquals(1, gvl.getPurposes().get(0).getId());
    Assertions.assertEquals("Store and/or access information on a device", gvl.getPurposes().get(0).getName());
    Assertions.assertEquals(
        "Cookies, device identifiers, or other information can be stored or accessed on your device for the purposes presented to you.",
        gvl.getPurposes().get(0).getDescription());
    Assertions.assertTrue(gvl.getPurposes().get(0).getDescriptionLegal().isPresent());
    Assertions.assertEquals(
        "Vendors can:\n* Store and access information on the device such as cookies and device identifiers presented to a user.",
        gvl.getPurposes().get(0).getDescriptionLegal().get());
    Assertions.assertTrue(gvl.getPurposes().get(0).getIllustrations().isEmpty());

    Assertions.assertEquals(1, gvl.getSpecialPurposes().get(0).getId());
    Assertions.assertEquals("Ensure security, prevent fraud, and debug", gvl.getSpecialPurposes().get(0).getName());
    Assertions.assertEquals(
        "Your data can be used to monitor for and prevent fraudulent activity, and ensure systems and processes work properly and securely.",
        gvl.getSpecialPurposes().get(0).getDescription());
    Assertions.assertTrue(gvl.getSpecialPurposes().get(0).getDescriptionLegal().isPresent());
    Assertions.assertEquals(
        "To ensure security, prevent fraud and debug vendors can:\n* Ensure data are securely transmitted\n* Detect and prevent malicious, fraudulent, invalid, or illegal activity.\n* Ensure correct and efficient operation of systems and processes, including to monitor and enhance the performance of systems and processes engaged in permitted purposes\nVendors cannot:\n* Conduct any other data processing operation allowed under a different purpose under this purpose.",
        gvl.getSpecialPurposes().get(0).getDescriptionLegal().get());
    Assertions.assertTrue(gvl.getSpecialPurposes().get(0).getIllustrations().isEmpty());

    Assertions.assertEquals(1, gvl.getFeatures().get(0).getId());
    Assertions.assertEquals("Match and combine offline data sources", gvl.getFeatures().get(0).getName());
    Assertions.assertEquals(
        "Data from offline data sources can be combined with your online activity in support of one or more purposes",
        gvl.getFeatures().get(0).getDescription());
    Assertions.assertTrue(gvl.getFeatures().get(0).getDescriptionLegal().isPresent());
    Assertions.assertEquals(
        "Vendors can:\n* Combine data obtained offline with data collected online in support of one or more Purposes or Special Purposes.",
        gvl.getFeatures().get(0).getDescriptionLegal().get());
    Assertions.assertTrue(gvl.getFeatures().get(0).getIllustrations().isEmpty());

    Assertions.assertEquals(1, gvl.getSpecialFeatures().get(0).getId());
    Assertions.assertEquals("Use precise geolocation data", gvl.getSpecialFeatures().get(0).getName());
    Assertions.assertEquals(
        "Your precise geolocation data can be used in support of one or more purposes. This means your location can be accurate to within several meters.",
        gvl.getSpecialFeatures().get(0).getDescription());
    Assertions.assertTrue(gvl.getSpecialFeatures().get(0).getDescriptionLegal().isPresent());
    Assertions.assertEquals(
        "Vendors can:\n* Collect and process precise geolocation data in support of one or more purposes.\nN.B. Precise geolocation means that there are no restrictions on the precision of a user’s location; this can be accurate to within several meters.",
        gvl.getSpecialFeatures().get(0).getDescriptionLegal().get());
    Assertions.assertTrue(gvl.getSpecialFeatures().get(0).getIllustrations().isEmpty());

    Assertions.assertEquals(1, gvl.getStacks().get(0).getId());
    Assertions.assertEquals("Precise geolocation data, and identification through device scanning", gvl.getStacks().get(0).getName());
    Assertions.assertEquals("Precise geolocation and information about device characteristics can be used.", gvl.getStacks().get(0).getDescription());
    Assertions.assertTrue(gvl.getStacks().get(0).getPurposes().isEmpty());
    Assertions.assertEquals(List.of(1, 2), gvl.getStacks().get(0).getSpecialFeatures());
   
    Assertions.assertEquals(2, gvl.getStacks().get(1).getId());
    Assertions.assertEquals("Basic ads, and ad measurement", gvl.getStacks().get(1).getName());
    Assertions.assertEquals("Basic ads can be served. Ad performance can be measured.", gvl.getStacks().get(1).getDescription());
    Assertions.assertEquals(List.of(2, 7), gvl.getStacks().get(1).getPurposes());
    Assertions.assertTrue(gvl.getStacks().get(1).getSpecialFeatures().isEmpty());
    
    Assertions.assertEquals(8,  gvl.getVendors().get(0).getId());
    Assertions.assertFalse(gvl.getVendors().get(0).getDeletedDate().isPresent());
    Assertions.assertEquals("Emerse Sverige AB", gvl.getVendors().get(0).getName());
    Assertions.assertEquals(List.of(1, 3, 4),  gvl.getVendors().get(0).getPurposes());
    Assertions.assertEquals(List.of(2, 7, 8, 9),  gvl.getVendors().get(0).getLegIntPurposes());
    Assertions.assertEquals(List.of(2, 9),  gvl.getVendors().get(0).getFlexiblePurposes());
    Assertions.assertEquals(List.of(1, 2),  gvl.getVendors().get(0).getSpecialPurposes());
    Assertions.assertEquals(List.of(1, 2),  gvl.getVendors().get(0).getFeatures());
    Assertions.assertTrue(gvl.getVendors().get(0).getSpecialFeatures().isEmpty());
    Assertions.assertTrue(gvl.getVendors().get(0).getPolicyUrl().isPresent());
    Assertions.assertEquals("https://www.emerse.com/privacy-policy/",  gvl.getVendors().get(0).getPolicyUrl().get());
    
    Assertions.assertEquals(9,  gvl.getVendors().get(1).getId());
    Assertions.assertTrue(gvl.getVendors().get(1).getDeletedDate().isPresent());
  }

}
