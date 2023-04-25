package net.electrifai.setpdefinations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.electrifai.library.pom.CustomerOverviewPage;
import net.electrifai.library.utils.GenericPageActions;
import net.electrifai.library.utils.LogManager;
import net.electrifai.library.utils.ThreadLocalManager;
import net.electrifai.library.utils.excelsheet.ReadAndWriteExcel;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
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

    @Then("validate my products list from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void validateMyProductsListFromTheFileWhereTheSheetIsandDataRowNumIs(String fileName, String sheetName,String dataRowNum) {
           COPage.getProductName(fileName,sheetName,dataRowNum);
    }

    @Then("Enter product name from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void validateMyProductsSearch(String fileName, String sheetName,String dataRowNum ) {
            COPage.searchProduct(fileName, sheetName, dataRowNum);
    }

    @Then("validate potential customers for product from the file {string} where the sheet is {string} and DataRowNum is {string}")
    public void validatePotentialCustomersForProduct(String fileName, String sheetName,String dataRowNum ) {
        COPage.reachCustomerLink(fileName,sheetName,dataRowNum);
    }

    @Then("validate data for the product widget")
    public void validateDataForProductWidget(){
        COPage.getDataCountFromProductWidget();
    }


    @Then("validate navigation to {string} page from my products")
    public void validateNavigationToPageFromMyProducts(String string) {
        // verify navigation to cross-sell page when clicked on  "Reach potential customer"
        // verify respective propensity selected by default according to products selection.
        throw new io.cucumber.java.PendingException();
    }

    @Then("Validate three category customer tiles from the file {string} where the sheet is {string}")
    public void validateThreeCategoryCustomerTilesFromTheFileWhereTheSheetIs(String string, String string2) {
        // validate Total, active, newly boarding customers tiles.
        // validate each tile title
        throw new io.cucumber.java.PendingException();
    }

    @Then("validate three category customers data.")
    public void validateThreeCategoryCustomersData() {
        // validate data from all three customer tiles.
        throw new io.cucumber.java.PendingException();
    }

}
