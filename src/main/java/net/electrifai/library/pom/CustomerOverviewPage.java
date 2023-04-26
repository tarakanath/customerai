package net.electrifai.library.pom;

import net.electrifai.library.utils.LogManager;
import net.electrifai.library.utils.excelsheet.ReadAndWriteExcel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.*;

public class CustomerOverviewPage extends HomePage {
    String excelFilePath = projectPath + property.getString("testDataPath");
    public static Map<String, String> data = null;

    @FindBy(xpath = "//div[@class='customerOverview_top__BKMWM']/div/div")
    WebElement Customeroverview365days;
    @FindBy(xpath = "//div[@class=\"customerOverview_customerItem__WvKrU customerItem\"]")
    List<WebElement> customerData;
    @FindBy(xpath = "//div[@class='customerOverview_customerItem__WvKrU customerItem']/child::div[1]/div[1]/div[1]")
    List<WebElement> titleHeadings;
    @FindBy(xpath = "//div[contains(@class, 'customerItem')][2]")
    List<WebElement> customerDataa;
    String titleHeading = "div[1]/div[1]/div[1]";
    String bottomHeading = "div[3]";
    String customerCount = "div/div/div[2]";

    @FindBy(xpath = "//div[contains(@class, 'customerItem')][2]/div/div/div[2]")
    WebElement activeCount;
    @FindBy(xpath = "//div[contains(@class, 'customerItem')][2]/div[3]")
    WebElement inactiveCount;
    @FindBy(xpath = "//div[contains(@class, 'customerItem')]/div/div/div[2]")
    WebElement totalCustomerCount;
    @FindBy(xpath = "//div[contains(@class, 'customerItem')][2]/div[2]/span")
    WebElement inactiveCustomerPercentage;
    @FindBy(xpath = "//div[contains(@class, 'customerItem')][3]/div/div/div[2]")
    WebElement newlyOnboardedCount;
    @FindBy(xpath = "//div[contains(@class, 'customerItem')][3]/div[2]/span")
    WebElement newlyOnboardedCountPercentage;
    @FindBy(xpath = "//div[@class=\"customerOverview_customerItem__WvKrU customerItem\"]/")
    List<WebElement> Try;
    String a="div[1]/div[1]";

    public void getTopTitle() {
        try {
            String customer360heading = "Customers Over 365 Days";
            String customersHeading = Customeroverview365days.getText();
            LogManager.printInfoLog("Heading: " + customersHeading);
            Assert.assertEquals(customersHeading, (customer360heading), "Value does not matches");
        }
        catch(Exception e) {
            e.printStackTrace();
            String logMessage = "Value doesn't match";
            LogManager.printExceptionLog(e, logMessage);
        }}
    public void customerTitleHeading(String fileName, String sheetName, String dataRowNum) {
        fileName = "" + fileName + ".xlsx";
        data = ReadAndWriteExcel.readExcelTabRowNew(excelFilePath, fileName, sheetName, dataRowNum);
        List<String> title_List = Arrays.asList(data.get("Top headings").split("\\|"));
        LogManager.printInfoLog("The title list from excel is: "+ title_List);
        List<String> title = new ArrayList<>();
        for (WebElement element : customerData) {
            String text = element.findElement(By.xpath(titleHeading)).getText();
            title.add(text);
        }
        LogManager.printInfoLog("Title Headings from UI :" + title);
        try{
            Assert.assertEquals(title_List, (title),"The title list does not matches the expected title.");
            LogManager.printInfoLog( " The title list matches the expected titles");
        }

        catch(Exception e) {
                e.printStackTrace();
                String logMessage = "The list doesn't match";
                LogManager.printExceptionLog(e, logMessage);
            }
    }
    public void customerBottomHeading(String fileName, String sheetName, String dataRowNum) {
        fileName = "" + fileName + ".xlsx";
        data = ReadAndWriteExcel.readExcelTabRowNew(excelFilePath, fileName, sheetName, dataRowNum);
        List<String> bottom_List = Arrays.asList(data.get("Bottom headings").split("\\|"));
        LogManager.printInfoLog("The Bottom list from excel is: "+ bottom_List);
        List<String> bottomTitle = new ArrayList<>();
        for (WebElement element : customerData) {
            String text = element.findElement(By.xpath(bottomHeading)).getText();
            bottomTitle.add(text);
        }
        LogManager.printInfoLog("Bottom Headings from UI :" + bottomTitle);
        try {
            Assert.assertEquals(bottom_List, (bottomTitle),"The Bottom list does not matches the expected title.");
            LogManager.printInfoLog( " The Bottom list matches the expected titles");
        }
        catch(Exception e) {
            e.printStackTrace();
            String logMessage = "The product list doesn't match";
            LogManager.printExceptionLog(e, logMessage);
        }
    }
    public void countCheck() {
        for (WebElement element : customerData) {
            String text = element.findElement(By.xpath(customerCount)).getText();
            int num = Integer.parseInt(text.replaceAll(",", ""));
            if (num > 0) {
                LogManager.printInfoLog("Greater than 0: " + num);
            }
        }
    }
    public void totalCount() {
        String activeValue = activeCount.getText();
        int num1 = Integer.parseInt(activeValue.replaceAll(",", ""));
        String inactiveValue = inactiveCount.getText();
        int num2 = Integer.parseInt(inactiveValue.replaceAll("[^0-9]+", ""));
        int totalCustomersCalculation = num1 + num2;
        LogManager.printInfoLog("Total Customer Count=" + totalCustomersCalculation);
        String totalValue = totalCustomerCount.getText();
        int total = Integer.parseInt(totalValue.replaceAll(",", ""));
        try {
            Assert.assertEquals(total, totalCustomersCalculation, "Total Customer Count does not match with UI.");
            LogManager.printInfoLog("Total Customer Count matches with UI");
        }
        catch (Exception e) {
            String logMessage = "Value not matches";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(logMessage);
        }
    }

    public void percentageCount() {
        String activeValue = activeCount.getText();
        int num1 = Integer.parseInt(activeValue.replaceAll(",", ""));
        String newlyOnboardedValue = newlyOnboardedCount.getText();
        int num2 = Integer.parseInt(newlyOnboardedValue .replaceAll(",", ""));
        String totalValue = totalCustomerCount.getText();
        int total = Integer.parseInt(totalValue.replaceAll(",", ""));
        double percentage = ((double) num1 / total) * 100;
        String percentageForActiveCustomer = String.format("%.1f", Math.round(percentage * 10.0) / 10.0);
        double percentage2 = ((double) num2 / total) * 100;
        String percentagefornewlyonboarded = String.format("%.1f", Math.round(percentage2 * 10.0) / 10.0);
        String inactivValueUI = inactiveCustomerPercentage.getText();
        String strPercentagewithoutpercentSign = inactivValueUI.replace("%", "");
        String newlyonboardedValue = newlyOnboardedCountPercentage.getText();
        String strPercentageWithoutPercentSign2 = newlyonboardedValue.replace("%", "");
        try {
            Assert.assertEquals(strPercentagewithoutpercentSign, percentageForActiveCustomer, "Percentage for active customers does not match the UI.");
            LogManager.printInfoLog("Percentage for Active customers matches with UI");
        } catch (Exception e) {
            String logMessage = "Percentage for active customers does not match the UI.";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(logMessage);
        }
        try {
            Assert.assertEquals(strPercentageWithoutPercentSign2, percentagefornewlyonboarded, "Percentage for Newly Onboarded Customers does not match with UI");
            LogManager.printInfoLog("Percentage for Newly Onboarded Customer matches with UI");
        } catch (Exception e) {
            String logMessage = "Percentage for Newly Onboarded Customers does not match with UI";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(logMessage);
        }
    }
    }










