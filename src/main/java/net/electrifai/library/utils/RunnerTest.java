package net.electrifai.library.utils;
import net.electrifai.library.utils.PropertiesFile;
import org.apache.commons.configuration.ConfigurationException;

import java.io.FileNotFoundException;

public class RunnerTest {
    public static void main(String[] args) throws ConfigurationException, FileNotFoundException {
        System.out.println(PropertiesFile.getProperty("testEnvironment.properties").getString("testSuite"));

        String s=" teja";;
        s="@"+s.trim();
        System.out.println(s);
    }
}
