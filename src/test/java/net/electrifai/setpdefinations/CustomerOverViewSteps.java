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

    CustomerOverviewPage COPage;
    String excelFilePath = projectPath + property.getString("testDataPath");
    public static Map<String, String> data = null;
    public CustomerOverViewSteps(CustomerOverviewPage COPage){
        this.COPage = COPage;
    }

    @Then("validate my products list from the file {string} where the sheet is {string}")
    public void validateMyProductsListFromTheFileWhereTheSheetIs(String string, String string2) {

        //verify available products list.
        throw new io.cucumber.java.PendingException();
    }

    @Then("validate my products search")
    public void validateMyProductsSearch() {

        // validate products search with exact and regular expression.
        //Product list filtered out according to search criteria.
        throw new io.cucumber.java.PendingException();
    }
    @Then("validate navigation to {string} page from my products")
    public void validateNavigationToPageFromMyProducts(String string) {
        // verify navigation to cross-sell page when clicked on  "Reach potential customer"
        // verify respective propensity selected by default according to products selection.
        throw new io.cucumber.java.PendingException();
    }
    @Then("Validate three top category customer tiles from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void validateThreeTopCategoryCustomerTilesFromTheFileWhereTheSheetIs( String fileName, String sheetName, String dataRowNum) {
        COPage.customerTitleHeading(fileName, sheetName, dataRowNum);
    }
    @Then("Validate three bottom category customer tiles from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void validateThreeBottomCategoryCustomerTilesFromTheFileWhereTheSheetIsAndDataRowNumIs( String fileName, String sheetName, String dataRowNum) {
        COPage.getTopTitle();
        COPage.customerBottomHeading(fileName,sheetName,dataRowNum);
    }
    @Then("Validate total count of Active and Inactive customers")
    public void validateTotalCountOfActiveAndInactiveCustomers() {
        COPage.totalCount();
    }
    @Then("Validate Active and Newly Onboarded Customers Percentage from UI")
    public void validateActiveAndNewlyOnboardedCustomersPercentageFromUI() {
            COPage.percentageCount();
        }
    @Then("Validate customer stats from Customer Overview Page are positive")
    public void validateCustomerStatsFromCustomerOverviewPageArePositive() {
        COPage.countCheck();
    }
    }
