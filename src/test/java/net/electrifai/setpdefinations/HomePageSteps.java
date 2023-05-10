package net.electrifai.setpdefinations;

import io.cucumber.java.en.And;
import net.electrifai.library.pom.HomePage;
import net.electrifai.library.utils.ThreadLocalManager;

public class HomePageSteps {

    HomePage homePage;
    String log;

    public HomePageSteps(HomePage homePage) {
        this.homePage = homePage;
    }

    @And("Navigate to {string} page")
    public void navigateToPage(String pageName) {
        log = "Navigate to " + pageName;
        ThreadLocalManager.setStep(ThreadLocalManager.getScenario().createNode("<b>" + log + "</b>"));
        homePage.navigateToSelectedPage(pageName);
    }
}
