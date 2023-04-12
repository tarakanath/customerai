package net.electrifai.setpdefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.electrifai.library.pom.HomePage;
import net.electrifai.library.pom.ReportLandingPage;
import net.electrifai.library.pom.ReportPage;
import net.electrifai.library.utils.LogManager;
import net.electrifai.library.utils.ThreadLocalManager;
import net.electrifai.library.utils.excelsheet.ReadAndWriteExcel;

import java.util.*;

import static net.electrifai.library.pom.HomePage.*;

public class CrossSellPageSteps {
    public static Map<String, String> data = null;
    ReportPage reportPage;
    ReportLandingPage reportLandingPage;
    HomePage homePage;
    String excelFilePath = projectPath + property.getString("testDataPath");
    String log;
    String pageName;

    public CrossSellPageSteps(ReportPage reportPage, ReportLandingPage reportLandingPage, HomePage homePage) {
        this.reportPage = reportPage;
        this.reportLandingPage = reportLandingPage;
        this.homePage = homePage;
    }

    @And("Select {string} from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void selectFromTheFileWhereTheSheetIsAndDataRowNumIs(String pageName, String fileName, String sheetName,
                                                                String dataRowNum) {
        log = "Generate " + pageName + " report for selected segmentation";
        ThreadLocalManager.setStep(ThreadLocalManager.getScenario().createNode("<b>" + log + "</b>"));
        homePage.navigateToSelectedPage(pageName);
        this.pageName = pageName;
        fileName = "" + fileName + ".xlsx";
        data = ReadAndWriteExcel.readExcelTabRowNew(excelFilePath, fileName, sheetName, dataRowNum);

        if (data.get("Segmentation").trim().equals("")) {
            reportLandingPage.selectOptionFromGivenDropDown("Segmentation", segmentName);
        } else {
            reportLandingPage.selectOptionFromGivenDropDown("Segmentation", data.get("Segmentation"));
        }
        reportLandingPage.selectOptionFromGivenDropDown("Propensity Model", data.get("Propensity"));
        reportLandingPage.selectOptionFromGivenDropDown("Date Range", data.get("Date Range"));
        reportLandingPage.clickOnApplyButton();

    }

    @And("Verify {string} from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void verifyFromTheFileWhereTheSheetIsAndDataRowNumIs(String pageName, String fileName, String sheetName, String dataRowNum) {
        log = "Verify " + pageName + " report for selected segmentation";
        ThreadLocalManager.setStep(ThreadLocalManager.getScenario().createNode("<b>" + log + "</b>"));
        fileName = "" + fileName + ".xlsx";
        data = ReadAndWriteExcel.readExcelTabRowNew(excelFilePath, fileName, sheetName, dataRowNum);
        reportPage.verifyReportSelection(pageName, data);
    }

    @And("update {string} from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void updateFromTheFileWhereTheSheetIsAndDataRowNumIs(String pageName, String fileName, String sheetName, String dataRowNum) {
        log = "update " + pageName + " report for selected segmentation";
        ThreadLocalManager.setStep(ThreadLocalManager.getScenario().createNode("<b>" + log + "</b>"));
        fileName = "" + fileName + ".xlsx";
        data = ReadAndWriteExcel.readExcelTabRowNew(excelFilePath, fileName, sheetName, dataRowNum);
        reportPage.updateReportSelection(pageName, data);
    }

    @Then("verify customer probability selection")
    public void verifyCustomerProbabilitySelection() {
        List<String> temp = Arrays.asList("Extreme Likely", "High Likely");
        List<String> range = Arrays.asList("100%", "90%");
        // reportPage.selectCustomerProbability(temp);
        reportPage.verifyCustomerProbabilitySelection(pageName, range);

    }

    @And("update report selection from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void updateReportSectionFromTheFileWhereTheSheetIsAndDataRowNumIs(String fileName, String sheetName, String dataRowNum) {
        log = "update " + pageName + " report for selected segmentation";
        ThreadLocalManager.setStep(ThreadLocalManager.getScenario().createNode("<b>" + log + "</b>"));
        fileName = "" + fileName + ".xlsx";
        data = ReadAndWriteExcel.readExcelTabRowNew(excelFilePath, fileName, sheetName, dataRowNum);
        reportPage.updateReportSelection(pageName, data);
    }

    @Then("Verify default selection from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void verifyDefaultSelectionFromTheFileWhereTheSheetIsAndDataRowNumIs(String fileName, String sheetName, String dataRowNum) {
        log = "Verify default" + pageName + " report for selected segmentation";
        ThreadLocalManager.setStep(ThreadLocalManager.getScenario().createNode("<b>" + log + "</b>"));
        fileName = "" + fileName + ".xlsx";
        data = ReadAndWriteExcel.readExcelTabRowNew(excelFilePath, fileName, sheetName, dataRowNum);
        reportPage.verifyCustomerProbabilitySelection(pageName, data);
        ArrayList<String> expectedDrivers = new ArrayList<>(Arrays.asList(data.get("Selected Drivers").split(",")));
        reportPage.verifyDriverSelection(expectedDrivers);
    }

    @Then("Verify selection from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void verifySelectionFromTheFileWhereTheSheetIsAndDataRowNumIs(String fileName, String sheetName, String dataRowNum) {
        log = "Verify " + pageName + " report for selected segmentation";
        ThreadLocalManager.setStep(ThreadLocalManager.getScenario().createNode("<b>" + log + "</b>"));
        fileName = "" + fileName + ".xlsx";
        data = ReadAndWriteExcel.readExcelTabRowNew(excelFilePath, fileName, sheetName, dataRowNum);
        reportPage.verifyCustomerProbabilitySelection(pageName, data);
    }

    @Then("verify driver selection")
    public void verifyDriverSelection() {
        Random ran = new Random();
        int x = ran.nextInt(5);

    }

    @Then("update and verify customer probability selection from file {string} Where the sheet is {string}")
    public void updateAndVerifyCustomerProbabilitySelectionFromFileWhereTheSheetIs(String fileName, String sheetName) {
        log = "update and verify customer probability selection for " + pageName;
        int dataRowNum = 1;
        ThreadLocalManager.setStep(ThreadLocalManager.getScenario().createNode("<b>" + log + "</b>"));
        fileName = "" + fileName + ".xlsx";
        for (dataRowNum = 2; dataRowNum < 7; dataRowNum++) {
            data = ReadAndWriteExcel.readExcelTabRowNew(excelFilePath, fileName, sheetName, Integer.toString(dataRowNum));
            reportPage.selectCustomerProbability(data.get("Customer Probability"));
            reportPage.verifyCustomerProbabilitySelection(pageName, data);
            LogManager.printInfoLog(data.get("Customer Probability") + " selection validated");
        }
    }

    @Then("update and verify driver selection in customer profile table")
    public void updateAndVerifyDriverSelection() {
        List<String> driversList = new ArrayList<>(Arrays.asList("Driver 1", "Driver 2", "Driver 3", "Driver 4", "Driver 5"));
        Random ran = new Random();
        int x = ran.nextInt(5);
        driversList.remove(x);
        // test to select four drivers out of five.
        reportPage.selectDrivers(driversList);
        reportPage.verifyDriverSelection(driversList);
        // test to select three drivers out of five.
        x = ran.nextInt(4);
        driversList.remove(x);
        reportPage.selectDrivers(driversList);
        reportPage.verifyDriverSelection(driversList);

    }

    @Then("verify customer profile pagination with {string}")
    public void verifyCustomerProfilePaginationWith(String state) {
        if(!state.equals("minimize"))
            reportPage.doGivenActionOnProfile(state);
        reportPage.verifyCustomerProfilePaginationWith(state);
        reportPage.verifyNavigationToLastPage(state);
        reportPage.VerifyNavigationToFirstPage(state);
        reportPage.verifyPageNavigationByOneStep(state);
        if(state.equals("expand")) // to minimize the profile for next step validation.
            reportPage.doGivenActionOnProfile("minimize");

    }

    @Then("update {string} filter criteria from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void updateFilterCriteriaFromTheFileWhereTheSheetIsAndDataRowNumIs(String pageName, String fileName, String sheetName,
                                                                              String dataRowNum) {
        log = "Update segmentation,Propensity,Date Range " + pageName;
        ThreadLocalManager.setStep(ThreadLocalManager.getScenario().createNode("<b>" + log + "</b>"));
        this.pageName = pageName;
        fileName = "" + fileName + ".xlsx";
        data = ReadAndWriteExcel.readExcelTabRowNew(excelFilePath, fileName, sheetName, dataRowNum);
        String pageType = "report";
        if (data.get("Segmentation").equals("")) {
            data.put("Segmentation", segmentName);
        }
        reportPage.selectOptionFromGivenDropDown("Segmentation", data.get("Segmentation"));
        reportPage.selectOptionFromGivenDropDown("Propensity Model", data.get("Propensity"));
        reportPage.selectOptionFromGivenDropDown("Date Range", data.get("Date Range"));
        LogManager.printInfoLog("Segmentation,Propensity,date range updated");

    }

    @Then("Verify {string} filter criteria selection from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void verifyFilterCriteriaSelectionFromTheFileWhereTheSheetIsAndDataRowNumIs(String pageName, String fileName, String sheetName,
                                                                                       String dataRowNum) {
        log = "Verify segmentation,Propensity,Date Range selection" + pageName;
        ThreadLocalManager.setStep(ThreadLocalManager.getScenario().createNode("<b>" + log + "</b>"));
        this.pageName = pageName;
        fileName = "" + fileName + ".xlsx";
        data = ReadAndWriteExcel.readExcelTabRowNew(excelFilePath, fileName, sheetName, dataRowNum);
        if (data.get("Segmentation").equals("")) {
            data.put("Segmentation", segmentName);
        }
        reportPage.verifySelectedFilterCtriteria(data);
        reportPage.verifySelectedFilterCtriteriaInProfileTable(data);
    }

    @Then("verify customer profile info page validate with profile window {string}")
    public void verifyCustomerProfileInfoPageValidateWithProfileWindow(String state) {
        if(!state.equals("minimize"))
            reportPage.doGivenActionOnProfile(state);
        reportPage.validateCustomerProfileToViewWhenProfileWindow(state);
    }

    @Then("verify drivers filter in customer profile table.")
    public void verifyDriversFilterInCustomerProfileTable() {
        List<String> driversList = new ArrayList<>(Arrays.asList("Driver 1", "Driver 2", "Driver 3", "Driver 4", "Driver 5"));
        Random ran = new Random();
        List<String> selectedDriver=new ArrayList<>();
        selectedDriver.add(driversList.get(ran.nextInt(5)));
        reportPage.doGivenActionOnProfile("expand");
        reportPage.setupFilterForSelectedDrivers(selectedDriver);

    }
}