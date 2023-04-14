package net.electrifai.library.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CustomerOverviewPage extends HomePage{

    @FindBy(xpath = "//div[@class='customerOverview_title__o_i4H']/span")
    WebElement myProductsHeadings;


}
