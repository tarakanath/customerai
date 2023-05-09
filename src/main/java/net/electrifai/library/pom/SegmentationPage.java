package net.electrifai.library.pom;

import net.electrifai.library.utils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.*;

public class SegmentationPage extends HomePage {

    @FindBy(xpath = "//div[contains(@class,'AddFilters_container')]")
    List<WebElement> segmentFilterList;
    @FindBy(xpath = "//div[contains(@class,'AddFilters_container')]/descendant::div/div[text()='Age']/parent::div//input[@type='checkbox']")
    WebElement ageCheckBox;
    @FindBy(xpath = "//div[contains(@class,'AddFilters_container')]/child::div[1]/div")
    List<WebElement> segmentFilterNameList;
    @FindBy(xpath = "//div[contains(@class,'ant-select-dropdown') and not (contains(@class,'ant-select-dropdown-hidden'))]//div[contains(@class,'ant-select-item ant-select-item-option')]")
    List<WebElement> segmentFilterDropDownOptions;
    @FindBy(css = "div.ant-select-item.ant-select-item-option.ant-select-item-option-selected")
    List<WebElement> selectedSegmentFilterDropDownOptions;
    @FindBy(xpath = "//button[@class='ant-btn ant-btn-primary']")
    WebElement segmentGenerationButton;
    @FindBy(css = "button.ant-btn.ant-btn-default.ant-dropdown-trigger")
    WebElement addSegmentDropDown;
    @FindBy(xpath = "//li[contains(@class,'ant-dropdown-menu-item-only-child')]")
    List<WebElement> addSegmentList;

    //Sgement list elements
    @FindBy(xpath = "//input[@placeholder='Search existing segments']")
    WebElement segmentSearchBox;
    @FindBy(xpath = "//div[contains(@class,'segmentation_item')]")
    List<WebElement> existingSegmentationList;
    @FindBy(css = "div.segmentation_item__cETbN.segmentItem div:first-child div:first-child")
    List<WebElement> existingSegmentationNameList;
    @FindBy(css = "div.segmentation_item__cETbN.segmentItem div:first-child div:last-child")
    List<WebElement> existingSegmentationNameBySelectedFilterList;
    String segmentationEditButton = "div.segmentation_edit__y72ss.edit div:first-child";
    String segmentationDeleteButton = "div.segmentation_edit__y72ss.edit div:last-child";
    @FindBy(xpath = "//div[contains(@class,'ant-select-dropdown-placement-bottomLeft')]//div[@class='ant-select-item-option-content']")
    List<WebElement> pages;
    @FindBy(xpath = "//div[contains(@class,'ant-pagination-options-size-changer')]")
    WebElement pageDropdown;
    @FindBy(xpath = "//li[@title='Next Page']")
    WebElement nextPage;
    @FindBy(xpath = "//li[@title='Previous Page']")
    WebElement previousPage;

    @FindBy(xpath = "//div[contains(@class,'segmentation_item')]/div[1]/div[last()]")
    List<WebElement> segmentNameByFilterSelection;
    @FindBy(xpath = "//div[@class='ant-modal-mask']/parent::div//div[@role='dialog']")
    WebElement segmentationPopup;
    @FindBy(xpath = "//div[@class='ant-modal-body']/div")
    List<WebElement> segmentPopUpMessage;
    @FindBy(xpath = "//div[@class='ant-modal-mask']/parent::div//div[@role='dialog']//button")
    List<WebElement> segmentGenerationPopupButtons;
    @FindBy(xpath = "//div[@class='ant-modal-mask']/parent::div//div[@role='dialog']//input[@class='ant-input']")
    WebElement segmentationRenameTextBox;
    @FindBy(xpath = "//div[@class='ant-modal-mask']/parent::div//div[@role='dialog']//div[text()='Save']")
    WebElement segmentRenameSaveButton;


    @FindBy(css = "div[class*='ant-message-notice-content'] div")
    WebElement message;
    @FindBy(xpath = "//div[contains(@class,'segmentation_duplicateActive')]")
    WebElement duplicateSegmentation;
    By dropDownElements = By.xpath("//div[@class='ant-select-item-option-content']");
    String checkbox = "//div[contains(@class,'AddFilters_container')]/descendant::div/div[text()='textToReplace']/parent::div//span";
    String segmentDropDown = "//div[contains(@class,'AddFilters_container')]/descendant::div/div[text()='textToReplace']/ancestor::div[contains(@class,'AddFilters_container')]//div[contains(@class,'ant-select-show-search')]";
    String segmentDropdownSearch = "(//div[@class='ant-select-selection-overflow'])[textToReplace]";


    public void selectSegmentFiltersdontuse(String selectedSegment, String segmentFiltersOption) {

        int i = 1;
        if (!getAvailableSegmentFilterFroSelected().contains(selectedSegment)) {
            addFilter(selectedSegment);
            System.out.println();
        }
        for (WebElement segment : segmentFilterList) {
            try {
                System.out.println(segment.getText());
                if (segment.getText().contains(selectedSegment) && !segment.getText().contains("Age")) {
                    //GenericPageActions.click(getWeblement(checkbox,i),selectedSegement+" checkbox");
                    GenericPageActions.click(getWebElement(checkbox, selectedSegment), selectedSegment + " checkbox");
                    GenericPageActions.click(getWebElement(segmentDropDown, selectedSegment), selectedSegment + " dropdown");
                    LogManager.printInfoLog("Segment filter " + selectedSegment + " selected");
                    selectFilterOptionsFromDropDown(segmentFiltersOption);
                    break;
                } else if (segment.getText().contains("Age") && segment.getText().contains(selectedSegment)) {
                    GenericPageActions.click(getWebElement(checkbox, selectedSegment), selectedSegment + " checkbox");
                    LogManager.printInfoLog("Segment filter " + selectedSegment + " selected");
                    break;
                }
                i++;

            } catch (Exception e) {
                e.printStackTrace();
                String logMessage = "Segment filter " + selectedSegment + " selection failed";
                LogManager.printExceptionLog(e, logMessage);
                Assert.fail(e.getMessage());
            }

        }
        LogManager.printInfoLog(segmentFiltersOption + "  options selected for " + selectedSegment + " Segmentation");

    }


    private void addFilter(String selectedSegment) {
        GenericPageActions.click(addSegmentDropDown, "Add segment dropdown");
        for (WebElement element : addSegmentList) {
            if (element.getText().trim().equals(selectedSegment)) {
                GenericPageActions.click(element, selectedSegment + " add segment " +
                        "check box");
                break;
            }
        }
        if (getAvailableSegmentFilterFroSelected().contains(selectedSegment))
            LogManager.printInfoLog(selectedSegment + " segment filter added successfully");
        else
            LogManager.printFailLog(selectedSegment + "segment filter not added");
    }

    private List<String> getAvailableSegmentFilterFroSelected() {
        List<String> temp = new ArrayList<>();
        for (WebElement element : segmentFilterNameList) {
            temp.add(element.getText());
        }
        return temp;
    }

    private void selectFilterOptionsFromDropDown(String segmentFiltersOption) throws InterruptedException {
        List<String> segmentFiltersOptionList = Arrays.asList(segmentFiltersOption.split(","));
        for (String segmentOption : segmentFiltersOptionList) {
            for (int i = 0; segmentFilterDropDownOptions.size() > i; i++) {
                if (segmentFilterDropDownOptions.get(i).getAttribute("title").equals(segmentOption)) {
                    GenericPageActions.actionClick(segmentFilterDropDownOptions.get(i), segmentOption + " option");
                    break;
                }

            }

        }

    }

    private void selectFilterOptionsFromDropDowntemp(String segmentFiltersOption) throws InterruptedException {
        List<String> segmentFiltersOptionList = Arrays.asList(segmentFiltersOption.split(","));
        for (String segmentOption : segmentFiltersOptionList) {
            System.out.println(segmentFilterDropDownOptions.size());
            for (WebElement option : segmentFilterDropDownOptions) {

                System.out.println("option text " + option.getText());
                System.out.println(option.getAttribute("title").equals(segmentOption));
                if (option.getAttribute("title").equals(segmentOption)) {
                    GenericPageActions.actionClick(option, segmentOption + " option");
                    wait(10000);
                    //GenericPageActions.click(option, segmentOption + " option");
                    break;
                }
            }
        }


    }


    public String generateSegment(Map<String, String> data) {
        try {
            GenericPageActions.click(segmentGenerationButton, "Segment Generation button");
            VerifyPopUpMessage(data.get("confirmationMessage1"), data.get("confirmationMessage2"),
                    "Confirmation");
            segmentName = getSegmentName();
            popupMessageAction("Yes");
            if (!data.get("SuccessMessage1").contains("Oops")) {
                VerifyPopUpMessage(data.get("SuccessMessage1"), data.get("SuccessMessage2"), "Success");
                // rename segment during segment generation
                popupMessageAction("Rename");
                segmentName = "AU_" + GenericPageActions.generateRandomName(10);
                GenericPageActions.isElementDisplayed(segmentationRenameTextBox, "Segment Rename Text Box");
                GenericPageActions.enterTextOnElement(segmentationRenameTextBox, "Segment Rename Text Box", segmentName);
                GenericPageActions.click(segmentRenameSaveButton, " Segment Rename Save Button");
                // verify segment rename action
                VerifyPopUpMessage(data.get("RenamedMessage1"), data.get("RenamedMessage2"), "Rename");
                confirmSegmentGeneration("Generate New Segmentation");
            } else {
                VerifyPopUpMessage(data.get("SuccessMessage1"), data.get("SuccessMessage2"), "Duplicate");
            }
            LogManager.printPassLog(segmentName + " generated Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            String logMessage = "Segment generation message failed";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(e.getMessage());
        }
        return segmentName;
    }

    private String getSegmentName() {
        return segmentPopUpMessage.get(2).getText();
    }

    public void VerifyPopUpMessage(String expectedMessage1, String expectedMessage2, String messageType) {
        try {
            Thread.sleep(2000);
            GenericPageActions.isElementDisplayed(segmentationPopup, "Segment generation " + messageType + " " +
                    "popup");
            GenericPageActions.isElementDisplayedWithExpectedText(segmentPopUpMessage.get(0), messageType, expectedMessage1);
            if (expectedMessage1.contains("Oops")) {
                GenericPageActions.isElementDisplayedWithExpectedText(segmentPopUpMessage.get(2), messageType,
                        expectedMessage2);
            } else {
                GenericPageActions.isElementDisplayedWithExpectedText(segmentPopUpMessage.get(1), messageType,
                        expectedMessage2);
            }
            LogManager.printPassLog("Segment generate " + messageType + " message validation success");
        } catch (Exception e) {
            e.printStackTrace();
            String logMessage = "Segment popup " + messageType + " message validation failed";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(e.getMessage());
        }

    }

    public void popupMessageAction(String action) {
        if (action.equalsIgnoreCase("close")) {
            GenericPageActions.click(segmentGenerationPopupButtons.get(0), "X button");
        } else {
            for (WebElement button : segmentGenerationPopupButtons) {
                if (button.getText().equals(action)) {
                    GenericPageActions.click(button, action);
                }
            }
        }
    }

    public void confirmSegmentGeneration(String action) {
        try {
            popupMessageAction(action);
        } catch (Exception e) {
            e.printStackTrace();
            String logMessage = "Segment generate " + action + " failed";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(e.getMessage());
        }

    }


    public void verifyGivenSegmentFromCurrentSegmentList(String expectedSegmentName) {
        boolean status = false;
        try {
            for (WebElement segment : existingSegmentationNameList
            ) {
                if (segment.getAttribute("title").trim().equals(expectedSegmentName)) {
                    status = true;
                    Assert.assertTrue(true);
                    LogManager.printInfoLog(expectedSegmentName + " available in segment list ");
                    break;
                }
            }
            if (!status) {
                Assert.fail("Given segment name not available in list.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            String logMessage = "Given segment name search failed";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(e.getMessage());
        }

    }

    public void verifyGivenSegmentAvailable(String expectedSegmentName) {
        String actualSegmentName = getGivenSegmentWebElement(expectedSegmentName).findElement(By.xpath("div/div")).getAttribute("title");
        Assert.assertEquals(actualSegmentName, (expectedSegmentName), "given segmentation not found");
        LogManager.printInfoLog(actualSegmentName + " segmentation found");
    }

    public void verifyGivenSegmentNotFound(String expectedSegmentName) {
        if (getGivenSegmentWebElement(expectedSegmentName) == null) {
            LogManager.printPassLog(expectedSegmentName + " not found in segment list");
        } else {
            Assert.fail(expectedSegmentName + " found in segment list");
        }
    }

    public void doGivenActionOnGivenSegment(String action, Map<String, String> data) {
        navigateToSelectedPage("Segmentation");
        WebElement element = getGivenSegmentWebElement(data.get("segmentName"));
        WebElement deleteButton = null;
        if (!(element == null)) {
            deleteButton = element.findElement(By.cssSelector(segmentationDeleteButton));
        } else {
            String log = "Segmentation is not found";
            LogManager.printFailLog("Segmentation is not found");
            Assert.fail(log);
        }
        WebElement editButton = element.findElement(By.cssSelector(segmentationEditButton));
        switch (action) {
            case "delete":
                GenericPageActions.moveToElement(element, data.get("segmentName"));
                GenericPageActions.isElementEnabled(deleteButton, "Segment Delete button");
                GenericPageActions.actionClick(deleteButton, "Segment Delete button");
                popupMessageAction("Yes");
                break;
            case "rename":
                GenericPageActions.isElementEnabled(editButton, "Segment Rename button");
                GenericPageActions.actionClick(editButton, "Segment Rename button");
                break;
            default:
                LogManager.printFailLog("incorrect action " + action);
        }

    }

    public WebElement getGivenSegmentWebElement(String selectedSegment) {
        WebElement element = null;
        for (int i = 0; existingSegmentationList.size() > i; i++) {
            if (existingSegmentationList.get(i).findElement(By.xpath("div/div")).getAttribute("title").trim().equals(selectedSegment)) {
                element = existingSegmentationList.get(i);
                break;
            }
            if (!(existingSegmentationList.size() > i + 1)) {
                i = 0;
                if (!Boolean.valueOf(nextPage.getAttribute("aria-disabled"))) {
                    nextPage.click();
                } else {
                    //LogManager.printInfoLog("Selected segmentation not found " + selectedSegment);
                    break;
                }

            }
        }
        return element;
    }

    public void confirmSegmentDelete(String action) {
        for (WebElement button : segmentGenerationPopupButtons
        ) {
            if (button.getText().equals(action)) {
                try {
                    GenericPageActions.click(button, action);
                    if (action.equalsIgnoreCase("yes")) {
                        GenericPageActions.isElementDisplayed(message, "message displayed");
                        verifyMessageAfterAction("success", "Delete Successfully");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    String logMessage = "Segment generate " + action + " failed";
                    LogManager.printExceptionLog(e, logMessage);
                    Assert.fail(e.getMessage());
                }
                break;
            }
        }

    }

    public void verifyMessageAfterAction(String expectedMessageType, String expectedMessage) {

        switch (expectedMessageType) {
            case "error":
                GenericPageActions.isElementDisplayedWithExpectedText(message, "error message", expectedMessage);
            case "success":
                GenericPageActions.isElementDisplayedWithExpectedText(message, "success message", expectedMessage);

        }
    }

    public void selectSegmentFilters(String selectedSegment, String segmentFiltersOption) {

        try {
            if (!getAvailableSegmentFilterFroSelected().contains(selectedSegment)) {
                addFilter(selectedSegment);
            }
            if (!selectedSegment.equals("Age")) {
                GenericPageActions.click(getWebElement(segmentDropDown, selectedSegment), selectedSegment + " dropdown");
                selectFilterOptionsFromDropDown(segmentFiltersOption);
                LogManager.printInfoLog(segmentFiltersOption + "  options selected for " + selectedSegment + " " +
                        "Segmentation");
                GenericPageActions.click(getWebElement(checkbox, "Age"), selectedSegment + " checkbox");
                GenericPageActions.click(getWebElement(checkbox, "Age"), selectedSegment + " checkbox");

            }

        } catch (Exception e) {
            e.printStackTrace();
            String logMessage = "Segment filter " + selectedSegment + " selection failed";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(e.getMessage());
        }

    }

    public void navigateToExistingSegmentation() {
        try {
            GenericPageActions.click(segmentPopUpMessage.get(1), "Existing segmentation link");
            GenericPageActions.isElementDisplayed(duplicateSegmentation, "duplicate segmentation");
        } catch (Exception e) {
            e.printStackTrace();
            String logMessage = "Navigation to existing segmentation failed";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(e.getMessage());
        }
    }

    public void verifyExistingSegmentsWithDB() {
        List<String> actualSegments = getExistingSegments();
        String stmt = "SELECT * FROM customerengagementqa.segments  where status='executed' order by created_on DESC;";
        List<Map<String, String>> data = DBUtils.getDataFromStatement(stmt);
        for (int i = 0; i < actualSegments.size(); i++) {
            Assert.assertEquals(actualSegments.get(i), data.get(i).get("segment_name"), "Existing segment validation failed");
        }
        LogManager.printInfoLog("Existing segment DB validation completed successfully ");
    }

    private List<String> getExistingSegments() {
        boolean condition = true;
        List<String> segments = new ArrayList<>();
        do {
            for (WebElement webElement : existingSegmentationList) {
                segments.add(webElement.findElement(By.xpath("div/div")).getAttribute("title"));
            }
            if (!Boolean.parseBoolean(nextPage.getAttribute("aria-disabled"))) {
                nextPage.click();
            } else {
                condition = false;
            }
        } while (condition);
        return segments;
    }
}
