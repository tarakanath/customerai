package net.electrifai.library.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.ArrayList;

public class ThreadLocalManager
{
    private static final java.lang.ThreadLocal<WebDriver> webDriver = new java.lang.ThreadLocal<>();

    public static void setDriver(WebDriver driver)
    {
        webDriver.set(driver);
    }

    public static  WebDriver getDriver()
    {
        return webDriver.get();
    }


    private static final java.lang.ThreadLocal<Enum> browserName = new java.lang.ThreadLocal<Enum>();

    public static Enum getBrowserName()
    {

        return browserName.get();
    }

    public static void setBrowserName(Enum browser)
    {

        browserName.set(browser);
    }

    private static final java.lang.ThreadLocal<String> reportPath = new java.lang.ThreadLocal<>();

    public static String getReportPath()
    {

        return reportPath.get();
    }

    public static void setReportPath( String  exntrpt)
    {

        reportPath.set(exntrpt);
    }

    private static final java.lang.ThreadLocal<String> localDirectoryPath = new java.lang.ThreadLocal<>();

    public static String getLocalDirectoryPath()
    {

        return localDirectoryPath.get();
    }

    public static void setLocalDirectoryPath( String  exntrpt)
    {

        localDirectoryPath.set(exntrpt);
    }

    private static final java.lang.ThreadLocal<String> osName = new java.lang.ThreadLocal<>();

    public static String getOSName()
    {

        return osName.get();
    }

    public static void setOSName( String  osname)
    {

        osName.set(osname);
    }




    private static final java.lang.ThreadLocal<String> currentFileName = new java.lang.ThreadLocal<>();

    public static String getCurrentFileName()
    {

        return currentFileName.get();
    }

    public static void setCurrentFileName(String  fileName)
    {

        currentFileName.set(fileName);
    }

    private static final java.lang.ThreadLocal<String> expectedReportName = new java.lang.ThreadLocal<>();

    public static String getExpectedReportName()
    {

        return expectedReportName.get();
    }

    public static void setExpectedReportName(String  fileName)
    {

        expectedReportName.set(fileName);
    }

    private static final java.lang.ThreadLocal<String> newFileName = new java.lang.ThreadLocal<>();

    public static String getNewFileName()
    {

        return newFileName.get();
    }

    public static void setNewFileName(String  fileName)
    {

        newFileName.set(fileName);
    }


    private static final java.lang.ThreadLocal<File> newFile = new java.lang.ThreadLocal<>();

    public static File getNewFile()
    {

        return newFile.get();
    }

    public static void setNewFile(File  fileName)
    {

        newFile.set(fileName);
    }

    private static final java.lang.ThreadLocal<String> reportName = new java.lang.ThreadLocal<>();

    public static String getReportName()
    {

        return reportName.get();
    }

    public static void setReportName( String  reportname)
    {

        reportName.set(reportname);
    }

    private static final java.lang.ThreadLocal<String> suiteName = new java.lang.ThreadLocal<>();

    public static String getSuiteName()
    {

        return suiteName.get();
    }

    public static void setSuiteName(String  suitetype)
    {

        suiteName.set(suitetype);
    }

    private static final java.lang.ThreadLocal<String> browserVersion = new java.lang.ThreadLocal<>();

    public static String getBrowserVersion()
    {

        return browserVersion.get();
    }

    public static void setBrowserVersion( String  browserversion)
    {

        browserVersion.set(browserversion);
    }

    private static final java.lang.ThreadLocal<ExtentSparkReporter> report = new java.lang.ThreadLocal<>();

    public static ExtentSparkReporter getReporterType()
    {

        return report.get();
    }

    public static void setReporterType(ExtentSparkReporter rpt)
    {

        report.set(rpt);
    }

    private static final java.lang.ThreadLocal<ExtentReports> extent = new java.lang.ThreadLocal<>();

    public static ExtentReports getExtent()
    {

        return extent.get();
    }

    public static void setExtent(ExtentReports rpt)
    {

        extent.set(rpt);
    }

    private static final java.lang.ThreadLocal<ExtentTest> feature = new java.lang.ThreadLocal<>();

    public static ExtentTest getFeature()
    {

        return feature.get();
    }

    public static void setFeature(ExtentTest rpt)
    {

        feature.set(rpt);
    }


    private static final java.lang.ThreadLocal<ExtentTest> scenario = new java.lang.ThreadLocal<>();

    public static ExtentTest getScenario()
    {

        return scenario.get();
    }

    public static void setScenario(ExtentTest rpt)
    {

        scenario.set(rpt);
    }

    private static final java.lang.ThreadLocal<ExtentTest> step = new java.lang.ThreadLocal<>();

    public static ExtentTest getStep()
    {

        return step.get();
    }

    public static void setStep(ExtentTest rpt)
    {

        step.set(rpt);
    }

    private static final java.lang.ThreadLocal<ArrayList<String>> featuresName = new java.lang.ThreadLocal<>();

    public static ArrayList<String> getFeaturesName()
    {

        return featuresName.get();
    }

    public static void setFeaturesName(ArrayList<String> rpt)
    {

        featuresName.set(rpt);
    }

    private static final java.lang.ThreadLocal<XmlSuite> suiteType = new java.lang.ThreadLocal<>();

    public static XmlSuite getSuiteType()
    {

        return suiteType.get();
    }

    public static void setSuiteType(XmlSuite  suitetype)
    {

        suiteType.set(suitetype);
    }
}
