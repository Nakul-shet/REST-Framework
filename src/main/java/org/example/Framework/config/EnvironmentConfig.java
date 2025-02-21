package org.example.Framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvironmentConfig {
    private static final Properties properties = new Properties();
    private static final String DEFAULT_ENV = "test";

    static {
        loadProperties();
    }

    private static void loadProperties() {
        String env = System.getProperty("env", DEFAULT_ENV);
        String propertiesFile = "config/" + env + ".properties";

        try (InputStream input = EnvironmentConfig.class.getClassLoader().getResourceAsStream(propertiesFile)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + propertiesFile);
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getBaseUrl() {
        return System.getProperty("baseUrl", properties.getProperty("api.baseUrl", "https://jsonplaceholder.typicode.com"));
    }

    public static int getRequestTimeout() {
        return Integer.parseInt(System.getProperty("requestTimeout", properties.getProperty("api.timeout", "10000")));
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}