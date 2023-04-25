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

    @FindBy(xpath = "//div[@class='customerOverview_top__BKMWM']/div[1]")
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
    String tt = "child::div[1]/div";
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


    public void getTopTitle() {

        String customer360heading = "Customers Over 365 Days";
        try {
            String customersHeading = Customeroverview365days.getText();
            LogManager.printInfoLog("Heading: " + customersHeading);
            Assert.assertEquals(customersHeading, (customer360heading), "Value does not matches");
        }
        catch(NoSuchElementException e){
                e.printStackTrace();
                String logMessage = "The Value doesn't match";
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
            if (title_List.equals(title)) {
            System.out.println("The two ArrayLists are equal");
        }
            else{
            System.out.println("The two ArrayLists are not equal");
        }}

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
            if (bottom_List.equals(bottomTitle)) {
                LogManager.printInfoLog("The two Bottom ArrayLists are equal");
            } else {
                LogManager.printInfoLog("The two ArrayLists are not equal");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            String logMessage = "The product list doesn't match";
            LogManager.printExceptionLog(e, logMessage);
        }
    }
    public void countCheck() {
        //List<Integer> counts = new ArrayList<>();
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
            if (total == totalCustomersCalculation) {
                LogManager.printInfoLog("Total Customer Count matches with UI");
            } else {

                LogManager.printInfoLog("Total Customer Count does not matches with UI");

            }
        }catch(Exception e) {
            e.printStackTrace();
            String logMessage = "Value does not match";
            LogManager.printExceptionLog(e, logMessage);
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
        String PercentageforActiveCustomer = String.format("%.1f", Math.round(percentage * 10.0) / 10.0);
        double percentage2 = ((double) num2 / total) * 100;
        String PercentageforNewlyOnboarded = String.format("%.1f", Math.round(percentage2 * 10.0) / 10.0);
        String inactivValueUI = inactiveCustomerPercentage.getText();
        String strPercentageWithoutPercentSign = inactivValueUI.replace("%", "");
        String newlyonboardedValue = newlyOnboardedCountPercentage.getText();
        String strPercentageWithoutPercentSign2 = newlyonboardedValue.replace("%", "");
        if (strPercentageWithoutPercentSign.equals(PercentageforActiveCustomer)) {
            LogManager.printInfoLog("Percentage for Active customers matches with UI");
        } else {
            LogManager.printInfoLog("Percentage for active customers does not match the UI.");
        }
        if (strPercentageWithoutPercentSign2.equals(PercentageforNewlyOnboarded)) {
            LogManager.printInfoLog("Percentage for Newly Onboarded Customer matches with UI");
        } else {
            LogManager.printInfoLog("Percentage for Newly Onboarded Customers does not match with UI");
        }
        }
    }










