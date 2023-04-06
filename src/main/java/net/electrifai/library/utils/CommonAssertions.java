package net.electrifai.library.utils;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class CommonAssertions {

    private static SoftAssert softAssert;

    public static void hardAssertDataValidation(String actualData, String expectedData) {
        Assert.assertEquals(actualData, expectedData);
    }

    public static void hardAssertFailure(String message) {
        Assert.fail(message);
    }

    public static void hardAssertBooleanTrueValidation(boolean status) {
        Assert.assertTrue(status);
    }

    public static void hardAssertBooleanFalseValidation(boolean status) {
        Assert.assertFalse(status);
    }

    public static void softAssertDataValidation(String actualData, String expectedData) {
        softAssert=new SoftAssert();
        softAssert.assertEquals(actualData, expectedData);
    }

    public static void softAssertFailure(String message) {
        softAssert=new SoftAssert();
        softAssert.fail(message);
    }

    public static void softAssertBooleanTrueValidation(boolean status) {
        softAssert=new SoftAssert();
        softAssert.assertTrue(status);
    }

    public static void softAssertBooleanFalseValidation(boolean status) {
        softAssert=new SoftAssert();
        softAssert.assertFalse(status);
    }
}
