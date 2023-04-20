package net.electrifai.library.pom;

import net.electrifai.library.utils.GenericPageActions;
import net.electrifai.library.utils.LogManager;
import net.electrifai.library.utils.excelsheet.ReadAndWriteExcel;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class CustomerOverviewPage extends HomePage{

    @FindBy(xpath = "//div[contains(text(),'Customer Overview')]")
    WebElement CO_Heading;
    @FindBy(xpath = "//span[contains(text(),'My Products')]")
    WebElement myProduct_Heading;
    @FindBy(xpath = "//input[@placeholder='Search for products']")
    WebElement searchProduct;
    @FindBy(xpath = "//div[@class='productItem_productName__m8VQb']")
    List<WebElement> product_Name;
    @FindBy(xpath = "")
    WebElement reach_potential_customers;
    @FindBy(xpath = "(//div[@class='ant-space-item'])[3]")
    WebElement propensity_link_cross_sell;
    @FindBy(xpath = "//div[@class='productItem_count__ks2QU']")
    List<WebElement> activeInactiveNumber;
    @FindBy(xpath = "//div[@class='productItem_percent__mYeBG']")
    List<WebElement> percentage_change;





    public void validatePageTitle(String string) {
        try {
            GenericPageActions.isElementDisplayed(CO_Heading, "Customer Overview");
        } catch (Exception e) {
            e.printStackTrace();
            String logMessage = "could not launch page customer overview";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(e.getMessage());
        }
    }

    public void validateProductName(){
        for (WebElement element : product_Name) {
            String product_Match = element.getText();
                if (product_Match.contains((CharSequence) product_Name)) {
                    System.out.println("Match found for: " + product_Name);
                    break;
                }
        }
    }

    public void percentageCheck(){
        for (WebElement element : percentage_change) {
            String value = element.getText();
            int intValue = Integer.parseInt(value);
            // Check if the value is greater than zero
            if (intValue > 0) {
                element.isDisplayed();
                System.out.println("The percentage change displayed are: " + percentage_change);
            }
        }
    }
    public void valueCheck(){
        for (WebElement element : activeInactiveNumber) {
            String value = element.getText();
            int intValue = Integer.parseInt(value);
            // Check if the value is greater than zero
            if (intValue > 0) {
                element.isDisplayed();
                System.out.println("The numbers displayed are: " + activeInactiveNumber);
            }
        }
    }
}


