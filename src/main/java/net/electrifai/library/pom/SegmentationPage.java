package net.electrifai.library.pom;

import net.electrifai.library.utils.GenericPageActions;
import net.electrifai.library.utils.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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
    @FindBy(css = "div.segmentation_edit__y72ss.edit div:first-child")
    List<WebElement> segmentationRenameButton;
    @FindBy(css = "div.segmentation_edit__y72ss.edit div:last-child")
    List<WebElement> segmentationDeleteButton;
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
        if(action.equalsIgnoreCase("close")){
            GenericPageActions.click(segmentGenerationPopupButtons.get(0),"X button");
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

    public void doGivenActionOnGivenSegmentd(String action, String givenSegmentName) {
        navigateToSelectedPage("Segmentation");
        boolean status = false;
        try {
            outerloop:
            do {
                for (int i = 0; existingSegmentationNameList.size() > i; i++) {
                    if (existingSegmentationNameList.get(i).getAttribute("title").trim().equals(givenSegmentName)) {
                        GenericPageActions.moveToElement(existingSegmentationNameList.get(i), "Given segmentation");
                        break outerloop;
                    }
                }

                if (nextPage.isEnabled()) {
                    GenericPageActions.click(nextPage, "next_page");
                    status = true;
                } else
                    status = false;

            } while (!status);

            if (!status) {
                LogManager.printFailLog("Given segmentation not found for " + action);
            }

            LogManager.printInfoLog("Given segment name not available in list to delete.");
        } catch (Exception e) {
            e.printStackTrace();
            String logMessage = "Given segment name search failed";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(e.getMessage());
        }
    }

    public void doGivenActionOnGivenSegment(String action, Map<String, String> data) {
        navigateToSelectedPage("Segmentation");

        for (int i = 0; existingSegmentationNameList.size() > i; i++) {
            if (existingSegmentationNameList.get(i).getAttribute("title").trim().equals(data.get("segmentName"))) {

                switch (action) {
                    case "delete":
                        GenericPageActions.moveToElement(existingSegmentationNameList.get(i), data.get("segmentName"));
                        GenericPageActions.isElementEnabled(segmentationDeleteButton.get(i), "Segment Delete button");
                        GenericPageActions.actionClick(segmentationDeleteButton.get(i), "Segment Delete button");
                        popupMessageAction("Yes");
                        //confirmSegmentDelete("Yes");
                        break;
                    case "rename":
                        GenericPageActions.isElementEnabled(segmentationRenameButton.get(i), "Segment Rename button");
                        GenericPageActions.actionClick(segmentationRenameButton.get(i), "Segment Rename button");
                        break;
                    default:
                        LogManager.printFailLog("incorrect action " + action);
                }
                break;
            }

            if (!(existingSegmentationNameList.size() > i + 1)) {
                i = 0;
                if (!Boolean.valueOf(nextPage.getAttribute("aria-disabled"))) {
                    nextPage.click();
                } else {
                    LogManager.printFailLog("Selected segmentation not found " + data.get("segmentName"));
                    Assert.fail("Selected segmentation not found " + data.get("segmentName"));
                    break;
                }

            }
        }

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
}
