package net.electrifai.library.pom;


import net.electrifai.library.utils.*;
import net.electrifai.library.utils.excelsheet.ReadAndWriteExcel;
import org.apache.commons.configuration.CompositeConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class HomePage {
    public static Map<String, String> data = null;

    public static String projectPath = System.getProperty("user.dir");

    public static CompositeConfiguration property = null;

    public static String ThreadName = null;
    public static String segmentName;

    @FindBy(xpath = "//span[@aria-label='user']")
    WebElement userIcon;

    @FindBy(xpath = "//span[@class=\"ant-dropdown-menu-title-content\"]")
    WebElement logoutButton;

    @FindBy(xpath = "//li[contains(@class,'ant-menu-item')]")
    List<WebElement> leftMenu;

    @FindBy(xpath = "//li[contains(@data-menu-id,'customerOverview')]")
    WebElement customerOverview;
    @FindBy(xpath = "//li[contains(@data-menu-id,'segmentation')]")
    WebElement segmentation;
    @FindBy(xpath = "//li[contains(@data-menu-id,'upSell')]")
    WebElement upsell;
    @FindBy(xpath = "//li[contains(@data-menu-id,'crossSell')]")
    WebElement crossSell;
    @FindBy(xpath = "//li[contains(@data-menu-id,'churn')]")
    WebElement customerChurn;
    @FindBy(xpath = "//li[contains(@data-menu-id,'campaigns')]")
    WebElement campaigns;
    @FindBy(xpath = "(//header[contains(@class,'ant-layout-header')]//div)[2]")
    WebElement pageHeading;

    public HomePage() {
        try {
            PageFactory.initElements(ThreadLocalManager.getDriver(), this);
            String environment = System.getProperty("environment");
            if (System.getProperty("environment") != null) {
                PropertiesFile.writeProperty("testEnvironment.properties", "environment", environment);
            }
            property = PropertiesFile.getProperty("testEnvironment.properties");
            String excelFilePath = projectPath + property.getString("testDataPath");
            String fileName = "" + "course" + ".xlsx";


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void navigateToSelectedPage(String pageName) {

        switch (pageName) {
            case "Segmentation":
                GenericPageActions.click(segmentation, "Segmentation");
                Wait.explicitWaitTextVerification(pageHeading, "Segmentation");
                Assert.assertEquals(pageHeading.getText(), pageName);
                LogManager.printInfoLog("User landed on " + pageHeading.getText() + " page.");
                break;

            case "Product Up-Sell":
                GenericPageActions.click(upsell, "Product_Up_Sell");
                Wait.explicitWaitTextVerification(pageHeading, "Product Up-Sell");
                Assert.assertEquals(pageHeading.getText(), pageName);
                LogManager.printInfoLog("User landed on " + pageHeading.getText() + " page.");
                break;

            case "Product Cross-Sell":
                GenericPageActions.click(crossSell, "Product_Cross_Sell");
                Wait.explicitWaitTextVerification(pageHeading, "Product Cross-Sell");
                Assert.assertEquals(pageHeading.getText(), pageName);
                LogManager.printInfoLog("User landed on " + pageHeading.getText() + " page.");
                break;

            case "Customer Churn":
                GenericPageActions.click(customerChurn, "Customer_Churn");
                Wait.explicitWaitTextVerification(pageHeading, "Customer Churn");
                Assert.assertEquals(pageHeading.getText(), pageName);
                LogManager.printInfoLog("User landed on " + pageHeading.getText() + " page.");
                break;

            case "Customer Overview":
                GenericPageActions.click(customerOverview, "Customer_Overview");
                Wait.explicitWaitTextVerification(pageHeading, "Customer Overview");
                Assert.assertEquals(pageHeading.getText(), pageName);
                LogManager.printInfoLog("User landed on " + pageHeading.getText() + " page.");
                break;
            case "Campaigns":
                GenericPageActions.click(customerOverview, "Customer_Overview");
                Wait.explicitWaitTextVerification(pageHeading, "Campaigns");
                Assert.assertEquals(pageHeading.getText(), pageName);
                LogManager.printInfoLog("User landed on " + pageHeading.getText() + " page.");
                break;

            default:
                String logMessage = "Selected page doesn't exist.";
                LogManager.printFailLog(logMessage);
                Assert.fail(logMessage);

        }

    }

    public String getPageHeading() {
        return pageHeading.getText().trim();
    }

    public WebElement getWebElement(String xpath, String i) {
        xpath = xpath.replace("textToReplace", i);
        return ThreadLocalManager.getDriver().findElement(By.xpath(xpath));
    }

    public void verifyUserLandedOnGivenPage(String expectedPage) {
        Assert.assertEquals(getPageHeading(), expectedPage, "User not landed on " + expectedPage);
        String logMessage = "User successfully landed on " + expectedPage;
        LogManager.printInfoLog(logMessage);
    }

}
