package net.electrifai.setpdefinations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.electrifai.library.pom.CustomerOverviewPage;

public class CustomerOverViewSteps {

    CustomerOverviewPage COPage;

    @When("Validate landed on {string} page.")
    public void validateLandedOnPage(String string) {

       COPage.validatePageTitle(string);
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
