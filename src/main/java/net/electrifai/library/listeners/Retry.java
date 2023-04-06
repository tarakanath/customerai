package net.electrifai.library.listeners;

import net.electrifai.library.utils.PropertiesFile;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.io.IOException;

public class Retry implements IRetryAnalyzer
{


    public int count = 0;
    private static int maxTry = 1;



    @Override
    public boolean retry(ITestResult iTestResult)
    {
        if (!iTestResult.isSuccess())
        {  //Check if test not succeed

            try {
                if(retryOnException(PropertiesFile.getProperty("testEnvironment.properties").getStringArray("reTryOnExceptions"), iTestResult.getThrowable().toString()))//retry with specific exception
                {
                    if (count < maxTry)
                    {                            //Check if maxtry count is reached
                        ++count;                                     //Increase the maxTry count by 1
                        iTestResult.setStatus(ITestResult.FAILURE);  //Mark test as failed
                        return true;                                 //Tells TestNG to re-run the test
                    } else
                    {
                        iTestResult.setStatus(ITestResult.FAILURE);  //If maxCount reached,test marked as failed
                    }
                }else
                {
                    iTestResult.setStatus(ITestResult.FAILURE);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ConfigurationException e) {
                e.printStackTrace();
            }
        } else
        {
            iTestResult.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
        }
        return false;
    }



    public boolean retryOnException(String[] exceptions,String actualException )
    {
        for(int a=0;a<=exceptions.length-1;a++)
        {
            if(actualException.contains(exceptions[a]))
            {
                return true;
            }
        }
        return false;
    }
}
