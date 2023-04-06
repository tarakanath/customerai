package net.electrifai.library.utils;

import com.aventstack.extentreports.Status;
import io.cucumber.java.Scenario;

public class LogManager
{

    public  static  Scenario scenario;

    public static void printInfoLog(String logMessage)
    {
        scenario.log(logMessage);
        ThreadLocalManager.getStep().log(Status.INFO, logMessage );
    }

    public static void printPassLog(String logMessage)
    {
        //scenario.log("\033[1;92m"+logMessage);
        scenario.log(logMessage);
        ThreadLocalManager.getStep().log(Status.PASS, "<font color=green>"+logMessage+"</font>" );

    }

    public static void printFailLog(String logMessage)
    {
        scenario.log(logMessage);
        ThreadLocalManager.getStep().log(Status.FAIL, "<b><font color=red>"+logMessage+"</font></b> ");
    }

    public static void printExceptionLog(Throwable throwable, String logMessage)
    {
        String customLog="EXCEPTION : "+logMessage+"";
        scenario.log(customLog);
        ThreadLocalManager.getStep().log(Status.FAIL, "<b><font color=red>"+customLog+"</font></b>" );
        ThreadLocalManager.getStep().fail(throwable.fillInStackTrace());
    }


}
