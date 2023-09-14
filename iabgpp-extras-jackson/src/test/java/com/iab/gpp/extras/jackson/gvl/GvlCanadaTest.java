package com.iab.gpp.extras.jackson.gvl;

import com.iab.gpp.extras.gvl.Gvl;
import com.iab.gpp.extras.jackson.Loader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class GvlCanadaTest {
    @Test
    public void test() throws IOException {
        Gvl gvl = new Loader()
            .globalVendorList(GvlV2Test.class.getClassLoader().getResourceAsStream("vendorlist/v2/ca/vendor-list.json"));
        Assertions.assertFalse(gvl.getDataCategories().isPresent());
        Assertions.assertEquals(3, gvl.getFeatures().size());
        Assertions.assertEquals(2, gvl.getGvlSpecificationVersion());
        Assertions.assertNotNull(gvl.getLastUpdated());
        Assertions.assertEquals(10, gvl.getPurposes().size());
        Assertions.assertEquals(2, gvl.getSpecialFeatures().size());
        Assertions.assertEquals(2, gvl.getSpecialPurposes().size());
        Assertions.assertEquals(42, gvl.getStacks().size());
        Assertions.assertEquals(2, gvl.getTcfPolicyVersion());
        Assertions.assertEquals(45, gvl.getVendorListVersion());
        Assertions.assertEquals(36, gvl.getVendors().size());

        Assertions.assertEquals(1, gvl.getPurposes().get(0).getId());
        Assertions.assertEquals("Store and/or access information on a device", gvl.getPurposes().get(0).getName());
        Assertions.assertEquals(
            "Cookies, device identifiers, or other information can be stored or accessed on your device for the purposes presented to you.",
            gvl.getPurposes().get(0).getDescription());
        Assertions.assertTrue(gvl.getPurposes().get(0).getDescriptionLegal().isPresent());
        Assertions.assertEquals(
            "Vendors can:\n* Store and access information on the device such as cookies and device identifiers presented to a user.",
            gvl.getPurposes().get(0).getDescriptionLegal().get());
        Assertions.assertFalse(gvl.getPurposes().get(0).getIllustrations().isPresent());

        Assertions.assertEquals(1, gvl.getSpecialPurposes().get(0).getId());
        Assertions.assertEquals("Ensure security, prevent fraud, and debug", gvl.getSpecialPurposes().get(0).getName());
        Assertions.assertEquals(
            "Your data can be used to monitor for and prevent fraudulent activity, and ensure systems and processes work properly and securely.",
            gvl.getSpecialPurposes().get(0).getDescription());
        Assertions.assertTrue(gvl.getSpecialPurposes().get(0).getDescriptionLegal().isPresent());
        Assertions.assertEquals(
            "To ensure security, prevent fraud and debug vendors can:\n* Ensure data are securely transmitted\n* Detect and prevent malicious, fraudulent, invalid, or illegal activity.\n* Ensure correct and efficient operation of systems and processes, including to monitor and enhance the performance of systems and processes engaged in permitted purposes\nVendors cannot:\n* Conduct any other data processing operation allowed under a different purpose under this purpose.\nNote: Data collected and used to ensure security, prevent fraud, and debug may include automatically-sent device characteristics for identification, precise geolocation data, and data obtained by actively scanning device characteristics for identification without separate disclosure and/or opt-in.",
            gvl.getSpecialPurposes().get(0).getDescriptionLegal().get());
        Assertions.assertFalse(gvl.getSpecialPurposes().get(0).getIllustrations().isPresent());

        Assertions.assertEquals(1, gvl.getFeatures().get(0).getId());
        Assertions.assertEquals("Match and combine offline data sources", gvl.getFeatures().get(0).getName());
        Assertions.assertEquals(
            "Data from offline data sources can be combined with your online activity in support of one or more purposes",
            gvl.getFeatures().get(0).getDescription());
        Assertions.assertTrue(gvl.getFeatures().get(0).getDescriptionLegal().isPresent());
        Assertions.assertEquals(
            "Vendors can:\n* Combine data obtained offline with data collected online in support of one or more Purposes or Special Purposes.",
            gvl.getFeatures().get(0).getDescriptionLegal().get());
        Assertions.assertFalse(gvl.getFeatures().get(0).getIllustrations().isPresent());

        Assertions.assertEquals(1, gvl.getSpecialFeatures().get(0).getId());
        Assertions.assertEquals("Use precise geolocation data", gvl.getSpecialFeatures().get(0).getName());
        Assertions.assertEquals(
            "Your precise geolocation data can be used in support of one or more purposes. This means your location can be accurate to within several meters.",
            gvl.getSpecialFeatures().get(0).getDescription());
        Assertions.assertTrue(gvl.getSpecialFeatures().get(0).getDescriptionLegal().isPresent());
        Assertions.assertEquals(
            "Vendors can:\n* Collect and process precise geolocation data in support of one or more purposes.\nN.B. Precise geolocation means that there are no restrictions on the precision of a user’s location; this can be accurate to within several meters.",
            gvl.getSpecialFeatures().get(0).getDescriptionLegal().get());
        Assertions.assertFalse(gvl.getSpecialFeatures().get(0).getIllustrations().isPresent());

        Assertions.assertEquals(1, gvl.getStacks().get(0).getId());
        Assertions.assertEquals("Precise geolocation data, and identification through device scanning", gvl.getStacks().get(0).getName());
        Assertions.assertEquals("Precise geolocation and information about device characteristics can be used.", gvl.getStacks().get(0).getDescription());
        Assertions.assertTrue(gvl.getStacks().get(0).getPurposes().isEmpty());
        Assertions.assertEquals(Arrays.asList(1, 2), gvl.getStacks().get(0).getSpecialFeatures());

        Assertions.assertEquals(2, gvl.getStacks().get(1).getId());
        Assertions.assertEquals("Basic ads, and ad measurement", gvl.getStacks().get(1).getName());
        Assertions.assertEquals("Basic ads can be served. Ad performance can be measured.", gvl.getStacks().get(1).getDescription());
        Assertions.assertEquals(Arrays.asList(2, 7), gvl.getStacks().get(1).getPurposes());
        Assertions.assertTrue(gvl.getStacks().get(1).getSpecialFeatures().isEmpty());

        Assertions.assertEquals(10,  gvl.getVendors().get(0).getId());
        Assertions.assertFalse(gvl.getVendors().get(0).getDeletedDate().isPresent());
        Assertions.assertEquals("Index Exchange Inc. ", gvl.getVendors().get(0).getName());
        Assertions.assertEquals(Arrays.asList(2, 7), gvl.getVendors().get(0).getPurposes());
        Assertions.assertFalse(gvl.getVendors().get(0).getLegIntPurposes().isPresent());
        Assertions.assertTrue(gvl.getVendors().get(0).getImpConsPurposes().isPresent());
        Assertions.assertEquals(Collections.emptyList(), gvl.getVendors().get(0).getImpConsPurposes().get());
        Assertions.assertEquals(Arrays.asList(2, 7),  gvl.getVendors().get(0).getFlexiblePurposes());
        Assertions.assertEquals(Arrays.asList(1, 2),  gvl.getVendors().get(0).getSpecialPurposes());
        Assertions.assertEquals(Collections.singletonList(3), gvl.getVendors().get(0).getFeatures());
        Assertions.assertEquals(Collections.singletonList(1), gvl.getVendors().get(0).getSpecialFeatures());
        Assertions.assertTrue(gvl.getVendors().get(0).getUrls().isPresent());
        Assertions.assertEquals("https://www.indexexchange.com/privacy/",
                                gvl.getVendors().get(0).getUrls().get().get(0).getPrivacy());
        Assertions.assertEquals("en",
                                gvl.getVendors().get(0).getUrls().get().get(0).getLangId());
        Assertions.assertEquals(52,  gvl.getVendors().get(1).getId());
        Assertions.assertFalse(gvl.getVendors().get(1).getDeletedDate().isPresent());
    }
}
