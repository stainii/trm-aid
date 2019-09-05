package be.stijnhooft.trmaid.helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Reads parameters from a file
 */
public class Parameters {

    public static String URL = "url";
    public static String USERNAME = "username";
    public static String PASSWORD = "password";
    public static String PROJECT_NUMBER = "project.number";
    public static String PROJECT_TASK_NUMBER = "project.task.number";

    private Properties properties;

    public Parameters(String parameterFileLocation) {
        try {
            this.properties = new Properties();
            this.properties.load(new FileInputStream(parameterFileLocation));
        } catch (IOException e) {
            throw new RuntimeException("Could not load parameters file", e);
        }
    }

    public String get(String key) {
        if (properties.containsKey(key)) {
            return properties.getProperty(key);
        } else {
            throw new IllegalArgumentException("Parameters file does not contain key " + key);
        }
    }

}
