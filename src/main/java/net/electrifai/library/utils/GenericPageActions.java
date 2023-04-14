package net.electrifai.library.utils;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static net.electrifai.library.utils.LogManager.scenario;

public class GenericPageActions {
    /**
     * Method to click on element  using selenium
     *
     * @param element     = webElement
     * @param elementName = Name of the element to  display in the report
     */
    public static void click(WebElement element, String elementName) {
        try {
            Wait.waitUntilElementToBeClickable(element);
            ((JavascriptExecutor) ThreadLocalManager.getDriver()).executeScript("arguments[0].scrollIntoView(false);", element);
            element.click();
            LogManager.printInfoLog("Clicked on  " + getElementName(elementName) + "");
        } catch (Exception exception) {
            exception.printStackTrace();
            String logMessage = "could not perform click action on \'" + getElementName(elementName) + "\'  " + extractElementType(elementName) + "";
            LogManager.printExceptionLog(exception, logMessage);

            Assert.fail(exception.getMessage());

        }
    }

    /**
     * Method to click on element via javascript
     *
     * @param element     = WebElement
     * @param elementName = Name of the element to  display in the report
     */
    public static void clickOnElementViaJavascript(WebElement element, String elementName) {
        try {
            Wait.waitUntilElementToBeClickable(element);
            JavascriptExecutor executor = (JavascriptExecutor) ThreadLocalManager.getDriver();
            executor.executeScript("arguments[0].scrollIntoView(true);", element);
            executor.executeScript("arguments[0].click();", element);
            LogManager.printInfoLog("Clicked on  " + getElementName(elementName) + "");
        } catch (Exception exception) {
            exception.printStackTrace();
            LogManager.printInfoLog("As javascript click not working trying actions click");
            //GenericPageActions.actionClick(element,elementName);  //Need to check (Findings)
            String logMessage = "could not perform click action on \'" + getElementName(elementName) + "\'  " + extractElementType(elementName) + "";
            LogManager.printExceptionLog(exception, logMessage);
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * Method  to  move to element and  then click  on element via Action class functions
     *
     * @param element     = WebElement
     * @param elementName = Name of the element to  display in the report
     */
    public static void actionClick(WebElement element, String elementName) {
        Actions action = new Actions(ThreadLocalManager.getDriver());
        try {
            Wait.waitUntilElementToBeClickable(element);
            ((JavascriptExecutor) ThreadLocalManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            action.moveToElement(element).click(element).build().perform();
            LogManager.printInfoLog("Clicked on  " + getElementName(elementName) + "");
        } catch (Exception exception) {
            exception.printStackTrace();
            String logMessage = "could not perform action click on \'" + getElementName(elementName) + "\'  " + extractElementType(elementName) + "";
            LogManager.printExceptionLog(exception, logMessage);
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * Method  to  move to element and  then doubleClick  on element via Action class functions
     *
     * @param element     = WebElement
     * @param elementName = Name of the element to  display in the report
     */
    public static void actionDoubleClick(WebElement element, String elementName) {
        Actions action = new Actions(ThreadLocalManager.getDriver());
        try {
            Wait.waitUntilElementToBeClickable(element);
            ((JavascriptExecutor) ThreadLocalManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            action.moveToElement(element).doubleClick(element).build().perform();
            LogManager.printInfoLog("Clicked on  " + getElementName(elementName) + "");
        } catch (Exception exception) {
            exception.printStackTrace();
            String logMessage = "could not perform action doubleClick on \'" + getElementName(elementName) + "\'  " + extractElementType(elementName) + "";
            LogManager.printExceptionLog(exception, logMessage);
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * Method to enter value into textField via selenium sendKeys function
     *
     * @param element     = WebElement
     * @param elementName = = Name of the element to  display in the report
     * @param inputData   = Input data to enter into textField
     */
    public static void enterTextOnElement(WebElement element, String elementName, String inputData) {
        try {
            Wait.waitUntilElementToBeClickable(element);
            // ((JavascriptExecutor) ThreadLocalManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            element.clear();
            // element.sendKeys(Keys.DELETE);
            element.sendKeys(inputData.trim());
            LogManager.printInfoLog("Entered text into  " + getElementName(elementName) + " as \"" + inputData + "\"");
        } catch (Exception exception) {
            exception.printStackTrace();
            String logMessage = "could not perform enterTextOnElement action on \'" + getElementName(elementName) + "\'  " + extractElementType(elementName) + "";
            LogManager.printExceptionLog(exception, logMessage);
            Assert.fail(exception.getMessage());

        }
    }

    /**
     * Method to upload file
     *
     * @param element        = WebElement
     * @param elementName    = Name of the element to  display in the report
     * @param fileUploadPath = Absolute path of the file to be uploaded
     */
    public static void uploadImage(WebElement element, String elementName, String fileUploadPath) {
        try {
            ((JavascriptExecutor) ThreadLocalManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            // String filePathToUpload = fileUploadPath.replace("\\", "/");
            element.sendKeys(fileUploadPath);
            String[] filePath = fileUploadPath.split("/");
            String file = filePath[filePath.length - 1];
            LogManager.printInfoLog("Uploaded " + file + " file via  " + getElementName(elementName) + "");
        } catch (Exception exception) {
            exception.printStackTrace();
            String logMessage = "could not perform upload image action on \'" + getElementName(elementName) + "\'  " + extractElementType(elementName) + "";
            LogManager.printExceptionLog(exception, logMessage);
            Assert.fail(exception.getMessage());
        }

    }

    public static void uploadImageLayout(WebElement element, String elementName, String fileUploadPath) {
        try {
            ((JavascriptExecutor) ThreadLocalManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            // String filePathToUpload = fileUploadPath.replace("\\", "/");
            element.sendKeys(fileUploadPath);
            String[] filePath = fileUploadPath.split("/");
            String file = filePath[filePath.length - 1];
            LogManager.printInfoLog("Uploaded " + file + " file via  " + getElementName(elementName) + "");
        } catch (Exception exception) {
            exception.printStackTrace();
            String logMessage = "could not perform upload image action on \'" + getElementName(elementName) + "\'  " + extractElementType(elementName) + "";
            LogManager.printExceptionLog(exception, logMessage);
            Assert.fail(exception.getMessage());
        }

    }

    /**
     * Method to verify the display of the element
     *
     * @param element     =   WebElement
     * @param elementName = Name of the element to  display in the report
     */
    public static void isElementDisplayed(WebElement element, String elementName) {
        try {
            Wait.waitUntilElementToBeClickable(element);
            ((JavascriptExecutor) ThreadLocalManager.getDriver()).executeScript("arguments[0].scrollIntoView(false);", element);
            Assert.assertTrue(element.isDisplayed());
            LogManager.printInfoLog("" + getElementName(elementName) + " is displayed ");

        } catch (Exception exception) {
            exception.printStackTrace();
            String logMessage = "could not find  \'" + getElementName(elementName) + "\'  " + getElementName(elementName) + "";
            LogManager.printExceptionLog(exception, logMessage);
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * Method to verify the element is not displayed with text
     *
     * @param element     = WebElement
     * @param elementName = Name of the element to  display in the report
     */
    public static void isElementNotDisplayed(WebElement element, String elementName) {
        try {
            Assert.assertTrue(element.isDisplayed());
            String logMessage = "Element is still displayed as " + element.getText() + "";
            LogManager.printFailLog(logMessage);
            Assert.fail(logMessage);
        } catch (Exception exception) {
            LogManager.printPassLog("" + getElementName(elementName) + " is not displayed successfully");
        }
    }

    /**
     * Method to verify whether actual text of the element is as expected text or not
     *
     * @param element        = WebElement
     * @param elementName    = Name of the element to  display in the report
     * @param expectedResult = Expected result
     */
    public static void isElementDisplayedWithExpectedText(WebElement element, String elementName, String expectedResult) {
        try {

            ((JavascriptExecutor) ThreadLocalManager.getDriver()).executeScript("arguments[0].scrollIntoView(false);", element);
            Assert.assertTrue(element.isDisplayed());
            String actualResult = element.getText().trim();
            Assert.assertEquals(actualResult, expectedResult.trim());
            LogManager.printInfoLog(elementName + " displayed with text" + actualResult);

        } catch (Exception exception) {
            exception.printStackTrace();
            String logMessage = "could not find  \'" + getElementName(elementName) + "\'  " + extractElementType(elementName) + "";
            LogManager.printExceptionLog(exception, logMessage);
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * Method to verify whether actual text of the element is not equal to expected text
     *
     * @param element        = WebElement
     * @param elementName    = Name of the element to  display in the report
     * @param expectedResult = Expected result
     *                       Author - Taniya Gulia
     */


    public static void isElementNotDisplayedWithExpectedText(WebElement element, String elementName, String expectedResult) {
        try {
            Wait.waitUntilElementToBeClickable(element);
            ((JavascriptExecutor) ThreadLocalManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            Assert.assertTrue(element.isDisplayed());
            String actualResult = element.getText().trim();
            Assert.assertNotEquals(actualResult, expectedResult.trim());
            LogManager.printInfoLog("" + actualResult + " is successfully not displaying");
        } catch (Exception exception) {
            exception.printStackTrace();
            String logMessage = " " + getElementName(elementName) + " Element is " + extractElementType(elementName) + " displaying";
            LogManager.printExceptionLog(exception, logMessage);
            Assert.fail(exception.getMessage());
        }
    }


    /**
     * Method to verify whether file downloaded or not into the expected path  or not
     *
     * @param actualResult   =  Actual file downloaded
     * @param expectedResult = expected file downloaded
     * @param filePath       = absolute file path
     */
    public static void isExpectedFileDownloaded(String actualResult, String expectedResult, String filePath) {
        try {
            Assert.assertEquals(actualResult.trim(), expectedResult.trim());
            LogManager.printInfoLog(" expected file successfully downloaded and renamed to " + filePath + "");
        } catch (Exception exception) {
            exception.printStackTrace();
            String logMessage = "could not find expected downloaded file ";
            LogManager.printExceptionLog(exception, logMessage);
            Assert.fail(exception.getMessage());

        }
    }

    /**
     * Method to verify whether element is enabled or not
     *
     * @param element     = WebElement
     * @param elementName = Name of the element to display in the report
     */
    public static void isElementEnabled(WebElement element, String elementName) {
        try {
            Wait.waitUntilElementToBeClickable(element);
            ((JavascriptExecutor) ThreadLocalManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            Assert.assertTrue(element.isEnabled());

            LogManager.printInfoLog("\'" + getElementName(elementName) + " is enabled ");

        } catch (Exception exception) {
            exception.printStackTrace();
            String logMessage = "Element is not enabled   \'" + getElementName(elementName) + "\'  " + extractElementType(elementName) + "";
            LogManager.printExceptionLog(exception, logMessage);
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * Method to verify whether element is enabled or not
     *
     * @param element     = WebElement
     * @param elementName = Name of the element to display in the report
     */
    public static void isElementNotEnabled(WebElement element, String elementName) {
        try {
            //Wait.waitUntilElementToBeClickable(element);
            ((JavascriptExecutor) ThreadLocalManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
            Assert.assertTrue(element.getAttribute("disabled").equals("true"));
            LogManager.printInfoLog(elementName + " is not enabled");

        } catch (Exception exception) {
            exception.printStackTrace();
            String logMessage = "Element is still enabled   \'" + getElementName(elementName) + "\'  " + extractElementType(elementName) + "";
            LogManager.printExceptionLog(exception, logMessage);
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * Method to select data/ value / option from the dropdownList via Select class
     *
     * @param element      = WebElement
     * @param elementName= = Name of the element to display in the report
     * @param inputData    = Data to be selected from dropdown list
     */
    public static void selectByDropdownList(WebElement element, String elementName, String inputData) {

//        try {
//            Wait.waitUntilElementToBeClickable(element);
//            ((JavascriptExecutor) ThreadLocalManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
//            Select options = new Select(element);
//            options.selectByValue(inputData.split("\\.")[0]);
//
//            LogManager.printInfoLog("Selected  " + getElementName(elementName) + " as  \"" + inputData + "\"");
//        } catch (Exception exception) {
//            exception.printStackTrace();
//            String logMessage = "Could not select option from   \'" + getElementName(elementName) + "\'  " + extractElementType(elementName) + "";
//            LogManager.printExceptionLog(exception, logMessage);
//            Assert.fail(exception.getMessage());
//        }
        try {
            element.clear();
            element.sendKeys(inputData);
        } catch (Exception e) {
            System.out.println(elementName);
        }

    }

    /**
     * @param element = Name of the element
     * @return =  Name of the element without '_' char
     */
    public static String getElementName(String element) {
        return String.valueOf(element).replace("_", " ");

    }

    /**
     * @param element = Name of the element
     * @return =  Type of the element without '_'
     */
    public static String extractElementType(String element) {

        String[] elementType = String.valueOf(element).split("_");
        if (elementType.length > 1) {
            return elementType[1];
        } else {
            return "";
        }
    }

    /**
     * Method to generate RandomName based of length
     *
     * @param length = total length of randomName
     * @return = Random Name
     */
    public static String generateRandomName(int length) {

        String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String email = "";
        String temp = RandomStringUtils.random(length, allowedChars);
        email = temp;
        return email;
    }

    /**
     * Method to generate Random special based of length
     *
     * @param length = total length of random special character
     * @return = Random special character
     */
    public static String generateRandomSpecialCharacter(int length) {

        String allowedChars = "@#$%&*";
        String specialchar = "";
        String temp = RandomStringUtils.random(length, allowedChars);
        specialchar = temp;
        return specialchar;
    }

    /**
     * Method to generate Random number based of length
     *
     * @param length = total length of random number character
     * @return = Random number
     */
    public static String generateRandomNumber(int length) {
        String allowedChars = "976" + "1234567890";
        String phonenumber = "";
        String temp = RandomStringUtils.random(length, allowedChars);
        phonenumber = ("9") + temp;
        return phonenumber;
    }

    public static void scrollToElementView(WebElement element) {
        ((JavascriptExecutor) ThreadLocalManager.getDriver()).executeScript("arguments[0].scrollIntoView(false);", element);
    }

    /**
     * Method  to  move to element via Action class functions
     *
     * @param element     = WebElement
     * @param elementName = Name of the element to  display in the report
     */
    public static void moveToElement(WebElement element, String elementName) {
        Actions action = new Actions(ThreadLocalManager.getDriver());
        try {
            Wait.waitUntilElementToBeClickable(element);
            ((JavascriptExecutor) ThreadLocalManager.getDriver()).executeScript("arguments[0].scrollIntoView(false);", element);
            action.moveToElement(element).build().perform();
            LogManager.printInfoLog("Moved Cursor On  " + getElementName(elementName) + "");
        } catch (Exception exception) {
            exception.printStackTrace();
            String logMessage = "could not perform move action on \'" + getElementName(elementName) + "\'  " + extractElementType(elementName) + "";
            LogManager.printExceptionLog(exception, logMessage);
            Assert.fail(exception.getMessage());
        }
    }


    /**
     * Method  to hover on a element
     *
     * @param element     = WebElement
     * @param elementName = Name of the element to  display in the report
     */
    public static void actionHover(WebElement element, String elementName) {
        Actions action = new Actions(ThreadLocalManager.getDriver());
        try {
            //Wait.waitUntilElementToBeClickable(element);
            ((JavascriptExecutor) ThreadLocalManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);

            action.moveToElement(element).perform();
            LogManager.printInfoLog("Hover on  " + getElementName(elementName) + "");
        } catch (Exception exception) {
            exception.printStackTrace();
            String logMessage = "could not perform Hover on \'" + getElementName(elementName) + "\'  " + extractElementType(elementName) + "";
            LogManager.printExceptionLog(exception, logMessage);
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * Method  to  refresh the page via javascript
     */
    public static void refreshPageViaJavascript() {

        try {
            JavascriptExecutor executor = (JavascriptExecutor) ThreadLocalManager.getDriver();
            executor.executeScript("history.go(0)");
            LogManager.printInfoLog("Successfully refreshed the page");
        } catch (Exception exception) {
            exception.printStackTrace();
            String logMessage = "could not perform refresh of the page ";
            LogManager.printExceptionLog(exception, logMessage);
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * Method to generate Random number between a specific range
     *
     * @return = Random number
     * Author - Taniya Gulia
     */
    public static String generateRandomNumberInRange(int length) {

        String allowedChars = "1234567";
        String random = RandomStringUtils.random(length, allowedChars);
        return random;


    }

    public static void switchToChildTabAndClose() {
        List<String> multipleTabs = new ArrayList<>(ThreadLocalManager.getDriver().getWindowHandles());
        LogManager.printInfoLog("Number of Tabs In Chrome Browser:" + multipleTabs.size());
        ThreadLocalManager.getDriver().switchTo().window(multipleTabs.get(1));
        ThreadLocalManager.getDriver().close();
        ThreadLocalManager.getDriver().switchTo().window(multipleTabs.get(0));
    }

    public static void switchToChildFrame(WebElement webElement) {
        ThreadLocalManager.getDriver().switchTo().frame(webElement);
    }

    public static void switchToParentFrame() {
        ThreadLocalManager.getDriver().switchTo().parentFrame();
    }

    public static void compareGivenLists(List<String> actualList, String nameOfActualList, List<String> expectedList, String nameOfExpectedList) {
        String logMessage;
        try {
            Assert.assertEquals(actualList.stream().sorted().collect(Collectors.toList()), expectedList.stream().sorted().collect(Collectors.toList()));
            logMessage = nameOfActualList + " validated successfully";
            LogManager.printInfoLog(logMessage);

        } catch (Exception e) {
            logMessage = nameOfActualList + " " + nameOfExpectedList + " not matched";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(logMessage);
        }
    }

    public static void takeScreenShot() {
        String screenShotName = "Screenshot" + generateRandomName(10);
        scenario.attach(Screenshot.getScreenShot(), "image/png", screenShotName);
        LogManager.printInfoLog("Screen shot captured with image name " + screenShotName);
    }
}