package net.electrifai.library.utils;

import net.electrifai.library.constants.EnumConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.microsoft.edge.seleniumtools.EdgeDriver;
import com.microsoft.edge.seleniumtools.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DriverSetUp
{
    public static WebDriver driver;
    public static String broswerversion;
    public static String broswerName;
    public  static Capabilities browserCap = null;


    public static void initializeDriver(String browser) throws IOException, ConfigurationException {
        switch (EnumConstants.BrowserType.valueOf(browser))
        {

            case firefox:

                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                String resources= File.separator +"src" + File.separator + "test"+ File.separator + "resources" ;
                String expectedFolder=resources+File.separator + "downloads";
                String downloadsFolder=System.getProperty("user.dir") +expectedFolder;
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                firefoxProfile.setPreference("browser.private.browsing.autostart", true);
                firefoxProfile.setPreference("browser.download.folderList", 2);
                firefoxProfile.setPreference("browser.download.dir",downloadsFolder);
                options.addArguments("--no-sandbox");
                if (Boolean.valueOf(PropertiesFile.getProperty("testEnvironment.properties").getString( "RunHeadLess")))
                {
                    options.addArguments("--headless");
                }
                if (Boolean.valueOf(PropertiesFile.getProperty("testEnvironment.properties").getString( "incognitoMode")))
                {
                    options.addArguments("-private");
                }
                //  options.addArguments("--window-size=1980,1080");
                options.setProfile(firefoxProfile);
                driver = new FirefoxDriver(options);
                ThreadLocalManager.setBrowserName(EnumConstants.BrowserType.firefox);
                break;

            case chrome:

                WebDriverManager.chromedriver().setup();
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                String resourcesPath= File.separator +"src" + File.separator + "test"+ File.separator + "resources" ;
                String expectedFolderPath=resourcesPath+File.separator + "downloads";
                String downloadsFolderPath=System.getProperty("user.dir") +expectedFolderPath;
                chromePrefs.put("download.default_directory",downloadsFolderPath);
                chromePrefs.put("profile.default_content_settings.popups", 0);
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.setExperimentalOption("prefs",chromePrefs);
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                if (Boolean.valueOf(PropertiesFile.getProperty("testEnvironment.properties").getString( "RunHeadLess")))
                {
                    chromeOptions.addArguments("--headless");
                }
                chromeOptions.addArguments("disable-gpu");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.setExperimentalOption("useAutomationExtension", false);
                if (Boolean.valueOf(PropertiesFile.getProperty("testEnvironment.properties").getString( "incognitoMode")))
                {
                    chromeOptions.addArguments("--incognito");
                }
                chromeOptions.addArguments("--window-size=1980,1080");
                driver = new ChromeDriver(chromeOptions);
                ThreadLocalManager.setBrowserName(EnumConstants.BrowserType.chrome);
                break;

            case safari:

                driver = new SafariDriver();
                ThreadLocalManager.setBrowserName(EnumConstants.BrowserType.safari);
                break;

            case ie:

                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
                ieOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                //To speedup browser execution
                ieOptions.disableNativeEvents();
                ieOptions.requireWindowFocus();
                ieOptions.introduceFlakinessByIgnoringSecurityDomains();
                ieOptions.ignoreZoomSettings();
                ieOptions.destructivelyEnsureCleanSession();
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver(ieOptions);
                ThreadLocalManager.setBrowserName(EnumConstants.BrowserType.ie);
                break;

            case edge:

                WebDriverManager.edgedriver().setup();
                HashMap<String, Object> edgePrefs= new HashMap<String, Object>();
                String resourcesPathEdge= File.separator +"src" + File.separator + "test"+ File.separator + "resources" ;
                String expectedFolderPathEdge=resourcesPathEdge+File.separator + "downloads";
                String downloadsFolderPathEdge=System.getProperty("user.dir") +expectedFolderPathEdge;
                edgePrefs.put("download.default_directory", downloadsFolderPathEdge);
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setExperimentalOption("prefs",edgePrefs);
                if (Boolean.valueOf(PropertiesFile.getProperty("testEnvironment.properties").getString( "incognitoMode")))
                {
                    edgeOptions.addArguments("inprivate");
                }
                driver = new EdgeDriver(edgeOptions);
                ThreadLocalManager.setBrowserName(EnumConstants.BrowserType.edge);
                break;

            default:
                System.out.println("Browser is not specified ");
        }

        ThreadLocalManager.setDriver(driver);
        ThreadLocalManager.setOSName(System.getProperty("os.name").trim());

        //Set browser version
        browserCap = ((RemoteWebDriver) ThreadLocalManager.getDriver()).getCapabilities();
        broswerversion = browserCap.getVersion();
        ThreadLocalManager.setBrowserVersion(broswerversion.trim());

        ThreadLocalManager.getExtent().setSystemInfo("Browser Name", ThreadLocalManager.getBrowserName().toString());
        ThreadLocalManager.getExtent().setSystemInfo("Browser Version", ThreadLocalManager.getBrowserVersion().toString());

    }

    public static void closeDriver()
    {
        ThreadLocalManager.getDriver().manage().deleteAllCookies();
        ThreadLocalManager.getDriver().close();
    }

    public static void launchUrl() throws ConfigurationException, IOException
    {
        try {
            ThreadLocalManager.getDriver().manage().window().maximize();
            String environment=PropertiesFile.getProperty("testEnvironment.properties").getString( "environment");
            String testUrl=PropertiesFile.getProperty("testEnvironment.properties").getString( ""+environment+".url");
            System.out.println(testUrl);
            ThreadLocalManager.getDriver().get(testUrl);
            ThreadLocalManager.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            ThreadLocalManager.getDriver().manage().timeouts().pageLoadTimeout(21, TimeUnit.SECONDS);
            ThreadLocalManager.getDriver().manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

            Wait.waitUntilUrlToBe(testUrl);

        }catch (Exception e){e.printStackTrace();}

    }

}
