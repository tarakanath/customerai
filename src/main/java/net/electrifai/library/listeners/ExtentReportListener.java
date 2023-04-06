package net.electrifai.library.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import net.electrifai.library.constants.EnumConstants;
import net.electrifai.library.utils.ThreadLocalManager;

import org.apache.poi.xssf.model.Themes;

public class ExtentReportListener
{
   public static  ExtentReports extent;
    private static String reportName;

    public static ExtentReports setup()
    {
        try {
            ThreadLocalManager.setReportName("extentreports");
            String reportLocation =System.getProperty("user.dir")+"/reports/"+ThreadLocalManager.getReportName()+".html";
            ThreadLocalManager.setLocalDirectoryPath(System.getProperty("user.dir")+"/reports/");
            ThreadLocalManager.setReporterType(new ExtentSparkReporter(reportLocation));
            ThreadLocalManager.getReporterType().config().setDocumentTitle("Web Automation Test Report");
            ThreadLocalManager.getReporterType().config().setReportName("Customer AI Automation Test Report");
            ThreadLocalManager.getReporterType().config().setTheme(Theme.STANDARD);

                        // report.config().setCSS(".node.level-1  ul{ display:none;} .node.level-1.active ul{display:block;}  .card-panel.environment  th:first-child{ width:30%;}");
            System.out.println("Extent report inistialized ");

            extent=new ExtentReports();

            extent.attachReporter(ThreadLocalManager.getReporterType());
            extent.setSystemInfo("Application", "Customer EngagementAi");
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));
            System.out.println("System info has been set ");
        }catch (Exception e){}
        return extent;

    }



    public static void testStepHandle(String testStatus, ExtentTest extentTest, Throwable throwable)
    {
        switch (EnumConstants.TestStatus.valueOf(testStatus))
        {
            case FAIL :
                extentTest.fail(MarkupHelper.createLabel("Test Case is Failed :", ExtentColor.RED));
                extentTest.fail(throwable.fillInStackTrace());

                if(ThreadLocalManager.getDriver()!=null)
                {
                    ThreadLocalManager.getDriver().quit();
                }
                break;

            case PASS:
                extentTest.pass(MarkupHelper.createLabel("Test Case is Passed : ", ExtentColor.GREEN));
                break;

            default: break;
        }
    }

}
