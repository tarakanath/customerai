package net.electrifai.library.pom;

import net.electrifai.library.utils.*;
import net.electrifai.library.utils.excelsheet.ReadAndWriteExcel;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.Map;

import static net.electrifai.library.utils.LogManager.scenario;

public class LoginPage extends HomePage {
    public static Map<String, String> data = null;
    private static String CURL = null;
    String userlocal = null;
    @FindBy(xpath = "//input[@name='username']")
    WebElement userName_textBox;
    @FindBy(xpath = "//input[@name='password']")
    WebElement password_textBox;
    @FindBy(xpath = "//button[text()='Login']")
    WebElement loginButton;
    @FindBy(css = "div.login_spendName__ZzZap")
    WebElement applicationName;
    @FindBy(xpath = "//h2[@class='login_welcome__8MuGK']")
    WebElement welcomeMessage;

    public LoginPage() {

    }

    public void launchApplication() {
        try {
            String environment = PropertiesFile.getProperty("testEnvironment.properties").getString("environment");
            //ThreadLocalManager.getDriver().get(PropertiesFile.getProperty("testEnvironment.properties").getString("" + environment + ".url"));
            LogManager.printInfoLog("User successfully launched url " + ThreadLocalManager.getDriver().getCurrentUrl());
            GenericPageActions.isElementDisplayed(userName_textBox, "userName_textBox");
            GenericPageActions.isElementDisplayed(password_textBox, "password_textBox");
            GenericPageActions.isElementDisplayed(loginButton, "Login button");
            Assert.assertEquals(applicationName.getText(), "CustomerAi", "Application ICON is missing");
            LogManager.printInfoLog("User successfully landed on Customer AI Login Page");

        } catch (Exception e) {
            e.printStackTrace();
            String logMessage = "application is down";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(e.getMessage());

        }

    }

    public void login(String FileName, String SheetName, String dataRowNum, String user) {
        try {
            String excelFilePath = projectPath + property.getString("testDataPath");
            userlocal = user;
            String fileName = "" + FileName + ".xlsx";
            data = ReadAndWriteExcel.readExcelTabRowNew(excelFilePath, fileName, SheetName, dataRowNum);
            GenericPageActions.enterTextOnElement(userName_textBox, "userName_textBox", data.get("UserName"));
            GenericPageActions.enterTextOnElement(password_textBox, "password_textBox", data.get("Password"));
            GenericPageActions.click(loginButton, "loginButton");
            Assert.assertEquals(pageHeading.getText(),"Customer Overview");
            LogManager.printInfoLog("User landed on "+pageHeading.getText()+" page.");



        } catch (Exception e) {
            e.printStackTrace();
            String logMessage = "could not login";
            LogManager.printExceptionLog(e, logMessage);
            Assert.fail(e.getMessage());
        }
    }

    public void logout() {
        try {
            GenericPageActions.moveToElement(userIcon, "User icon");
            GenericPageActions.isElementDisplayed(logoutButton, "logout button");
            GenericPageActions.click(logoutButton, "logout button");
            GenericPageActions.isElementDisplayed(applicationName, "Application Name");

        } catch (Exception e) {
            e.printStackTrace();
            String logMessage = "could not logout";
            LogManager.printExceptionLog(e, logMessage);

        }
    }

}
