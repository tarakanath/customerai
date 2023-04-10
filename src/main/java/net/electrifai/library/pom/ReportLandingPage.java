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

    @FindBy(xpath ="//div[contains(@class,'component_content__NXEzW')]/button" )
    List<WebElement> filterCriteria;
    @FindBy(xpath = "//div[contains(@class,'component_content__NXEzW')]/button[1]")
    WebElement segmentationDropDown;
    @FindBy(xpath = "//div[contains(@class,'component_content')]/button[2]")
    WebElement propersityDropDown;
    @FindBy(xpath = "//div[contains(@class,'component_content')]/button[3]")
    WebElement dateRangeDropDown;
    @FindBy(xpath = "//div[contains(@class,'component_content__NXEzW')]//div[contains(@class,'ant-dropdown') and not(contains(@class,'ant-dropdown-hidden'))]//li")
    List<WebElement> dropDownElements;
    @FindBy(css = "//div[contains(@class,'component_content__NXEzW')]//li[contains(@class,'ant-dropdown-menu-item-selected')]")
    List<WebElement> selectedDropDownOptions;
    @FindBy(css = "button[class*='ant-btn ant-btn-primary']:not([class*='ant-dropdown-trigger'])")
    WebElement applyFiltersButton;
    String logMessage;

    public void selectOptionFromGivenDropDown(String dropDown, String option) {
         try{
            for(WebElement element:filterCriteria){
                if(element.getText().contains(dropDown)){
                    GenericPageActions.moveToElement(element, dropDown+" Dropdown");
                    selectGivenOptionFromDropDown(option);
                }
            }
        }catch (Exception e){
            logMessage = "Given drop down is not available";
            LogManager.printExceptionLog(e,logMessage);
            Assert.fail(logMessage);
        }

    }

    public void selectGivenOptionFromDropDown(String option) {
        boolean condition = false;
        try {

            //Thread.sleep(1000);
            Wait.explicitWait(dropDownElements.get(0),"visibility");
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

}
