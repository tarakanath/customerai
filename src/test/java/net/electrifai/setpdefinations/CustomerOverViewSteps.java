package net.electrifai.setpdefinations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.electrifai.library.pom.CustomerOverviewPage;
import net.electrifai.library.pom.HomePage;
import net.electrifai.library.pom.ReportLandingPage;
import net.electrifai.library.utils.GenericPageActions;
import net.electrifai.library.utils.LogManager;
import net.electrifai.library.utils.ThreadLocalManager;
import net.electrifai.library.utils.excelsheet.ReadAndWriteExcel;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static net.electrifai.library.pom.HomePage.projectPath;
import static net.electrifai.library.pom.HomePage.property;

public class CustomerOverViewSteps {

    CustomerOverviewPage coPage;
    String excelFilePath = projectPath + property.getString("testDataPath");
    public static Map<String, String> data = null;
    ReportLandingPage reportLandingPage;
    HomePage homePage;

    public CustomerOverViewSteps(CustomerOverviewPage coPage, ReportLandingPage reportLandingPage, HomePage homePage ){
        this.coPage = coPage;
        this.reportLandingPage = reportLandingPage;
        this.homePage = homePage;
    }

    @Then("validate my products list from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void validateMyProductsListFromTheFileWhereTheSheetIsandDataRowNumIs(String fileName, String sheetName,String dataRowNum) {
        coPage.getProductName(fileName,sheetName,dataRowNum);
    }
    @Then("validate my product list from Database")
    public void validateMyProductListFromDatabase(){
        coPage.getAndCompareProductName();
    }
    @Then("Enter product name from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void validateMyProductsSearch(String fileName, String sheetName,String dataRowNum ) {
        coPage.searchProduct(fileName, sheetName, dataRowNum);
    }

    @Then("validate potential customers for product from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void validatePotentialCustomersForProduct(String fileName, String sheetName,String dataRowNum ){
        fileName = "" + fileName + ".xlsx";
        data = ReadAndWriteExcel.readExcelTabRowNew(excelFilePath, fileName, sheetName, dataRowNum);
        String productName = data.get("product name");
        coPage.reachCustomerLink(productName);
        reportLandingPage.verifyPropensitySelection(productName);
        homePage.navigateToSelectedPage("Customer Overview");
    }

    @Then("validate data for the product widget")
    public void validateDataForProductWidget(){
        coPage.getDataCountFromProductWidget();
    }

    @Then("validate active customer count from Database for the product widget")
    public void validateActiveCustomerCountFromDatabaseForTheProductWidget(){
        coPage.validateActiveCustomerCountForProduct();
    }

    @Then("validate inactive customer count for the product widget")
    public void validateInactiveCustomerCountForTheProductWidget(){
        coPage.validateInactiveCustomerCount();
    }

    @Then("validate percentage count for the product widget")
    public void validatePercentageCountForTheProductWidget(){
        coPage.validatePercentageCount();

    }
}
