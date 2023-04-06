package net.electrifai.library.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wait
{

    private static WebDriverWait wait;

    public static void  waitUntilElementToBeClickable(WebElement element)
    {
        try {

            FluentWait wait = new FluentWait(ThreadLocalManager.getDriver())
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofMillis(600))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(AssertionError.class)
                .ignoring(ScriptTimeoutException.class)
                .ignoring(ElementNotVisibleException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(ElementNotSelectableException.class);

            wait.until(ExpectedConditions.elementToBeClickable(element));
        }catch (Exception e){

            e.printStackTrace();
        }
    }


    public static void  waitUntilElementLocated(By by)
    {
        try {

            FluentWait wait = new FluentWait(ThreadLocalManager.getDriver())
                .withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofMillis(600))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(AssertionError.class)
                .ignoring(ScriptTimeoutException.class)
                .ignoring(ElementNotVisibleException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(ElementNotSelectableException.class);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        }catch (Exception e){e.printStackTrace();}
    }



    public static void  waitUntilUrlToBe(String url)
    {
        try {
            wait = new WebDriverWait(ThreadLocalManager.getDriver(), 2);
            wait.until(ExpectedConditions.urlContains(url));
        }catch (Exception e){e.printStackTrace();}
    }

    /* Function to wait for the page to load. otherwise it will fail the test*/
    public static void waitForPageToLoad()
    {
        try {
            ExpectedCondition<Boolean> javascriptDone = new ExpectedCondition<Boolean>()
            {
                public Boolean apply(WebDriver d) {
                    try {
                        return ((JavascriptExecutor) ThreadLocalManager.getDriver()).executeScript("return document.readyState").equals("complete");
                    }
                    catch (Exception e) {
                        return Boolean.FALSE;
                    }
                }
            };
            WebDriverWait wait = new WebDriverWait(ThreadLocalManager.getDriver(), 5);
            wait.until(javascriptDone);
        }catch (Exception e){e.printStackTrace();}
    }


    public static void  waitUntilElementToBeDisappear(By element)
    {
        try {

            FluentWait wait = new FluentWait(ThreadLocalManager.getDriver())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(600))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(AssertionError.class)
                .ignoring(ScriptTimeoutException.class)
                .ignoring(ElementNotVisibleException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(ElementNotSelectableException.class);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
        }catch (Exception e){e.printStackTrace();}
    }

    public static void  waitUntilElementToBeDisappearForVideoContentType(By element)
    {
        try {

            FluentWait wait = new FluentWait(ThreadLocalManager.getDriver())
                    .withTimeout(Duration.ofSeconds(180))
                    .pollingEvery(Duration.ofMillis(600))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(TimeoutException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(AssertionError.class)
                    .ignoring(ScriptTimeoutException.class)
                    .ignoring(ElementNotVisibleException.class)
                    .ignoring(ElementClickInterceptedException.class)
                    .ignoring(ElementNotInteractableException.class)
                    .ignoring(ElementNotSelectableException.class);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
        }catch (Exception e){e.printStackTrace();}
    }
    public static void  waitUntiltextToBePresentInElement(WebElement element,String text1)
    {
        try {

            FluentWait wait = new FluentWait(ThreadLocalManager.getDriver())
                    .withTimeout(Duration.ofSeconds(300))
                    .pollingEvery(Duration.ofMillis(600))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(TimeoutException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(AssertionError.class)
                    .ignoring(ScriptTimeoutException.class)
                    .ignoring(ElementNotVisibleException.class)
                    .ignoring(ElementClickInterceptedException.class)
                    .ignoring(ElementNotInteractableException.class)
                    .ignoring(ElementNotSelectableException.class);
            wait.until(ExpectedConditions.textToBePresentInElement(element,text1));
        }catch (Exception e){e.printStackTrace();}
    }
    public static void explicitWait(WebElement webElement, String waitCondition) {
        wait=new WebDriverWait(ThreadLocalManager.getDriver(),60);
        switch(waitCondition.toLowerCase()) {
            case "clickable":
                wait.until(ExpectedConditions.elementToBeClickable(webElement));
                break;
            case "visibility":
                wait.until(ExpectedConditions.visibilityOf(webElement));
                break;
            case "invisibility":
                wait.until(ExpectedConditions.invisibilityOf(webElement));
                break;
            case "alert":
                wait.until(ExpectedConditions.alertIsPresent());
                break;
            default:
                LogManager.printInfoLog("Inavlid Explicit Wait Selection");
        }
    }

    public static void explicitWaitTextVerification(WebElement webElement, String text) {
        wait=new WebDriverWait(ThreadLocalManager.getDriver(),60);
        wait.until(ExpectedConditions.textToBePresentInElement(webElement,text));

    }
    public static void explicitWaitElementAttributeContains(WebElement webElement, String attribute, String value) {
    wait=new WebDriverWait(ThreadLocalManager.getDriver(),60);
    wait.until(ExpectedConditions.attributeContains(webElement,attribute,value));
}





}
