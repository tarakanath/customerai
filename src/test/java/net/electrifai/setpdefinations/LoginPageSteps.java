package net.electrifai.setpdefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.electrifai.library.pom.LoginPage;
import net.electrifai.library.utils.ThreadLocalManager;

public class LoginPageSteps {

    LoginPage loginPage;

    public LoginPageSteps(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    @When("{string} logged into application, from the file {string} where the sheet is {string} and DataRowNum  is {string}")
    public void userLoggedIntoApplicationFromTheFileWhereTheSheetIsAndDataRowNumIs(String userType, String fileName, String sheetName, String rowNum) {
        String log = userType + " logged into application";
        ThreadLocalManager.setStep(ThreadLocalManager.getScenario().createNode("<b>" + log + "</b>"));
        loginPage.login(fileName, sheetName, rowNum, userType);
    }

    @And("logout from the application")
    public void logoutFromTheApplication() {
        loginPage.logout();
    }

    @Given("User landed on Customer AI Application")
    public void customerAIApplicationIsUp() {
        String log = "User landed on Customer AI Application";
        ThreadLocalManager.setStep(ThreadLocalManager.getScenario().createNode("<b>" + log + "</b>"));
        loginPage.launchApplication();

    }

    @Then("{string} logged in successfully.")
    public void loggedInSuccessfully(String arg0) {
        ThreadLocalManager.setStep(ThreadLocalManager.getScenario().createNode("<b>" + "login" + "</b>"));
    }



}
