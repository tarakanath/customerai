package net.electrifai.library.listeners;

import net.electrifai.library.utils.ThreadLocalManager;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.sorting.SortingMethod;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TestNGListener extends ExtentReportListener implements ITestListener
{

    @Override
    public void onStart(ITestContext context)
    {

    }

    @Override
    public void onFinish(ITestContext context)
    {
        Set<ITestResult> skippedTests  = context.getSkippedTests().getAllResults();
        Set<ITestResult> failedTests  = context.getFailedTests().getAllResults();

        for (ITestResult temp : failedTests) {
            ITestNGMethod method = temp.getMethod();
            String failClassName = method.getRealClass().getName();
            try {
                for (ITestResult iTestResult : skippedTests) {
                    String skipClassName = iTestResult.getMethod().getRealClass().getName();
                    if ((skipClassName + iTestResult.getMethod().getMethodName()).equals(failClassName + method.getMethodName())) {
                        skippedTests.remove(iTestResult);
                    }
                }
            } catch (Exception e)
            {
            }
        }

        for (ITestResult temp : skippedTests) {
            ITestNGMethod method = temp.getMethod();
            if (context.getSkippedTests().getResults(method).size() > 1)
            {
                skippedTests.remove(temp);
            } else {
                if (context.getPassedTests().getResults(method).size() > 0)
                {
                    skippedTests.remove(temp);
                }
            }
        }


        // extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result)
    {
        System.out.println("On Test Start ");

    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        System.out.println("On Test Success ");

    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        System.out.println("On Test failure ");
       // Take base64Screenshot screenshot.
        //ExtentReport.addLog(result.getStatus(),"failed");

    }
    @Override
    public void onTestSkipped(ITestResult result)
    {
        if(result.wasRetried())
        {
            ThreadLocalManager.getExtent().removeTest(ThreadLocalManager.getScenario());
        }

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result)
    {
        System.out.println("On Test failed with timeout  ");
         //  this.onTestFailure(result);
    }




}
