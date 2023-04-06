package net.electrifai.library.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Screenshot
{
    public static String getBase64ScreenShot()
    {
        String Base64StringOfScreenshot = "";
        try {
            File src = ((TakesScreenshot) ThreadLocalManager.getDriver()).getScreenshotAs(OutputType.FILE);

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYY_HHmmss");
            String sDate = sdf.format(date);
            String screenShotPath=System.getProperty("user.dir")+"/target/screenshots"+ "image_" + sDate + ".png";
            FileUtils.copyFile(src, new File(screenShotPath));

            byte[] fileContent = FileUtils.readFileToByteArray(src);
            Base64StringOfScreenshot = "data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent);

        }catch (Exception e)
        {e.printStackTrace();
        }
        return Base64StringOfScreenshot;

    }


    public static byte[] getScreenShot()
    {
         byte[] screenshot=null;
        try {


            screenshot = ((TakesScreenshot) ThreadLocalManager.getDriver()).getScreenshotAs(OutputType.BYTES);


        }catch (Exception e)
        {e.printStackTrace();
        }
        return screenshot;

    }


    public static byte[] getBase64()
    {
        Base64.Decoder decoder = Base64.getDecoder();
         String encodedString= ((TakesScreenshot) ThreadLocalManager.getDriver()).getScreenshotAs(OutputType.BASE64);
        byte[] bytes = decoder.decode(encodedString);

        return bytes;
    }
}
