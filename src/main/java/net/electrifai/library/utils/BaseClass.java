package net.electrifai.library.utils;

import net.electrifai.library.runner.CustomAbstractTestNGCucumberTests;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.sorting.SortingMethod;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static net.electrifai.library.listeners.ExtentReportListener.setup;

public class BaseClass extends CustomAbstractTestNGCucumberTests
{
    @Parameters({"browser"})
    @BeforeTest
    public void launchApp(@Optional("")String browser) throws IOException, ConfigurationException
    {
        ThreadLocalManager.setExtent(setup());

        String resourcesPath= File.separator +"src" + File.separator + "test"+ File.separator + "resources" ;
        String expectedFolderPath=System.getProperty("user.dir")+File.separator+resourcesPath+File.separator + "downloads";

        if(FileHandling.createDirectoryIfNotExist(expectedFolderPath))
        {
            FileHandling.deleteDirectoryFiles(expectedFolderPath);
        }

        if(Boolean.valueOf(PropertiesFile.getProperty("testEnvironment.properties").getString("launch.browser.all.feature.wise")))
        {
            DriverSetUp.initializeDriver(setBrowserName(browser));
            DriverSetUp.launchUrl();
        }


    }


    @AfterTest
    public void close() throws ConfigurationException, IOException
    {
        if(Boolean.valueOf(PropertiesFile.getProperty("testEnvironment.properties").getString( "close.browser.all.feature.wise")))
        {

            DriverSetUp.closeDriver();
        }
        ThreadLocalManager.getExtent().flush();
        FileHandling.renameReport();
    }

    public static String setBrowserName(String browser) throws ConfigurationException, FileNotFoundException {
        if(browser.equals(""))
        {

            return PropertiesFile.getProperty("testEnvironment.properties").getString( "browser");

        }else
        {
            return  browser;
        }
    }

    @AfterSuite
    public void afterSuite()
    {
        try {

            String currentTime= String.valueOf(LocalTime.now()).replaceAll(":", "_");
            String currentDateTime = LocalDate.now() + "-" + currentTime;
            String reportOutputPath = "reports/cucumber-reports/" + currentDateTime;
            String jsonInputPath = "target/cucumber.json";
            String projectName = PropertiesFile.getProperty("testEnvironment.properties").getString( "project");
            String buildNumber = PropertiesFile.getProperty("testEnvironment.properties").getString( "buildNumber");

            CucumberReporting.generateCucumberReport(reportOutputPath, jsonInputPath, projectName, buildNumber);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }






}
