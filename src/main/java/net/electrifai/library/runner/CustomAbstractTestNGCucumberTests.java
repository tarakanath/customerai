package net.electrifai.library.runner;

import net.electrifai.library.utils.PropertiesFile;
import net.electrifai.library.utils.ThreadLocalManager;
import net.electrifai.library.utils.excelsheet.ReadAndWriteExcel;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CustomAbstractTestNGCucumberTests {
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) throws Throwable {
        // the 'featureWrapper' parameter solely exists to display the feature file in a test report
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    /**
     * Returns two dimensional array of PickleEventWrapper scenarios
     * with their associated CucumberFeatureWrapper feature.
     *
     * @return a two dimensional array of scenarios features.
     */
    @DataProvider
    public Iterator<Object[]> scenarios() throws ConfigurationException, IOException {
        ArrayList<Object[]> modifiedList = new ArrayList<>();
        if (testNGCucumberRunner == null) {
            //return new Object[0][0];
            return modifiedList.iterator();
        }
        //Object[][] data = testNGCucumberRunner.provideScenarios();
        //PickleEventWrapper pickleEventWrapper = (PickleEventWrapper)data[0][0];
        //CucumberFeatureWrapper cucumberFeatureWrapper = (CucumberFeatureWrapper)data[0][1];
        modifiedList = filterTheFeature(testNGCucumberRunner.provideScenarios());
        //return testNGCucumberRunner.provideScenarios();
        return modifiedList.iterator();
    }

    // data[0][0] ->> PickleEventWrapper
    // data[0][1] ->> CucumberFeatureWrapper

    /*
     * private method, that will return the array list - Contains the feature which we want to execute
     * iterate over 2d object array.
     * using CucumberFeatureWrapper check,
     * if feature is present
     * 	-- add it to the array list
     * else
     *  -- skip
     * */

    private ArrayList<Object[]> filterTheFeature(Object[][] data) throws ConfigurationException, IOException {


//        List<String> featureList=new ArrayList<>();
//        if(System.getProperty("features")!=null)
//        {
//            String[] features=System.getProperty("features").split(",");
//           for(int g=0 ; g<=features.length-1;g++)
//           {
//               featureList.add(features[g]);
//           }
//
//            PropertiesFile.writeProperty("testEnvironment.properties", "features", StringUtils.join(features, ","));
//        }else
//        if(!PropertiesFile.getProperty("testEnvironment.properties", "features").isEmpty())
//        {
//            String[] features= PropertiesFile.getProperty("testEnvironment.properties", "features").split(",");
//            for(int g=0 ; g<=features.length-1;g++)
//            {
//                featureList.add(features[g]);
//            }
//        }
//
//        List<String> scenarioList=new ArrayList<>();
//        if(System.getProperty("scenarios")!=null)
//        {
//            String[] scenarios=System.getProperty("scenarios").split(",");
//            for(int g=0 ; g<=scenarios.length-1;g++)
//            {
//                scenarioList.add(scenarios[g]);
//            }
//
//            PropertiesFile.writeProperty("testEnvironment.properties", "scenarios", StringUtils.join(scenarios, ","));
//        }else
//        if(!PropertiesFile.getProperty("testEnvironment.properties", "scenarios").isEmpty())
//        {
//            String[] scenarios= PropertiesFile.getProperty("testEnvironment.properties", "scenarios").split(",");
//            for(int g=0 ; g<=scenarios.length-1;g++)
//            {
//                scenarioList.add(scenarios[g]);
//            }
//        }
//
//
//
//        //check later
////        String featureValue =null;
////
////        if(featureValue == null || featureValue.isEmpty()){
////            return getFeatureList(data);
////        }
//
//
//        //List<String> featureList = Arrays.asList("Web Element Functions","Working with Java Script Popup");
//        ArrayList<Object[]> modifiedList = new ArrayList<>();


        //Check if featurelist is  null or not

//        if(featureList==null&&scenarioList==null)
//        {
//           return getFeatureList(data);
//        }
//        else
//        if(!featureList.isEmpty() &&scenarioList.isEmpty())
//        {
//
//            if (data != null) {
//                for (int i = 0; i < data.length; i++)
//                {
//                    // For v6+
//                    FeatureWrapper cucumberFeatureWrapper = (FeatureWrapper) data[i][1];
//                    String featureName = getFeatureName(cucumberFeatureWrapper.toString().trim().replaceAll("\"", ""));
//
//                    if (featureList.contains(featureName))
//                    {
//
////                FeatureWrapper cucumberFeatureWrapper = (FeatureWrapper)data[i][1];
////                if(featureList.contains(cucumberFeatureWrapper.toString().trim().replaceAll("\"", ""))){
//////
////                        PickleWrapper pickleEventWrapper = (PickleWrapper) data[i][0];
////                        if (!pickleEventWrapper.getPickle().getTags().isEmpty()) {
////                            for (String aTag : tagList) {
////                                if (isTagPresent(aTag, pickleEventWrapper.getPickle().getTags())) {
////                                    modifiedList.add(data[i]);
////                                }
////                            }
////                        }
//
//                         modifiedList.add(data[i]);
//                        // data[0][0] ->> PickleEventWrapper i = 0;
//                        // data[0][1] ->> CucumberFeatureWrapper
//                        // data[1][0] ->> PickleEventWrapper i = 1;
//                        // data[1][1] ->> CucumberFeatureWrapper
//                    }
//                }
//            }
//        }
//        else
        ArrayList<Object[]> modifiedList = new ArrayList<>();
        try {

            String suiteType = ThreadLocalManager.getSuiteName();

            if (suiteType.equals("all")) {
                if (data != null) {
                    for (int i = 0; i < data.length; i++) {

                        PickleWrapper pickleEventWrapper = (PickleWrapper) data[i][0];
                        if (!pickleEventWrapper.getPickle().getTags().isEmpty()) {
                            modifiedList.add(data[i]);
                        }
                    }
                }
            } else {
                if (ThreadLocalManager.getSuiteName().contains(";")) {
                    String[] tag = ThreadLocalManager.getSuiteName().split(";");
                    for (int j = 0; j < tag.length; j++) {  //xy;yz;zx
                        suiteType = "@" + tag[j].trim();
                        if (data != null) {
                            for (int i = 0; i < data.length; i++) {
                                PickleWrapper pickleEventWrapper = (PickleWrapper) data[i][0];
                                //System.out.println("Inside the for block");
                                if (!pickleEventWrapper.getPickle().getTags().isEmpty()) {
                                    //System.out.println("Inside the if if block");
                                    if (isTagPresent(suiteType, pickleEventWrapper.getPickle().getTags())) {
                                        //System.out.println(data[i]+" This is in data of i");
                                        modifiedList.add(data[i]);
                                    }
                                }
                            }

                        }
                    }
                } else {
                    suiteType = "@" + ThreadLocalManager.getSuiteName();
                    System.out.println(ThreadLocalManager.getSuiteName() + " This is the suite name");
                    if (data != null) {
                        //System.out.println("Inside the if block");
                        System.out.println(data.length + " data length");
                        for (int i = 0; i < data.length; i++) {
                            PickleWrapper pickleEventWrapper = (PickleWrapper) data[i][0];
                            //System.out.println("Inside the for block");
                            if (!pickleEventWrapper.getPickle().getTags().isEmpty()) {
                                //System.out.println("Inside the if if block");
                                if (isTagPresent(suiteType, pickleEventWrapper.getPickle().getTags())) {
                                    //  System.out.println(data[i]+" This is in data of i");
                                    modifiedList.add(data[i]);
                                }
                            }
                        }

                    }
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

            ThreadLocalManager.setSuiteName("TestRunner");

            for (int i = 0; i < data.length; i++) {
                PickleWrapper pickleEventWrapper = (PickleWrapper) data[i][0];
                if (!pickleEventWrapper.getPickle().getTags().isEmpty()) {
                    modifiedList.add(data[i]);
                }
            }
        }
//
//        }else
//            if(!(scenarioList.isEmpty() && featureList.isEmpty()))
//            {
//
//                if (data != null) {
//                    for (int i = 0; i < data.length; i++)
//                    {
//                        // For v6+
//                        FeatureWrapper cucumberFeatureWrapper = (FeatureWrapper) data[i][1];
//                        String featureName = getFeatureName(cucumberFeatureWrapper.toString().trim().replaceAll("\"", ""));
//
//                        if (featureList.contains(featureName))
//                        {
//
//                            PickleWrapper pickleEventWrapper = (PickleWrapper) data[i][0];
//                            if (!pickleEventWrapper.getPickle().getTags().isEmpty())
//                            {
//                                for (String aTag : scenarioList)
//                                {
//                                    if (isTagPresent(aTag, pickleEventWrapper.getPickle().getTags()))
//                                    {
//                                        modifiedList.add(data[i]);
//                                    }
//                                }
//                            }
//
//                           // modifiedList.add(data[i]);
//                        }
//                    }
//                }
//

        return modifiedList;
    }

    private ArrayList<Object[]> getFeatureList(Object[][] data) {
        ArrayList<Object[]> modifiedList = new ArrayList<>();
        if (data != null) {
            for (int i = 0; i < data.length; i++) {
                modifiedList.add(data[i]);
            }
        }
        return modifiedList;
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        if (testNGCucumberRunner == null) {
            return;
        }
        testNGCucumberRunner.finish();
    }

    // For v6+
    private String getFeatureName(String name) {
        String featureName =
                name.replace("Optional[", "");
        if (featureName.lastIndexOf("]") != -1) {
            featureName = featureName.substring(0, featureName.lastIndexOf("]"));
        }
        return featureName;
    }


    private boolean isTagPresent(String aTag, List<String> aTagList) {

//        return aTagList.stream().filter(new Predicate<Messages.Pickle.PickleTag>() {
//            @Override
//            public boolean test(Messages.Pickle.PickleTag t) {
//                return aTag.equalsIgnoreCase(t.getName());
//            }
//        }).collect(Collectors.toList()).isEmpty();

        for (String pickleTag : aTagList) {
            if (aTag.equalsIgnoreCase(pickleTag)) {
                return true;
            }
        }
        return false;

    }


    public List<List<String>> readCucumberOptionsFromExcel(String excelPath) {
        List<List<Object>> featureTabData = ReadAndWriteExcel.readExcelTab(excelPath, "Feature", "SanitySuite");
        List<List<Object>> suiteTabData = ReadAndWriteExcel.readExcelTab(excelPath, "SanitySuite", "SanitySuite");
        List<String> tagList = new ArrayList<>();
        List<String> featureList = new ArrayList<>();

        Map<Object, Object> featureTabHeaderRowData = null;
        Map<Object, Object> suiteTabHeaderRowData = null;
        List<List<String>> cucumberOptions = new ArrayList<List<String>>();

        for (int i = 0; i <= featureTabData.size() - 1; i++) {
            featureTabHeaderRowData = ReadAndWriteExcel.readExcelTabRow(excelPath, "Feature", "SanitySuite", i + 1);

            switch (featureTabHeaderRowData.get("Execution Mode").toString()) {
                case "Enabled":

                    for (int j = 0; j <= suiteTabData.size() - 1; j++) {
                        suiteTabHeaderRowData = ReadAndWriteExcel.readExcelTabRow(excelPath, "SanitySuite", "SanitySuite", j + 1);

                        if (featureTabHeaderRowData.get("Feature").equals(suiteTabHeaderRowData.get("Feature"))) {
                            tagList.add("@" + suiteTabHeaderRowData.get("Tags").toString());

                            if (!featureList.contains(suiteTabHeaderRowData.get("Feature"))) {
                                featureList.add(suiteTabHeaderRowData.get("Feature").toString());
                            }
                        }

                    }

                    break;

                case "Disabled":

                    break;


                case "EnableSpecificTags":

                    for (int j = 0; j <= suiteTabData.size() - 1; j++) {
                        suiteTabHeaderRowData = ReadAndWriteExcel.readExcelTabRow(excelPath, "SanitySuite", "SanitySuite", j + 1);

                        if (featureTabHeaderRowData.get("Feature").equals(suiteTabHeaderRowData.get("Feature"))) {
                            if (Boolean.valueOf(suiteTabHeaderRowData.get("Enabled").toString()) == true) {
                                tagList.add("@" + suiteTabHeaderRowData.get("Tags").toString());

                                if (!featureList.contains(suiteTabHeaderRowData.get("Feature"))) {
                                    featureList.add(suiteTabHeaderRowData.get("Feature").toString());
                                }
                            }
                        }

                    }

                    break;

                case "IgnoreSpecificTags":

                    for (int j = 0; j <= suiteTabData.size() - 1; j++) {
                        suiteTabHeaderRowData = ReadAndWriteExcel.readExcelTabRow(excelPath, "SanitySuite", "SanitySuite", j + 1);


                        if (featureTabHeaderRowData.get("Feature").equals(suiteTabHeaderRowData.get("Feature"))) {
                            if ((!Boolean.valueOf(suiteTabHeaderRowData.get("Enabled").toString()) == false) || (suiteTabHeaderRowData.get("Enabled").toString().equals(""))) {
                                tagList.add("@" + suiteTabHeaderRowData.get("Tags").toString());
                            }
                            if (!featureList.contains(suiteTabHeaderRowData.get("Feature"))) {
                                featureList.add(suiteTabHeaderRowData.get("Feature").toString());
                            }
                        }

                    }


                default:
                    System.out.println("Could not find the feature ");

            }


        }

        cucumberOptions.add(featureList);
        cucumberOptions.add(tagList);

        return cucumberOptions;
    }

}
