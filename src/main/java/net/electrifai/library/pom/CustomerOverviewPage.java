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




    @FindBy(xpath = "//div[@class='customerOverview_title__o_i4H']/span")
    WebElement myProductsHeadings;


}
