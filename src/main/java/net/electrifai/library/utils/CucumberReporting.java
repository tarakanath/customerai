package net.electrifai.library.utils;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CucumberReporting
{
    public static void generateCucumberReport(String reportOutputPath, String jsonInputPath, String projectName, String buildNumber)
    {
        try {

            File reportOutputDirectory = new File(reportOutputPath);
            List<String> jsonFiles = new ArrayList<String>();
            jsonFiles.add(jsonInputPath);

            Configuration configuration = new Configuration(reportOutputDirectory, projectName);

            configuration.setBuildNumber(buildNumber);
            configuration.addClassifications("Platform", ThreadLocalManager.getOSName());
            configuration.addClassifications("Browser", ThreadLocalManager.getBrowserName().name());
            configuration.addClassifications("Browser-Version", ThreadLocalManager.getBrowserVersion());

            ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
            reportBuilder.generateReports();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
