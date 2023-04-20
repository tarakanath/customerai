package net.electrifai.setpdefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.electrifai.library.pom.HomePage;
import net.electrifai.library.pom.SegmentationPage;
import net.electrifai.library.utils.ThreadLocalManager;
import net.electrifai.library.utils.excelsheet.ReadAndWriteExcel;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static net.electrifai.library.pom.HomePage.projectPath;
import static net.electrifai.library.pom.HomePage.property;

public class SegmentPageSteps {
    public static Map<String, String> data = null;
    SegmentationPage segmentationPage;
    HomePage homePage;
    String excelFilePath = projectPath + property.getString("testDataPath");
    public static String  segmentName;

    public SegmentPageSteps(SegmentationPage segmentationPage, HomePage homePage) {
        this.segmentationPage = segmentationPage;
        this.homePage = homePage;
    }

    @And("Select {string} filter from the file {string} where the sheet is {string} and DataRowNum  is {string}")
    public void selectFilterFromTheFileWhereTheSheetIsAndDataRowNumIs(String pageName, String fileName, String sheetName, String dataRowNum) {

        fileName = "" + fileName + ".xlsx";
        data = ReadAndWriteExcel.readExcelTabRowNew(excelFilePath, fileName, sheetName, dataRowNum);
        String log = "Segmentation filter Selection";
        ThreadLocalManager.setStep(ThreadLocalManager.getScenario().createNode("<b>" + log + "</b>"));
        homePage.navigateToSelectedPage(pageName);
        List<String> segmentList = Arrays.asList(data.get("SelectedSegements").split(","));
        for (String segement : segmentList
        ) {
            segmentationPage.selectSegmentFilters(segement, data.get(segement));

        }

    }

    @And("create segment from the file {string} where the sheet is {string} and DataRowNum  is {string}")
    public void verifySegmentCreationMessageFromTheFileWhereTheSheetIsAndDataRowNumIs(){
        String log = "Segmentation creation";
        ThreadLocalManager.setStep(ThreadLocalManager.getScenario().createNode("<b>" + log + "</b>"));
        segmentName = segmentationPage.generateSegment(data);
    }

    @Then("validate created segment appeared in segment list")
    public void validateCreatedSegmentAppearedInSegmentList() {
        String log = "Verify given segment from current segment list";
        ThreadLocalManager.setStep(ThreadLocalManager.getScenario().createNode("<b>" + log + "</b>"));
        segmentationPage.verifyGivenSegmentAvailable(segmentName);
    }




    @And("Generate {string} filter get test data from the file {string} where the sheet is {string} and DataRowNum  is {string}")
    public void generateFilterFromTheFileWhereTheSheetIsAndDataRowNumIs(String pageName, String fileName, String sheetName, String dataRowNum) {

        String log = "Segmentation filter Selection";
        fileName = "" + fileName + ".xlsx";
        data = ReadAndWriteExcel.readExcelTabRowNew(excelFilePath, fileName, sheetName, dataRowNum);
        ThreadLocalManager.setStep(ThreadLocalManager.getScenario().createNode("<b>" + log + "</b>"));
        homePage.navigateToSelectedPage(pageName);
        List<String> segmentList = Arrays.asList(data.get("SelectedSegements").split(","));
        for (String segment : segmentList
        ) {
            segmentationPage.selectSegmentFilters(segment, data.get(segment));

        }
        log = "Segmentation Generation";
        ThreadLocalManager.setStep(ThreadLocalManager.getScenario().createNode("<b>" + log + "</b>"));
        segmentName = segmentationPage.generateSegment(data);
    }

    @Then("validate click on existing segmentation link from error message to navigate to existing segmentation")
    public void validateClickOnExistingSegmentationLinkFromErrorMessage() {
        String log = "validate click on existing segmentation link from error message to navigate to existing " +
                "segmentation";
        ThreadLocalManager.setStep(ThreadLocalManager.getScenario().createNode("<b>" + log + "</b>"));
        segmentationPage.navigateToExistingSegmentation();

    }

    @Then("Verify segment deleted")
    public void validateSegmentDeleted() {
        segmentationPage.verifyGivenSegmentNotFound(segmentName);
    }

    @When("{string} segmentation from the data file {string} where the sheet is {string} and DataRowNum is {string}")
    public void segmentationFromTheFileWhereTheSheetIsAndDataRowNumIs(String action, String fileName, String sheetName, String dataRowNum) {
        String log = "Segmentation filter Selection";
        fileName = "" + fileName + ".xlsx";
        data = ReadAndWriteExcel.readExcelTabRowNew(excelFilePath, fileName, sheetName, dataRowNum);
        if(!data.get("segmentName").equals(""))
            data.put("segmentName",segmentName);
        segmentationPage.doGivenActionOnGivenSegment(action, data);

    }
}
