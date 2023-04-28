package net.electrifai.library.pom;

import lombok.extern.java.Log;
import net.electrifai.library.utils.GenericPageActions;
import net.electrifai.library.utils.LogManager;
import net.electrifai.library.utils.Wait;
import net.electrifai.library.utils.excelsheet.ReadAndWriteExcel;
import org.apache.xpath.objects.XString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static net.electrifai.library.utils.DriverSetUp.driver;

public class CustomerOverviewPage extends HomePage {
    String excelFilePath = projectPath + property.getString("testDataPath");
    public static Map<String, String> data = null;
    ReportLandingPage reportLandingPage;

    @FindBy(xpath = "//span[contains(text(),'My Products')]")
    WebElement myProduct_Heading;
    @FindBy(xpath = "//input[@placeholder='Search for products']")
    WebElement searchProduct;
    @FindBy(xpath = "//div[@class='productItem_productName__m8VQb']")
    List<WebElement> product_Name;
    @FindBy(xpath = "//div[@class='component_title__koRQ4']")
    WebElement cross_sell_text;

    public List<String> getUIProducts() {
        List<String> UIList = new ArrayList<>();
        try {
            for (WebElement product : product_Name) {
                String products = product.getText();
                UIList.add(products);
            }
            LogManager.printInfoLog("The product list from UI is: " + UIList);
        } catch (Exception e) {
            e.printStackTrace();
            String logMessage = "The product list is not correct.";
            LogManager.printExceptionLog(e, logMessage);
        }
        return UIList;
    }

    public void getProductName(String fileName, String sheetName, String dataRowNum) {
        fileName = "" + fileName + ".xlsx";
        data = ReadAndWriteExcel.readExcelTabRowNew(excelFilePath, fileName, sheetName, dataRowNum);
        List<String> productList = Arrays.asList(data.get("product name").split("\\|"));
        LogManager.printInfoLog("The product list from excel is: " + productList);
        getUIProducts();
        try {
            Assert.assertEquals(productList, getUIProducts());
            LogManager.printInfoLog("The product list matches and are equal");
        }
        catch (Exception e) {
            e.printStackTrace();
            String logMessage = "The product list doesn't match";
            LogManager.printExceptionLog(e, logMessage);
        }


    }

    public void searchProduct(String fileName, String sheetName, String dataRowNum) {
        //validate the heading of the column
        GenericPageActions.isElementDisplayedWithExpectedText(myProduct_Heading, "My Products", "My Products");
        try {
            fileName = "" + fileName + ".xlsx";
            data = ReadAndWriteExcel.readExcelTabRowNew(excelFilePath, fileName, sheetName, dataRowNum);
            String productSearch = data.get("product name");
            Actions actions = new Actions(driver);
            actions.sendKeys(searchProduct, productSearch).perform();
            for (WebElement product : product_Name) {
                String products = product.getText();
                Assert.assertTrue(products.contains(productSearch));
                LogManager.printInfoLog("The Searched product is: " + products);
            }
            searchProduct.clear();
            searchProduct.clear();
            LogManager.printInfoLog("The search bar is cleared");
        } catch (Exception e) {
            e.printStackTrace();
            String logMessage = "The product name doesn't match";
            LogManager.printExceptionLog(e, logMessage);
        }
    }

    public void reachCustomerLink(String productName) {
        try {
            GenericPageActions.refreshPageViaJavascript();
            WebElement link = driver.findElement(By.xpath("//div[@title='" + productName + "']/ancestor::div[@class='productItem_top__y_f_w']/following-sibling::div[2]"));
            GenericPageActions.scrollToElementView(link);
            Assert.assertTrue(link.getText().equals("Reach to potential customers"));
            GenericPageActions.click(link, "Reach to potential customer link");
            Wait.explicitWaitTextVerification(cross_sell_text, "Select below filters to extract the results");
            GenericPageActions.isElementDisplayed(cross_sell_text, "heading is displayed");
            verifyUserLandedOnGivenPage("Product Cross-Sell");
        }
            catch (Exception e) {
            e.printStackTrace();
            String logMessage = "Reach to potential customer link not correct";
            LogManager.printExceptionLog(e, logMessage);
        }

    }

    public void getDataCountFromProductWidget() {
        for (WebElement product : product_Name) {
            String products = product.getText();
            WebElement activeCustomerData = driver.findElement(By.xpath("//div[@title='" + products + "']/ancestor::div[@class='productItem_top__y_f_w']/following-sibling::div/div[1]"));
            WebElement inactiveCustomerData = driver.findElement(By.xpath("//div[@title='" + products + "']/ancestor::div[@class='productItem_top__y_f_w']/following-sibling::div/div[2]"));
            WebElement percentage = driver.findElement(By.xpath("//div[@title='" + products + "']/ancestor::div[@class='productItem_top__y_f_w']//div[@class='productItem_percent__mYeBG']/span[1]"));
            //get string values for active and inactive customers
            String data1 = activeCustomerData.getText();
            String data2 = inactiveCustomerData.getText();
            //get string for percentage
            String percentageChange = percentage.getText();
            //split string values ie, number and string
            String activeCountString = data1.split("\\n")[0];
            String inactiveCountString = data2.split("\\n")[0];
            String countType1 = data1.split("\\n")[1];
            String countType2 = data2.split("\\n")[1];
            //split percentage string and convert to integer
            String changeValue = percentageChange.split("\\%")[0];
            try {
                //validate if it's an integer
                int activeCount = Integer.parseInt(activeCountString.replaceAll(",", ""));
                int inactiveCount = Integer.parseInt(inactiveCountString.replaceAll(",", ""));
                //convert percentage change float to integer
                double floatValue = Double.parseDouble(changeValue);
                int perValue = (int) floatValue;
                LogManager.printInfoLog("The count for: " + products + " is " + activeCount + " and the type is: " + countType1);
                LogManager.printInfoLog("The count for: " + products + " is " + inactiveCount + " and the type is: " + countType2);
                LogManager.printInfoLog("The percentage value for: " + products + " is " + perValue);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                String logMessage = "The number is not an integer.";
                LogManager.printExceptionLog(e, logMessage);
            }

        }
    }
}







