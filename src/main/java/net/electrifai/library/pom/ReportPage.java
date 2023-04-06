package net.electrifai.library.pom;


import net.electrifai.library.utils.GenericPageActions;
import net.electrifai.library.utils.LogManager;
import net.electrifai.library.utils.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.*;
import java.util.stream.Collectors;

public class ReportPage extends ReportLandingPage {

    boolean condition;
    // Filters dropdown
    @FindBy(css = "div[class*='component_filter'] span[class*='component_info']")
    WebElement filterHeading;
    @FindBy(xpath = "div[class*='component_filter'] button.ant-btn.ant-btn-default.ant-dropdown-trigger:nth-child(2)")
    WebElement segmentationDropDown;
    @FindBy(xpath = "div[class*='component_filter'] button.ant-btn.ant-btn-default.ant-dropdown-trigger:nth-child(3)")
    WebElement propensityDropDown;
    @FindBy(xpath = "div[class*='component_filter'] button.ant-btn.ant-btn-default.ant-dropdown-trigger:nth-child(4)")
    WebElement dateRangeDropDown;

    // customer probability elements

    @FindBy(css = "div[class*='compomentCheckbox'] label[class*='ant-checkbox-wrapper-checked']")
    List<WebElement> componentCheckedBoxNames;
    @FindBy(xpath = "//div[contains(@class,'component_checkbox')]/label[not(contains(@class,'ant-checkbox-wrapper-checked'))]")
    List<WebElement> componentUnCheckedBoxNames;
    @FindBy(css = "div[class*='compomentCheckbox'] label[class*='ant-checkbox-wrapper-checked']")
    List<WebElement> selectedComponentCheckboxes;
    @FindBy(xpath = "//span[contains(@class,'ant-slider-mark-text-active')]/span[(text())]")
    List<WebElement> selectedCustomerProbability;



    // channel dropdown elements
    @FindBy(css = "div[class*='component_title'] button[class*='ant-dropdown-trigger']")
    WebElement channelDropDown;
    @FindBy(css = "div[class*='ant-dropdown-placement-bottomLeft']:not([class*='ant-dropdown-hidden']) li span[class='ant-dropdown-menu-title-content']")
    List<WebElement> channelDropDownOptions;
    @FindBy(css = "div[class*='ant-dropdown-placement-bottomLeft']:not([class*='ant-dropdown-hidden']) input[class='ant-checkbox-input']")
    List<WebElement> channelDropDownCheckBox;

    //Drivers web elements.
    @FindBy(css = "div[class*='component_drivers'] div[class*='component_title']")
    WebElement driversTitle;
    @FindBy(css = "div[class*='component_drivers'] div[class*='component_subTitle']")
    List<WebElement> driverNames;
    @FindBy(css = "div[class*='component_drivers'] input[class*='ant-checkbox-input']")
    List<WebElement> driverCheckBoxList;
    @FindBy(css = "div[class*='component_drivers'] label[class*='ant-checkbox-wrapper-checked']")
    List<WebElement> selectedDriverCheckBoxList;
    @FindBy(css = "div[class*='component_drivers'] div[class*='component_customer']")
    List<WebElement> driverProfilesCountList;

    // profiles web elements
    @FindBy(css = "div[class*='component_profile'] span[class*='anticon-expand-alt']")
    WebElement profileExpand;
    @FindBy(css = "div[class*='component_expandContainer'] span[class*='anticon-shrink']")
    WebElement profileMinimize;
    @FindBy(css = "div[class*='component_profile'] div[class*='component_title'] span")
    WebElement profileTitleContent;
    @FindBy(css = "div.ant-list.ant-list-split.ant-list-something-after-last-item div.ant-spin-nested-loading")
    WebElement profileScroll;
    @FindBy(css = "div.ant-list.ant-list-split.ant-list-something-after-last-item li.ant-list-item")
    List<WebElement> profiles;
    @FindBy(css = "div.ant-list-item-meta div.ant-list-item-meta-avatar")
    List<WebElement> profileAvatarList;
    @FindBy(css = "div.ant-list-item-meta h4.ant-list-item-meta-title")
    List<WebElement> profilesNameList;
    @FindBy(css = "div.ant-list-item-meta div.ant-list-item-meta-description")
    List<WebElement> profileIdList;
    @FindBy(css = "li[title='Previous Page']")

    //profiles pagination elements
    WebElement profilesPreviousPage;
    @FindBy(css = "li[title='Next Page']")
    WebElement profilesNextPage;
    @FindBy(css = "li[class*='ant-pagination-item']")
    List<WebElement> profilePages;
    @FindBy(css = "li[class*='ant-pagination-item-active']")
    WebElement selectedProfilePage;
    String driverTitle;
    StringBuilder sb= new StringBuilder();
    String checkbox="input[class='ant-checkbox-input']";
    List<String> selectedDrivers;
    String componentcChebox="//div[contains(@class,'component_checkbox')]/label[(contains(@class,'ant-checkbox-wrapper'))]//span[text()='textToReplace']/preceding-sibling::span";

    // profile table

    @FindBy(xpath = "//thead[@class='ant-table-thead']//th")
    List<WebElement> profileTableHeadingList;
    @FindBy(xpath = "//tbody[@class='ant-table-tbody']//tr[@class='ant-table-row ant-table-row-level-0']")
    List<WebElement> profileTableRowList;
    public void verifyReportSelection(String pageName, Map<String, String> data) {

        String from =data.get("Customer Probability Range").split("-")[0];
        String to =data.get("Customer Probability Range").split("-")[1];
        List<String> customerProb= Arrays.asList(data.get("Customer Probability").trim().split(","));
//        List<String> drivers=Arrays.asList(data.get("Drivers").trim().split(","));
        String profileTitle=data.get("Profile Title").trim();
        String driverTitle=data.get("Drivers Title").trim();
        verifySelectionForGivenComponent(selectedComponentCheckboxes,customerProb);
        //verifySelectionForGivenComponent(selectedDriverCheckBoxList,drivers);
        verifySelectedCustomerProbability(from,to);
        Assert.assertEquals(driversTitle.getText().trim(),driverTitle);
        Assert.assertEquals(profileTitleContent.getText().split("\\(") [0].trim(),profileTitle);
       // doGivenActionOnProfile("expand");
       // doGivenActionOnProfile("minimize");

    }

    public void verifyCustomerProbabilitySelection(String pageName, Map<String, String> data){
        String from =data.get("Customer Probability Range").split("-")[0];
        String to =data.get("Customer Probability Range").split("-")[1];
        String profileTitle=data.get("Profile Title").trim();
        String driverTitle=data.get("Drivers Title").trim();
        verifySelectedCustomerProbability(from,to);
        Assert.assertEquals(driversTitle.getText().trim(),driverTitle);
        Assert.assertEquals(profileTitleContent.getText().split("\\(") [0].trim(),profileTitle);
        LogManager.printInfoLog("Customer probability range selection validated");
    }

    private void doGivenActionOnProfile(String action) {
        switch (action){
            case "expand": GenericPageActions.scrollToElementView(profileExpand);
                GenericPageActions.click(profileExpand,"Profile Expand Button");
                GenericPageActions.isElementNotDisplayed(driversTitle,"Drivers Title");
                break;
            case "minimize": GenericPageActions.scrollToElementView(profileMinimize);
                GenericPageActions.click(profileMinimize,"Profile Minimize Button");
                GenericPageActions.isElementDisplayed(driversTitle,"Drivers Title");
                break;
            default:
                logMessage = "Wrong action "+action+ " performed on profile";
                LogManager.printFailLog(logMessage);
                Assert.fail(logMessage);

        }
    }

    public void verifySelectionForGivenComponent(List<WebElement> elements, List<String> options) {
        condition = false;
        List<String> actualOptions= new ArrayList<>();
        try {
            for (WebElement element : elements) {
                actualOptions.add(element.getText().trim());
            }
            Assert.assertEquals(actualOptions.stream().sorted().collect(Collectors.toList()),options.stream().sorted().collect(Collectors.toList()));
            logMessage = "Expected options " + options + " selected";
            LogManager.printPassLog(logMessage);

        } catch (Exception e) {
            e.printStackTrace();
            String logMessage = "Expected option " + options + " verification failed";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(e.getMessage());
        }
    }

    public Map<String, String> getSelectedCustomerProbabilityRange(){
        Map<String, String> temp= new HashMap<>();
        int size=selectedCustomerProbability.size();
        temp.put("from",selectedCustomerProbability.get(size-1).getText());
        temp.put("to",selectedCustomerProbability.get(0).getText());
        return temp;
    }

    public void verifySelectedCustomerProbability(String from, String to){
        Map<String, String> temp= new HashMap<>();
        temp= getSelectedCustomerProbabilityRange();
        Assert.assertEquals(temp.get("from"),from,"from probability ");
        Assert.assertEquals(temp.get("to"),to,"to probability ");

    }


    public void updateReportSelection(String pageName, Map<String, String> data) {

        List<String> customerProb= Arrays.asList(data.get("Customer Probability").trim().split(","));
///        List<String> drivers=Arrays.asList(data.get("Drivers").trim().split(","));
       // selectCustomerProbability(customerProb);
        //selectDrivers(drivers);
    }

    public void selectCustomerProbability( String selectedOptions) {
        List<String> selectedOptionsList= Arrays.asList(selectedOptions.trim().split(","));
        for(String option: selectedOptionsList){
           if(!getWebElement(componentcChebox,option).getAttribute("class").contains("checked")){
               GenericPageActions.click(getWebElement(componentcChebox,option)," "+option);
               Wait.explicitWaitElementAttributeContains(getWebElement(componentcChebox,option), "class","checked");
           }
        }
        for(WebElement element:selectedComponentCheckboxes){

            if(!selectedOptionsList.contains(element.getText().trim())){
                element.findElement(By.cssSelector(checkbox)).click();
            }
        }
        if(selectedOptionsList.containsAll(getSelectedCustomerProbabilityOptions())){
            LogManager.printInfoLog(selectedOptions+" selected");
        } else{
            LogManager.printFailLog(selectedOptions+" not selected");
        }

    }

    private List<String > getSelectedCustomerProbabilityOptions() {
        List<String> temp= new ArrayList<>();
        for(WebElement element:selectedComponentCheckboxes) {
            temp.add(element.getText().trim());
        }
        return temp;
    }

    public void selectDrivers(List<String> options){

        try{
            for(WebElement element:driverNames){
                if(!options.contains(element.getText().trim())){
                    element.findElement(By.cssSelector(checkbox)).click();
                }
            }
            LogManager.printPassLog(options +" selected");
        }catch (Exception e) {
            e.printStackTrace();
            String logMessage = "Drivers selection failed failed";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(e.getMessage());
        }


    }



    public void verifyCustomerProbabilitySelection(String pageName, List<String> range) {
        String driverHeading="pageName Drivers for Top toRange to fromRange";
        String profileHeading="Profiles for Top toRange to fromRange" ;
        driverHeading.replace("pageName",pageName).replace("toRange",range.get(1).replace("fromRange",range.get(0)));
        profileHeading.replace("toRange",range.get(1).replace("fromRange",range.get(0)));
        GenericPageActions.isElementDisplayedWithExpectedText(driversTitle,"drivers Heading",driverHeading);
        GenericPageActions.isElementDisplayedWithExpectedText(profileTitleContent,"profile Heading",driverHeading);



    }

    public void verifyDriverSelection(List<String> driversList) {
        verifySelectionForGivenComponent(selectedDriverCheckBoxList,driversList);
        doGivenActionOnProfile("expand");
        verifyDriverSelectionInProfileExpandView(driversList);
        doGivenActionOnProfile("minimize");
    }

    private void verifyDriverSelectionInProfileExpandView(List<String> expectedDriverList) {
            List<String> tableHeaer= new ArrayList<>();
            for(WebElement element: profileTableHeadingList){
                tableHeaer.add(element.getText());
            }


    }
}
