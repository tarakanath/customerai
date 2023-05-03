package net.electrifai.setpdefinations;

import io.cucumber.java.en.Then;
import net.electrifai.library.pom.CustomerOverviewPage;
import net.electrifai.library.utils.LogManager;
import net.electrifai.library.utils.excelsheet.ReadAndWriteExcel;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static net.electrifai.library.pom.HomePage.projectPath;
import static net.electrifai.library.pom.HomePage.property;

public class CustomerOverViewSteps {

    CustomerOverviewPage coPage;
    String excelFilePath = projectPath + property.getString("testDataPath");
    public static Map<String, String> data = null;
    public CustomerOverViewSteps(CustomerOverviewPage COPage){
        this.coPage = COPage;
    }

    @Then("Validate three top category customer tiles from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void validateThreeTopCategoryCustomerTilesFromTheFileWhereTheSheetIs( String fileName, String sheetName, String dataRowNum) {
        coPage.customerTitleHeading(fileName, sheetName, dataRowNum);
    }
    @Then("Validate three bottom category customer tiles from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void validateThreeBottomCategoryCustomerTilesFromTheFileWhereTheSheetIsAndDataRowNumIs( String fileName, String sheetName, String dataRowNum) {
        coPage.getTopTitle();
        coPage.customerBottomHeading(fileName,sheetName,dataRowNum);
    }
    @Then("Validate total count of Active and Inactive customers")
    public void validateTotalCountOfActiveAndInactiveCustomers() {
        coPage.totalCount();
    }
    @Then("Validate Active and Newly Onboarded Customers Percentage from UI")
    public void validateActiveAndNewlyOnboardedCustomersPercentageFromUI() {
            coPage.percentageCount();
        }
    @Then("Validate customer stats from Customer Overview Page are positive and match the count from DB")
    public void validateCustomerStatsFromCustomerOverviewPageArePositiveAndMatchTheCountFromDB()  {
        coPage.countCheck();
    }
}
