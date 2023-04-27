package net.electrifai.library.pom;

import net.electrifai.library.utils.DBUtils;
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
    WebElement activeCustomerPercentage;
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
            //LogManager.printInfoLog("He: " + customersHeading);
            Assert.assertEquals(customersHeading, (customer360heading), "Value does not matches");
            LogManager.printInfoLog( "Heading " + customersHeading + " Validated Successfully");
        }
        catch(Exception e) {
            e.printStackTrace();
            String logMessage = "Value doesn't match";
            LogManager.printExceptionLog(e, logMessage);
        }
    }
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
            LogManager.printInfoLog( "Customer Overview Heading text Validated Successfully");
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
        LogManager.printInfoLog("Customer OverView Widgets Bottom text list from excel is: "+ bottom_List);
        List<String> bottomTitle = new ArrayList<>();
        for (WebElement element : customerData) {
            String text = element.findElement(By.xpath(bottomHeading)).getText();
            bottomTitle.add(text);
        }
        LogManager.printInfoLog("Customer OverView Widgets Bottom text list from UI is : " + bottomTitle);
        try {
            Assert.assertEquals(bottom_List, (bottomTitle),"Customer OverView Bottom Widgets text do not  validated Succesfully ");
            LogManager.printInfoLog( "Customer OverView Widgets Bottom text Validated Succesfully ");
        }
        catch(Exception e) {
            e.printStackTrace();
            String logMessage = "The list doesn't match";
            LogManager.printExceptionLog(e, logMessage);
        }
    }
    public void countCheck() {
        String[] Customers = {"Total Customer", "Active Customers", "Newly Onboarded Customers"};
        int i = 0;
        for (WebElement element : customerData) {
            String text = element.findElement(By.xpath(customerCount)).getText();
            int num = Integer.parseInt(text.replaceAll(",", ""));
            if (num > 0) {

                String message = "Count for " + Customers[i] + " " + num + " is greater than 0";
                LogManager.printInfoLog(message);
            }
            i++;
        }
    }
    public void totalCount() {
        String activeValue = activeCount.getText();
        int num1 = Integer.parseInt(activeValue.replaceAll(",", ""));
        LogManager.printInfoLog("Active Customer from UI:"+ num1);
        String inactiveValue = inactiveCount.getText();
        int num2 = Integer.parseInt(inactiveValue.replaceAll("[^0-9]+", ""));
        LogManager.printInfoLog("Inactive Customer from UI:"+ num2);
        int totalCustomersCalculation = num1 + num2;
        LogManager.printInfoLog("Total Customer from UI=" + totalCustomersCalculation);
        String totalValue = totalCustomerCount.getText();
        int total = Integer.parseInt(totalValue.replaceAll(",", ""));
        try {
            Assert.assertEquals(total, totalCustomersCalculation, "Total Customer of Active and Inactive Customers does not match with UI.");
            LogManager.printInfoLog("Total Customer of Active and Inactive Customers matches with UI : " +total);
        }
        catch (Exception e) {
            String logMessage = "Value not matches with UI";
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
        String activValueUI = activeCustomerPercentage.getText();
        String strPercentagewithoutpercentSign = activValueUI.replace("%", "");
        String newlyonboardedValue = newlyOnboardedCountPercentage.getText();
        String strPercentagewithoutPercentSign2 = newlyonboardedValue.replace("%", "");
        try {
            Assert.assertEquals(strPercentagewithoutpercentSign, percentageForActiveCustomer, "Active customers does not match the UI +.");
            LogManager.printInfoLog("Active customers Percentage " +strPercentagewithoutpercentSign+ " Validated Successful");
        } catch (Exception e) {
            String logMessage = "Percentage for active customers does not match the UI.";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(logMessage);
        }
        try {
            Assert.assertEquals(strPercentagewithoutPercentSign2, percentagefornewlyonboarded, "Percentage for Newly Onboarded Customers does not match with UI");
            LogManager.printInfoLog("Newly customers Percentage " +strPercentagewithoutPercentSign2+ " Validated Successful");
        } catch (Exception e) {
            String logMessage = "Percentage for Newly Onboarded Customers does not match with UI";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(logMessage);
        }
    }
    }










