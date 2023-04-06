package net.electrifai.library.listeners;

import com.beust.jcommander.internal.Maps;
import net.electrifai.library.constants.EnumConstants;
import net.electrifai.library.utils.PropertiesFile;
import net.electrifai.library.utils.ThreadLocalManager;
import org.apache.commons.lang.StringUtils;
import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.*;

public class CustomIAlterSuiteListener implements IAlterSuiteListener
{
     @Override
    public void alter(List<XmlSuite> suites)
     {
         try {
             XmlSuite suite = suites.get(0);
             if(System.getProperty("testsuite")!=null)
             {
                 PropertiesFile.writeProperty("testEnvironment.properties", "testSuite", System.getProperty("testsuite"));

                 ThreadLocalManager.setSuiteName(PropertiesFile.getProperty("testEnvironment.properties").getString("testSuite"));
             }
             else {

                 PropertiesFile.writeProperty("testEnvironment.properties", "testSuite", suite.getName());
                 ThreadLocalManager.setSuiteName(PropertiesFile.getProperty("testEnvironment.properties").getString("testSuite"));
             }

                 suite.setName(ThreadLocalManager.getSuiteName());
                 suite.setParallel(getParallelMode(PropertiesFile.getProperty("testEnvironment.properties").getString("parallelMode")));

                 String[] tests = null;
                 if (System.getProperty("browser") != null)
                 {
                     PropertiesFile.writeProperty("testEnvironment.properties", "testName", System.getProperty("browser"));

                     tests = getArray(PropertiesFile.getProperty("testEnvironment.properties").getStringArray("testName"));
                 }
                 else
                 {

                     tests =getArray(PropertiesFile.getProperty("testEnvironment.properties").getStringArray("browser"));
                 }

                 String[] testParameters = null;

                 if (System.getProperty("browser") != null) {
                     testParameters = System.getProperty("browser").split(",");
                     PropertiesFile.writeProperty("testEnvironment.properties", "browser", StringUtils.join(testParameters, ","));
                 }
                 else {
                     testParameters = PropertiesFile.getProperty("testEnvironment.properties").getStringArray("browser");
                 }

                 XmlTest test;
                 for (int i = 0; i <= tests.length - 1; i++) {
                     test = new XmlTest(suite);
                     test.setName(tests[i]);
                     String browserName = System.getProperty("browser");
                     System.out.println(browserName);

                     Map<String, String> parameters = Maps.newHashMap();
                     parameters.put("browser", testParameters[i]);
                     test.setParameters(parameters);
                     List<XmlClass> classes = new ArrayList<XmlClass>(); // <classes>
                     String[] testclasses = PropertiesFile.getProperty("testEnvironment.properties").getStringArray("className");
                     for (int j = 0; j <= testclasses.length - 1; j++) {
                         classes.add(new XmlClass(testclasses[j]));
                     }
                     test.setXmlClasses(classes);
                 }


         }catch (Exception e)
         {
             e.printStackTrace();
         }
     }

     public XmlSuite.ParallelMode getParallelMode(String parallelMode)
     {
         XmlSuite.ParallelMode a=null;
         switch (EnumConstants.ParallelMode.valueOf(parallelMode))
         {
             case none:
                 a=XmlSuite.ParallelMode.NONE;
             break;
             case classes:
                a= XmlSuite.ParallelMode.CLASSES;
             break;
             case tests:
                 a= XmlSuite.ParallelMode.TESTS;
             break;
             case instance:
                 a= XmlSuite.ParallelMode.INSTANCES;
             break;
             case methods:
                 a= XmlSuite.ParallelMode.METHODS;
             break;
             default:
                 System.out.println("could not find parallelmode type");
         }
         return a;
     }


     public static  String[] getArray(String[] value)
     {
        return new LinkedHashSet<String>(Arrays.asList(value)).toArray(new String[0]);
     }
}
