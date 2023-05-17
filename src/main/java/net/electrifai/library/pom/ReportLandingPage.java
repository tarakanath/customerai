package net.electrifai.library.pom;

import net.electrifai.library.utils.GenericPageActions;
import net.electrifai.library.utils.LogManager;
import net.electrifai.library.utils.Wait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import javax.swing.plaf.TableHeaderUI;
import java.util.List;

public class ReportLandingPage extends HomePage {

    @FindBy(xpath = "//div[@class='component_content__NXEzW']/button")
    List<WebElement> filterCriteria;
    @FindBy(xpath = "//div[@class='component_content__NXEzW']//div[contains(@class,'ant-dropdown') and not(contains(@class,'ant-dropdown-hidden'))]//li")
    List<WebElement> dropDownElements;
    @FindBy(css = "//div[@class='component_content__NXEzW']//li[contains(@class,'ant-dropdown-menu-item-selected')]")
    List<WebElement> selectedDropDownOptions;
    @FindBy(css = "button[class*='ant-btn ant-btn-primary']:not([class*='ant-dropdown-trigger'])")
    WebElement applyFiltersButton;
    String logMessage;
    @FindBy(xpath = "(//div[@class='ant-space-item'])[3]")
    WebElement propensity_link_cross_sell;

    public void selectOptionFromGivenDropDown(String dropDown, String option) {
        try {
            for (WebElement element : filterCriteria) {
                if (element.getText().contains(dropDown)) {
                    GenericPageActions.moveToElement(element, dropDown + " Dropdown");
                    selectGivenOptionFromDropDown(option);
                    // below step has been added to avoid synchronization issue
                    GenericPageActions.moveToElement(userIcon, "user icon");
                }
            }
        } catch (Exception e) {
            logMessage = "Given drop down is not available";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(logMessage);
        }

    }

    public void selectGivenOptionFromDropDown(String option) {
        boolean condition = false;
        try {

            //Thread.sleep(1000);
            Wait.explicitWait(dropDownElements.get(0), "visibility");
            for (WebElement element : dropDownElements
            ) {
                if (element.getText().trim().equals(option)) {
                    GenericPageActions.click(element, "Dropdown option " + option);
                    condition = true;
                    break;
                }

            }
            if (!condition) {
                logMessage = "Drop down option " + option + " not available";
                Assert.fail(logMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logMessage = "Drop down option " + option + " selection failed";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(e.getMessage());
        }

    }

    public void clickOnApplyButton() {
        GenericPageActions.isElementEnabled(applyFiltersButton, "apply filter button");
        GenericPageActions.click(applyFiltersButton, "apply filter button");
    }

    public void verifyPropensitySelection(String expectedPropensity) {
        Assert.assertEquals(filterCriteria.get(1).getText().trim(), expectedPropensity, expectedPropensity + " selected");
        String logMessage = "Propensity " + expectedPropensity + " selection validated successfully";
        LogManager.printInfoLog(logMessage);

    }
//
//    public String verifyPropensityWithDefaultSelection(String expectedPropensity){
//        String productName =  propensity_link_cross_sell.getText();
//        LogManager.printInfoLog("The product name is"+ productName);
//        return productName.trim();
//    }



}
