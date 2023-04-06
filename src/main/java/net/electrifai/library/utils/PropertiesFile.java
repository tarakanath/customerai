package net.electrifai.library.utils;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;

import java.io.File;
import java.io.FileNotFoundException;

public class PropertiesFile
{




     public static CompositeConfiguration getProperty(String fileName) throws FileNotFoundException, ConfigurationException
     {
         CompositeConfiguration config = new CompositeConfiguration();
         config.addConfiguration(new SystemConfiguration());
         PropertiesConfiguration proConfig=null;
       try{
           proConfig = new PropertiesConfiguration(new File(System.getProperty("user.dir")+"/src/test/resources/configProperties/"+fileName+""));
           proConfig.load();
           config.addConfiguration(proConfig);

         } catch (Exception ex)
         {
             ex.printStackTrace();
         }
         return config;
     }





    public static void writeProperty(String fileName,String key, String value) throws ConfigurationException
   {
       PropertiesConfiguration config=null;
       try{
           config = new PropertiesConfiguration(new File( System.getProperty("user.dir")+"/src/test/resources/configProperties/"+fileName+""));
           config.setProperty(key,value);
           config.save();


       } catch (Exception ex)
       {
           ex.printStackTrace();
       }
    }


}
