package net.electrifai.library.pom;


import net.electrifai.library.utils.GenericPageActions;
import net.electrifai.library.utils.LogManager;
import net.electrifai.library.utils.ThreadLocalManager;
import net.electrifai.library.utils.Wait;
import org.apache.commons.lang.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.*;

public class ReportPage extends ReportLandingPage {

    boolean condition;
    // Filters dropdown
    @FindBy(xpath = "//div[@class='component_title__koRQ4']/div")
    WebElement reportTitle;
    @FindBy(css = "div[class*='component_filter'] span[class*='component_info']")
    WebElement filterHeading;
    @FindBy(xpath = "//div[@class='component_filter__lQEjr']/button")
    List<WebElement> filterCriteria;
    @FindBy(xpath = "//div[@class='component_filter__lQEjr']//div[contains(@class,'ant-dropdown') and not(contains(@class,'ant-dropdown-hidden'))]//li")
    List<WebElement> dropDownElements;
    @FindBy(css = "//div[@class='component_filter__lQEjr']//li[contains(@class,'ant-dropdown-menu-item-selected')]")
    List<WebElement> selectedDropDownOptions;
    @FindBy(xpath = "div[class='component_filter__lQEjr'] button.ant-btn.ant-btn-default.ant-dropdown-trigger:first-of-type")
    WebElement segmentationDropDown;
    @FindBy(xpath = "div[class='component_filter__lQEjr'] button.ant-btn.ant-btn-default.ant-dropdown-trigger:nth-of-type(2)")
    WebElement propensityDropDown;
    @FindBy(xpath = "div[class='component_filter__lQEjr'] button.ant-btn.ant-btn-default.ant-dropdown-trigger:last-of-type")
    WebElement dateRangeDropDown;

    // customer probability elements

    @FindBy(css = "div[class*='component_checkbox__3mYm1'] label")
    List<WebElement> custProbCheckBoxes;
    @FindBy(xpath = "//div[contains(@class,'component_checkbox')]/label[not(contains(@class,'ant-checkbox-wrapper-checked'))]")
    List<WebElement> custProbUnCheckedBoxNames;
    @FindBy(css = "div[class='component_checkbox__3mYm1 compomentCheckbox'] label[class*='ant-checkbox-wrapper-checked']")
    List<WebElement> selectedCustProbCheckBoxList;
    @FindBy(xpath = "//div[@class='component_slider__lYIg8']//span[contains(@class,'ant-slider-mark-text-active')]/span[(text())]")
    List<WebElement> selectedCustomerProbability;
    @FindBy(xpath = "//div[@class='component_slider__lYIg8']//span[contains(@class,'ant-slider-mark-text')]/span[(text())]")
    List<WebElement> availableCustomerProbability;


    // channel dropdown elements
    @FindBy(css = "//div[@class='component_channelDropdown___6QtR channelDropdown']/span[last()]")
    WebElement channelDropDown;
    @FindBy(css = "//ul[@class='component_channel__xfEdb']/child::li")
    List<WebElement> channelDropDownOptions;
    @FindBy(css = "//ul[@class='component_channel__xfEdb']/child::li//span[@class='ant-checkbox']")
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
    WebElement profileMinimizeTitle;
    @FindBy(css = "div[class*='component_subTitle__jCCNJ']")
    WebElement profileExpandTitle;
    @FindBy(css = "div.ant-list.ant-list-split.ant-list-something-after-last-item div.ant-spin-nested-loading")
    WebElement profileScroll;
    @FindBy(css = "div.ant-list-item-meta div.ant-list-item-meta-avatar")
    List<WebElement> profileAvatarList;
    @FindBy(css = "div.ant-list-item-meta h4.ant-list-item-meta-title")
    List<WebElement> profilesNameList;
    @FindBy(css = "div.ant-list-item-meta div.ant-list-item-meta-description")
    List<WebElement> profileIdList;
    @FindBy(xpath = "//div[@class='component_titleContainer__uufBP']/div")
    WebElement profileTitle;
    @FindBy(css = "div[class='component_profile__oOR58'] div[class='ant-list-item-meta']")
    List<WebElement> profileList;

    //profiles pagination elements
    @FindBy(css = "li[title='Previous Page'] button")
    WebElement profilesPreviousPage;
    @FindBy(css = "li[title='Next Page'] button")
    WebElement profilesNextPage;
    @FindBy(css = "li[class*='ant-pagination-item']")
    List<WebElement> profilePages;
    @FindBy(css = "li[class*='ant-pagination-item-active']")
    WebElement selectedProfilePage;
    @FindBy(css = "li[class='ant-pagination-options'] span[class='ant-select-selection-item']")
    WebElement selectedProfilePageSize;
    @FindBy(css = "li[class='ant-pagination-options'] span[class='ant-select-selection-item']")
    WebElement pageSizeDropDown;
    @FindBy(xpath = "//div[contains(@class,'ant-select-item ant-select-item-option')]")
    List<WebElement> pageSizeDropDownOptions;
    @FindBy(xpath = "//div[@class='component_content__NXEzW']//div[@class='component_info__KcmP8']")
    WebElement currentPageIndexText;
    @FindBy(css = "li[class*='ant-pagination-item ant-pagination-item']")
    List<WebElement> profilePageNumbers;

    // profile table

    @FindBy(xpath = "//thead[@class='ant-table-thead']//th[@class='ant-table-cell']")
    List<WebElement> profileTableHeadingList;
    @FindBy(xpath = "//tbody[@class='ant-table-tbody']//tr[@class='ant-table-row ant-table-row-level-0']")
    List<WebElement> profileTableRowList;
    @FindBy(xpath = "//span[@role='button']")
    WebElement driverFilterButton;
    @FindBy(css = "div [class*='ant-table-filter-dropdown']")
    WebElement driverFilterDropDown;
    @FindBy(css = "div [class*='rc-virtual-list-holder-inner']>div")
    List<WebElement> getDriverFilterDropDownElements;

    StopWatch stopWatch = new StopWatch();

    //customer profile view elements
    @FindBy(css = "div[class='component_productName__56lGW'] span[class='anticon anticon-arrow-left']")
    WebElement custProfileClose;
    @FindBy(css = "div[class='component_personalProfile__BnMEl'] div span")
    WebElement customerName;

    String driverTitle;
    StringBuilder sb = new StringBuilder();
    String checkbox = "input[class='ant-checkbox-input']";
    List<String> selectedDrivers;
    String componentCheckbox = "//div[contains(@class,'component_checkbox')]/label[(contains(@class,'ant-checkbox-wrapper'))]//span[text()='textToReplace']/preceding-sibling::span";
    List<TreeMap<String, String>> profileTableData = new ArrayList<>();

    public void verifyReportSelection(String pageName, Map<String, String> data) {

        String from = data.get("Customer Probability Range").split("-")[0];
        String to = data.get("Customer Probability Range").split("-")[1];
        List<String> customerProb = Arrays.asList(data.get("Customer Probability").trim().split(","));

        String profileTitle = data.get("Profile Title").trim();
        String driverTitle = data.get("Drivers Title").trim();
        //verifySelectionForGivenComponent(selectedCustProbCheckBoxList, customerProb);
        verifySelectedCustomerProbabilityRange(from, to);
        Assert.assertEquals(driversTitle.getText().trim(), driverTitle);
        Assert.assertEquals(profileMinimizeTitle.getText().split("\\(")[0].trim(), profileTitle);

    }

    public void verifyCustomerProbabilitySelection(String pageName, Map<String, String> data) {
        String from = data.get("Customer Probability Range").split("-")[0];
        String to = data.get("Customer Probability Range").split("-")[1];
        String profileTitle = data.get("Profile Title").trim();
        String driverTitle = data.get("Drivers Title").trim();
        verifySelectedCustomerProbability(data.get("Customer Probability"));
        verifySelectedCustomerProbabilityRange(from, to);
        Assert.assertEquals(driversTitle.getText().trim(), driverTitle);
        Assert.assertEquals(profileMinimizeTitle.getText().split("\\(")[0].trim(), profileTitle);
        LogManager.printInfoLog("Customer probability range selection validated");
    }

    private void verifySelectedCustomerProbability(String expectedCustProb) {
        List<String> expectedCustProbList = Arrays.asList(expectedCustProb.split(","));
        List<String> temp = new ArrayList<>();
        for (WebElement element : custProbCheckBoxes) {

            if (element.getAttribute("class").contains("ant-checkbox-wrapper-checked")) {
                temp.add(element.getText().trim());
            }
        }
        GenericPageActions.compareGivenLists(temp, "Actual customer probability selection", expectedCustProbList, "Expected customer probability selection");
        LogManager.printInfoLog(temp + " selection validated");

    }

    public void doGivenActionOnProfile(String action) {

        switch (action) {
            case "expand":
                GenericPageActions.scrollToElementView(profileExpand);
                GenericPageActions.click(profileExpand, "Profile Expand Button");
                GenericPageActions.isElementDisplayed(profileMinimize, "Profile Minimize button");
                break;
            case "minimize":
                GenericPageActions.scrollToElementView(profileMinimize);
                GenericPageActions.click(profileMinimize, "Profile Minimize Button");
                GenericPageActions.isElementDisplayed(driversTitle, "Drivers Title");
                break;
            default:
                logMessage = "Wrong action " + action + " performed on profile";
                LogManager.printFailLog(logMessage);
                Assert.fail(logMessage);

        }
    }

    /*public void verifySelectionForGivenComponent(List<WebElement> elements, List<String> options) {
        condition = false;
        List<String> actualOptions = new ArrayList<>();
        try {
            for (WebElement element : elements) {
                actualOptions.add(element.getText().trim());
            }
            Assert.assertEquals(actualOptions.stream().sorted().collect(Collectors.toList()), options.stream().sorted().collect(Collectors.toList()));
            logMessage = "Expected options " + options + " selected";
            LogManager.printPassLog(logMessage);

        } catch (Exception e) {
            e.printStackTrace();
            String logMessage = "Expected option " + options + " verification failed";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(e.getMessage());
        }
    }*/

    public Map<String, String> getSelectedCustomerProbabilityRange() {
        Map<String, String> temp = new HashMap<>();
        int size = selectedCustomerProbability.size();
        temp.put("from", selectedCustomerProbability.get(size - 1).getText());
        temp.put("to", selectedCustomerProbability.get(0).getText());
        return temp;
    }

    public void verifySelectedCustomerProbabilityRange(String from, String to) {
        Map<String, String> temp = new HashMap<>();
        temp = getSelectedCustomerProbabilityRange();
        Assert.assertEquals(temp.get("from"), from, "from probability ");
        Assert.assertEquals(temp.get("to"), to, "to probability ");

    }

    public void updateReportSelection(String pageName, Map<String, String> data) {

        List<String> customerProb = Arrays.asList(data.get("Customer Probability").trim().split(","));
///        List<String> drivers=Arrays.asList(data.get("Drivers").trim().split(","));
        // selectCustomerProbability(customerProb);
        //selectDrivers(drivers);
    }

    public void selectCustomerProbability(String selectedOptions) {
        List<String> selectedOptionsList = Arrays.asList(selectedOptions.trim().split(","));
        for (String option : selectedOptionsList) {
            if (!getWebElement(componentCheckbox, option).getAttribute("class").contains("ant-checkbox-checked")) {
                GenericPageActions.click(getWebElement(componentCheckbox, option), " " + option);

            }
        }
        for (WebElement element : custProbCheckBoxes) {

            if (element.getAttribute("class").contains("ant-checkbox-wrapper-checked")) {
                if (!selectedOptionsList.contains(element.getText().trim())) {
                    element.findElement(By.cssSelector(checkbox)).click();
                }
            }

        }
        if (selectedOptionsList.equals(getTextForGivenWebElements(selectedCustProbCheckBoxList, "selected customer probability options"))) {
            LogManager.printInfoLog(selectedOptions + " selected");
        } else {
            LogManager.printFailLog(selectedOptions + " not selected");
            Assert.fail(selectedOptions + " not selected");
        }

    }


    public void selectDrivers(List<String> options) {

        try {
            for (WebElement element : driverNames) {
                // verify driver to un select when it is already checked
                if (!(options.contains(element.getText().trim())) && element.findElement(By.xpath("label")).getAttribute("class").contains("checked")) {
                    element.findElement(By.cssSelector(checkbox)).click();
                }
            }
            LogManager.printPassLog(options + " selected");
        } catch (Exception e) {
            e.printStackTrace();
            String logMessage = "Drivers selection failed failed";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(e.getMessage());
        }


    }

    public void verifyCustomerProbabilitySelection(String pageName, List<String> range) {
        String driverHeading = "pageName Drivers for Top toRange to fromRange";
        String profileHeading = "Profiles for Top toRange to fromRange";
        driverHeading.replace("pageName", pageName).replace("toRange", range.get(1).replace("fromRange", range.get(0)));
        profileHeading.replace("toRange", range.get(1).replace("fromRange", range.get(0)));
        GenericPageActions.isElementDisplayedWithExpectedText(driversTitle, "drivers Heading", driverHeading);
        GenericPageActions.isElementDisplayedWithExpectedText(profileMinimizeTitle, "profile Heading", driverHeading);

    }

    public void verifyDriverSelection(List<String> driversList) {
        List<String> selectedDrivers = new ArrayList<>(getTextForGivenWebElements(selectedDriverCheckBoxList, "selected drivers"));
        GenericPageActions.compareGivenLists(selectedDrivers, "Selected drivers", driversList, "Expected drivers");
        doGivenActionOnProfile("expand");
        List<String> prfileTableColumnNames = getTextForGivenWebElements(profileTableHeadingList, "customer profile column headings");
        prfileTableColumnNames.removeAll(List.of("Customer Name", "Customer Id"));
        GenericPageActions.compareGivenLists(prfileTableColumnNames, "Avaliable drivers in profile table columns heading",
                selectedDrivers, "Selected driver in report page");
        //getprofileTableData(false);
        doGivenActionOnProfile("minimize");


    }

    private List<String> getTextForGivenWebElements(List<WebElement> selectedDriverCheckBoxList, String elementsName) {
        List<String> temp = new ArrayList<>();
        try {
            for (WebElement element : selectedDriverCheckBoxList) {
                ((JavascriptExecutor) ThreadLocalManager.getDriver()).executeScript("arguments[0].scrollIntoView(false);", element);
                element.isDisplayed();
                temp.add(element.getText());
            }

        } catch (Exception e) {
            String logMessage = "Unable to get text for given " + elementsName;
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(logMessage);
        }


        return temp;
    }


    private List<String> getProfileHeading() {
        List<String> temp = new ArrayList<>();
        for (WebElement element : profileTableHeadingList) {
            ((JavascriptExecutor) ThreadLocalManager.getDriver()).executeScript("arguments[0].scrollIntoView(false);", element);
            temp.add(element.getText());
        }
        return temp;
    }

    private void verifyDriverSelectionInProfileExpandView(List<String> expectedDriverList) {
        System.out.println(getprofileTableData(true));

    }

    private List<TreeMap<String, String>> getprofileTableData(boolean driverData) {
        List<String> tableHeading = getProfileHeading();
        for (int i = 0; profileTableRowList.size() > i; i++) {
            List<WebElement> cells = profileTableRowList.get(i).findElements(By.xpath("td"));
            ((JavascriptExecutor) ThreadLocalManager.getDriver()).executeScript("arguments[0].scrollIntoView(false);", profileTableRowList.get(i));
            TreeMap<String, String> rowData = new TreeMap<>();
            int temp = 2;
            if (driverData) {
                temp = cells.size();
            }
            for (int j = 0; temp > j; j++) {
                ((JavascriptExecutor) ThreadLocalManager.getDriver()).executeScript("arguments[0].scrollIntoView(false);", cells.get(j));

                if (j < 2) {
                    rowData.put(tableHeading.get(j), cells.get(j).getText());

                } else {
                    rowData.put(tableHeading.get(j), String.valueOf(cells.get(j).findElements(By.xpath("child::ul/child::li[@class='ant-rate-star ant-rate-star-full']")).size()));

                }

            }
            profileTableData.add(i, rowData);
        }
        return profileTableData;
    }

    public void verifyCustomerProfilePaginationWith(String state) {
        LogManager.printInfoLog(" Profile pagination validation started when profile is " + state);
        int totalProfileCount = 0;
        verifyProfilePageSizeDropDownOptions();
        List<String> temp = getavaliablePageSizeOptions();
        // to expand page size drop down elements.
        GenericPageActions.click(pageSizeDropDown, "Page size dopw down");
        for (String s : temp) {
            for (WebElement element : pageSizeDropDownOptions) {
                if (s.equals(element.getAttribute("title"))) {
                    GenericPageActions.scrollToElementView(element);
                    GenericPageActions.click(element, element.getText());
                    Wait.explicitWait(profilePageNumbers.get(0), "visibility");
                    Wait.waitUntilElementToBeClickable(profilePageNumbers.get(0));
                    totalProfileCount = getTotalProfileCountFromGiveView(state);
                    verifyPageSizeForCurrentView(state);
                    verifyPageCount(totalProfileCount);
                    GenericPageActions.click(pageSizeDropDown, "Page size drop down");
                    Wait.explicitWait(pageSizeDropDownOptions.get(0), "visibility");
                    break;
                }

            }


        }
    }

    private void verifyProfileCountPerPageWhenProfileMinimize(int i) {
    }

    private int getTotalProfileCountFromGiveView(String state) {
        int temp = 0;
        switch (state) {
            case "minimize":
                temp = Integer.parseInt(profileMinimizeTitle.getText().split("\\(")[1].split("\\)")[0]);
                break;
            case "expand":
                temp = Integer.parseInt(profileExpandTitle.getText().split("\\(")[1].split("\\)")[0]);

        }
        return temp;
    }

    // to verify page size
    private void verifyPageSizeForCurrentView(String state) {
        int actualProfileCountPerPage= getProfileCountForCurrentPageFromSelectedView(state);
        Assert.assertEquals(actualProfileCountPerPage, getSelectedPageSize(),"Profiles count per page validate failed ");
        LogManager.printInfoLog("Profile count (" + actualProfileCountPerPage + ") validated sucessfully when profile " + state);
    }
    private int getProfileCountForCurrentPageFromSelectedView(String state){
        int actualProfileCountPerPage = 0;
        try {
            switch (state) {
                case "expand":
                    actualProfileCountPerPage = profileTableRowList.size();
                    break;
                case "minimize":
                    actualProfileCountPerPage = profileList.size();
                    break;
            }

        } catch (Exception e) {
            logMessage = "Profile count validation failed";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(logMessage);
        }
        return actualProfileCountPerPage;
    }

    private void verifyProfilePageSizeDropDownOptions() {
        List<String> expectedPageSizeOptions = new ArrayList<>(Arrays.asList("10 / page", "20 / page", "50 / page", "100 / page"));
        List<String> actualPageSizeOptions = getavaliablePageSizeOptions();
        GenericPageActions.compareGivenLists(actualPageSizeOptions, "actual Page Size Options ", expectedPageSizeOptions, "expected page size options");
        LogManager.printInfoLog("Profile page size drop down options validated sucessfully");
    }

    private void verifySelectedPageSize(String expectedPageSize, int totalProfileCount) {

        expectedPageSize = expectedPageSize + " / page";
        Assert.assertEquals(selectedProfilePageSize.getText(), expectedPageSize, "selected profile page size validation failed");
        LogManager.printInfoLog("Selected page size (" + expectedPageSize + ") validated sucessfullly");
    }

    private void verifyPageCount(int profileCount) {


        int actualProfilePageCount = Integer.parseInt(profilePageNumbers.get(profilePageNumbers.size() - 1).getAttribute("title"));
        // calculate expected page count
        int expectedProfilePageCount = getExpectedPageCount(profileCount);
        Assert.assertEquals(actualProfilePageCount, expectedProfilePageCount, " Profile page count validation failed");
        LogManager.printInfoLog("Profile Page count (" + actualProfilePageCount + ") validated");
    }

    private int getExpectedPageCount(int profileCount) {
        int selectedPageSize = getSelectedPageSize();
        return profileCount % selectedPageSize > 0 ? (profileCount / selectedPageSize) + 1 : profileCount / selectedPageSize;

    }

    private int getSelectedPageSize() {
        return Integer.parseInt(selectedProfilePageSize.getText().split(" ")[0]);
    }

    private List<String> getavaliablePageSizeOptions() {
        try {
            GenericPageActions.click(pageSizeDropDown, "Page size drop down");
            GenericPageActions.isElementDisplayed(pageSizeDropDownOptions.get(0), "page size dropdown");
            pageSizeDropDownOptions.stream().forEach(e -> e.isDisplayed());
            List<String> temp = new ArrayList<>();
            for (WebElement element : pageSizeDropDownOptions) {
                temp.add(element.getAttribute("title"));
            }
            GenericPageActions.click(pageSizeDropDown, "Page size drop down");
            return temp;
        } catch (Exception e) {
            LogManager.printExceptionLog(e, "Unable to get avaliable page size options");
        }

        return getTextForGivenWebElements(pageSizeDropDownOptions, "page size options");
    }

    public void selectOptionFromGivenDropDown(String dropDown, String option) {
        try {
            switch (dropDown) {
                case "Segmentation":
                    GenericPageActions.moveToElement(filterCriteria.get(0), dropDown + " Dropdown");
                    selectGivenOptionFromDropDown(option);
                    break;
                case "Propensity Model":
                    GenericPageActions.moveToElement(filterCriteria.get(1), dropDown + " Dropdown");
                    selectGivenOptionFromDropDown(option);
                    break;
                case "Date Range":
                    GenericPageActions.moveToElement(filterCriteria.get(2), dropDown + " Dropdown");
                    selectGivenOptionFromDropDown(option);
                    break;
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
            System.out.println(dropDownElements.get(1).getText());
            GenericPageActions.isElementDisplayed(dropDownElements.get(1), "dropdown elements");
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

    public void verifySelectedFilterCtriteria(Map<String, String> data) {
        List<String> selectedFilterCriteria = getTextForGivenWebElements(filterCriteria, "Selected filter");
        Assert.assertEquals(selectedFilterCriteria.get(0), (data.get("Segmentation")), "expected segmentation not selected");
        Assert.assertEquals(selectedFilterCriteria.get(1), (data.get("Propensity")), "expected propensity not selected");
        Assert.assertEquals(selectedFilterCriteria.get(2), (data.get("Date Range")), "expected propensity not selected");
        LogManager.printInfoLog(selectedFilterCriteria + "Segemntation, Propensity and Data Range selection validated sycessfully");

    }

    public void verifySelectedFilterCtriteriaInProfileTable(Map<String, String> data) {
        doGivenActionOnProfile("expand");
        List<String> temp = Arrays.asList(profileTitle.getText().split("\\n"));
        Assert.assertEquals(temp.get(1).trim(), data.get("Propensity"));
        Assert.assertEquals(temp.get(2).trim(), data.get("Segmentation"));
        doGivenActionOnProfile("minimize");
        LogManager.printInfoLog("Segementation,Propensity is verified on profile page sucessfully");

    }

    public void verifyNavigationToLastPage(String state) {
        int selectedPageSize=getSelectedPageSize();
        int totalProfileCount=getTotalProfileCountFromGiveView(state);
        int lastPageNumber = getExpectedPageCount(getTotalProfileCountFromGiveView(state));
        int actualLastPageProfilesCount= 0;
        int expectedLastPageProfilesCount=totalProfileCount % selectedPageSize==0?selectedPageSize:(totalProfileCount % selectedPageSize);
        WebElement lastPage = profilePageNumbers.get(profilePageNumbers.size() - 1);
        GenericPageActions.click(lastPage, "lastPage");
        actualLastPageProfilesCount= getProfileCountForCurrentPageFromSelectedView(state);
        Assert.assertEquals(actualLastPageProfilesCount,expectedLastPageProfilesCount,"Last page profile count validation failed");
        LogManager.printInfoLog("Last page ("+lastPageNumber+") profile count ("+actualLastPageProfilesCount+") validated sucessfully");
        Assert.assertEquals(Integer.parseInt(selectedProfilePage.getText().trim()), lastPageNumber);
        GenericPageActions.isElementNotEnabled(profilesNextPage, "Next Page button");

    }

    public void VerifyNavigationToFirstPage(String state) {
        WebElement firstPage = profilePageNumbers.get(0);
        GenericPageActions.click(firstPage, "firstPage");
        Assert.assertEquals(Integer.parseInt(selectedProfilePage.getText().trim()), 1);
        GenericPageActions.isElementNotEnabled(profilesPreviousPage, "Previous Page button");
    }

    public void verifyPageNavigationByOneStep(String state) {
        int numberOfPage = getExpectedPageCount(getTotalProfileCountFromGiveView(state));
        int pageSize= getSelectedPageSize();
        for (int i = 0; numberOfPage >= i; i++) {

            try {
                int intialPageNo = Integer.parseInt(selectedProfilePage.getText().trim());
                GenericPageActions.click(profilesNextPage, "Next Page");
                int selectedPageNo = Integer.parseInt(selectedProfilePage.getText().trim());
                Assert.assertEquals(selectedPageNo,intialPageNo+1);
                LogManager.printInfoLog("Navigation to page "+selectedPageNo+" validation sucessfull when profiles window "+ state);
                if(state.equals("expand")){
                    verifyShowingRecordsText(selectedPageNo);
                }
            } catch (NumberFormatException e) {
                logMessage = "Unable parse the current page number from active page webelement";
                LogManager.printExceptionLog(e, logMessage);
            }
            if (i == 3) {
                break;
            }

        }

    }

    private void verifyShowingRecordsText(int selectedPageNo) {
        int selectedPageSize=getSelectedPageSize();
        int totalRecordsCount= getTotalProfileCountFromGiveView("expand");
        String actualText=currentPageIndexText.getText().trim();
        int pageStartRecordNumber= (selectedPageNo-1)*selectedPageSize+1;
        int pageEndRecordNumber=pageStartRecordNumber+(selectedPageSize-1);
        String expectedText="Showing "+pageStartRecordNumber+" to "+pageEndRecordNumber+" of "+ totalRecordsCount+" items";
        Assert.assertEquals(actualText,expectedText,"Showing Records Text validation failed.");
        LogManager.printInfoLog(expectedText+" validation completed sucessfully");
    }

    public void validateCustomerProfileToViewWhenProfileWindow(String state) {
        WebElement element=getCustomerProfileEelementByIndexWhenProfileWindow(3,state);
        String expectedCustomerName=element.getText().trim();
        GenericPageActions.click(element,expectedCustomerName+ " profile");
        Assert.assertEquals(customerName.getText().trim(),expectedCustomerName);
        GenericPageActions.click(custProfileClose,"customer Profile Close");
        LogManager.printInfoLog("Customer profile view page validated sucessfully when profiles window "+state);


    }

    private WebElement getCustomerProfileEelementByIndexWhenProfileWindow(int i, String state) {
        switch (state){
            case "expand":

                return profileTableRowList.get(i).findElement(By.xpath("td/span"));

            case "minimize":
                return profileList.get(i).findElement(By.cssSelector("div[class='component_name__tD6uX']"));
        }
        return null;
    }

    public void setupFilterForSelectedDrivers(List<String> selectedDriver) {
        //profileTableHeadingList
    }
}
