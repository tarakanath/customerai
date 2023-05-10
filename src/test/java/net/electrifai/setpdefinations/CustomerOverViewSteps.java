package net.electrifai.setpdefinations;

import io.cucumber.java.en.Then;
import net.electrifai.library.pom.CustomerOverviewPage;
import net.electrifai.library.pom.HomePage;
import net.electrifai.library.pom.ReportLandingPage;
import net.electrifai.library.utils.excelsheet.ReadAndWriteExcel;

import java.util.Map;

import static net.electrifai.library.pom.HomePage.projectPath;
import static net.electrifai.library.pom.HomePage.property;

public class CustomerOverViewSteps {

    public static Map<String, String> data = null;
    CustomerOverviewPage coPage;
    String excelFilePath = projectPath + property.getString("testDataPath");
    ReportLandingPage reportLandingPage;
    HomePage homePage;

    public CustomerOverViewSteps(CustomerOverviewPage coPage, ReportLandingPage reportLandingPage, HomePage homePage) {
        this.coPage = coPage;
        this.reportLandingPage = reportLandingPage;
        this.homePage = homePage;
    }


    @Then("validate my products list from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void validateMyProductsListFromTheFileWhereTheSheetIsandDataRowNumIs(String fileName, String sheetName, String dataRowNum) {
        coPage.getProductName(fileName, sheetName, dataRowNum);
    }

    @Then("validate my product list from Database")
    public void validateMyProductListFromDatabase() {
        coPage.getAndCompareProductName();
    }

    @Then("Enter product name from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void validateMyProductsSearch(String fileName, String sheetName, String dataRowNum) {
        coPage.searchProduct(fileName, sheetName, dataRowNum);
    }

    @Then("validate potential customers for product from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void validatePotentialCustomersForProduct(String fileName, String sheetName, String dataRowNum) {
        fileName = "" + fileName + ".xlsx";
        data = ReadAndWriteExcel.readExcelTabRowNew(excelFilePath, fileName, sheetName, dataRowNum);
        String productName = data.get("product name");
        coPage.reachCustomerLink(productName);
        reportLandingPage.verifyPropensitySelection(productName);
        homePage.navigateToSelectedPage("Customer Overview");
    }

    @Then("Validate three top category customer tiles from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void validateThreeTopCategoryCustomerTilesFromTheFileWhereTheSheetIs(String fileName, String sheetName, String dataRowNum) {
        coPage.customerTitleHeading(fileName, sheetName, dataRowNum);
    }

    @Then("validate data for the product widget")
    public void validateDataForProductWidget() {
        coPage.getDataCountFromProductWidget();
    }

    @Then("Validate three bottom category customer tiles from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void validateThreeBottomCategoryCustomerTilesFromTheFileWhereTheSheetIsAndDataRowNumIs(String fileName, String sheetName, String dataRowNum) {
        coPage.getTopTitle();
        coPage.customerBottomHeading(fileName, sheetName, dataRowNum);
    }

    @Then("validate active customer count from Database for the product widget")
    public void validateActiveCustomerCountFromDatabaseForTheProductWidget() {
        coPage.validateActiveCustomerCountForProduct();
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
    public void validateCustomerStatsFromCustomerOverviewPageArePositiveAndMatchTheCountFromDB() {
        coPage.countCheck();
    }

    @Then("validate new customer count for the product widget")
    public void validateNewCustomerCountForTheProductWidget() {
        coPage.validateNewCustomerCount();
    }

    @Then("validate new customer percentage count for the product widget")
    public void validatePercentageCountForTheProductWidget() {
        coPage.validateNewCustPercentage();

    }
}
