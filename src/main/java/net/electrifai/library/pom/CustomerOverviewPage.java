package net.electrifai.library.pom;

import net.electrifai.library.utils.DBUtils;
import net.electrifai.library.utils.LogManager;
import net.electrifai.library.utils.excelsheet.ReadAndWriteExcel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.*;

import static org.testng.Assert.assertEquals;

public class CustomerOverviewPage extends HomePage {
    String excelFilePath = projectPath + property.getString("testDataPath");
    public static Map<String, String> data = null;
    @FindBy(xpath = "//div[@class='customerOverview_top__BKMWM']/div/div")
    WebElement customerOverView365days;
    @FindBy(xpath = "//div[@class=\'customerOverview_customerItem__WvKrU customerItem\']")
    List<WebElement> customerData;
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

    public void getTopTitle() {
            String customer360heading = "Customers Over 365 Days";
            String customersHeading = customerOverView365days.getText();
            assertEquals(customersHeading, (customer360heading), "\"Heading " + customersHeading + " does not Validated Successfully\"");
            LogManager.printInfoLog("Heading " + customersHeading + " Validated Successfully");
    }
    public void customerTitleHeading(String fileName, String sheetName, String dataRowNum) {
        fileName = "" + fileName + ".xlsx";
        data = ReadAndWriteExcel.readExcelTabRowNew(excelFilePath, fileName, sheetName, dataRowNum);
        List<String> title_List = Arrays.asList(data.get("Top headings").split("\\|"));
        LogManager.printInfoLog("The title list from excel is: " + title_List);
        List<String> title = new ArrayList<>();
        for (WebElement element : customerData) {
            String text = element.findElement(By.xpath(titleHeading)).getText();
            title.add(text);
        }
        LogManager.printInfoLog("Title Headings from UI :" + title);
        assertEquals(title_List, (title), "The title list does not matches the expected title.");
        LogManager.printInfoLog("Customer Overview Heading text Validated Successfully");
        }
    public void customerBottomHeading(String fileName, String sheetName, String dataRowNum) {
        fileName = "" + fileName + ".xlsx";
        data = ReadAndWriteExcel.readExcelTabRowNew(excelFilePath, fileName, sheetName, dataRowNum);
        List<String> bottom_List = Arrays.asList(data.get("Bottom headings").split("\\|"));
        LogManager.printInfoLog("Customer OverView Widgets Bottom text list from excel is: " + bottom_List);
        List<String> bottomTitle = new ArrayList<>();
        for (WebElement element : customerData) {
            String text = element.findElement(By.xpath(bottomHeading)).getText();
            bottomTitle.add(text);
        }
        LogManager.printInfoLog("Customer OverView Widgets Bottom text list from UI is : " + bottomTitle);
        assertEquals(bottom_List, (bottomTitle), "Customer OverView Bottom Widgets text do not  validated Successfully ");
        LogManager.printInfoLog("Customer OverView Widgets Bottom text Validated Successfully");
    }
    public void countCheck() {
        List<Map<String, String>> data = DBUtils.getDataFromStatement("SELECT * FROM customerengagementqa.customer_products_aggregated");
        Map<String, String> row = data.get(0);
        int dbNewlyOnboardedCustomerCount = Integer.parseInt(row.get("new_customer_count"));
        int dbActiveCustomerCount = Integer.parseInt(row.get("active_customer_count"));
        int dbTotalCustomerCount = Integer.parseInt(row.get("customer_count"));
        LogManager.printInfoLog("Newly Onbaorded Customer Count from DB: " + dbNewlyOnboardedCustomerCount);
        LogManager.printInfoLog("Active Customer Count from DB: " + dbActiveCustomerCount);
        LogManager.printInfoLog("Total Customer Count from DB: " + dbTotalCustomerCount);
        List<Integer> counts = new ArrayList<Integer>();
        int i=0;
        String[] Customers = {"Total Customer", "Active Customers", "Newly Onboarded Customers"};
        LogManager.printInfoLog("Checking if the count is greater than Zero or not");
        for (WebElement element : customerData) {
            String text = element.findElement(By.xpath(customerCount)).getText();
            int num = Integer.parseInt(text.replaceAll(",", ""));
            if (num > 0) {
                String message = "Count for " + Customers[i] + " " + num + " is greater than 0";
                LogManager.printInfoLog(message);
                counts.add(num);
            }
        }
        int totalCustomerCount = counts.get(0);
        int activeCustomerCount = counts.get(1);
        int newlyOnboardedCustomerCount = counts.get(2);
        LogManager.printInfoLog("Comparing Customer Counts from DB and UI");
        assertEquals(dbTotalCustomerCount, totalCustomerCount, "Total customer count does not match.");
        LogManager.printInfoLog("Total customer count matches with DB " + dbTotalCustomerCount);
        assertEquals(dbActiveCustomerCount, activeCustomerCount, "Active customer count does not match.");
        LogManager.printInfoLog("Active customer count matches with DB " + dbActiveCustomerCount);
        assertEquals(dbNewlyOnboardedCustomerCount, newlyOnboardedCustomerCount, "New customer count does not match.");
        LogManager.printInfoLog("Newly Onboarded Customer count matches with DB " + dbNewlyOnboardedCustomerCount);
        LogManager.printInfoLog("DB Validation Successful");
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
        assertEquals(total, totalCustomersCalculation, "Total Customer of Active and Inactive Customers does not match with UI." + totalCustomersCalculation);
        LogManager.printInfoLog("Total Customer of Active and Inactive Customers matches with UI : " +total);
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
        String percentageForNewlyOnboarded = String.format("%.1f", Math.round(percentage2 * 10.0) / 10.0);
        String activeValueUI = activeCustomerPercentage.getText();
        String strPercentageWithoutPercentSign = activeValueUI.replace("%", "");
        String newlyOnboardedValueUI = newlyOnboardedCountPercentage.getText();
        String strPercentageWithoutPercentSign2 = newlyOnboardedValueUI.replace("%", "");
        assertEquals(strPercentageWithoutPercentSign, percentageForActiveCustomer, "Active customers does not match the UI "+percentageForActiveCustomer);
        LogManager.printInfoLog("Active customers Percentage " +strPercentageWithoutPercentSign+ " Validated Successful");
        assertEquals(strPercentageWithoutPercentSign2, percentageForNewlyOnboarded, "Percentage for Newly Onboarded Customers does not match with UI "+percentageForNewlyOnboarded);
        LogManager.printInfoLog("Newly Customers Percentage " +strPercentageWithoutPercentSign2+ " Validated Successful");
    }
    }










