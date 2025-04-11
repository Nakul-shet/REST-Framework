package org.example.Framework.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class EnvironmentConfig {

    private static final String DEFAULT_ENV = "test";
    private static Map<String, String> configMap;

    static {
        loadConfig();
    }

    private static void loadConfig() {
        String env = System.getProperty("env", DEFAULT_ENV);
        String configFilePath = "config/" + env + ".json";

        try (InputStream input = EnvironmentConfig.class.getClassLoader().getResourceAsStream(configFilePath)) {
            if (input == null) {
                System.out.println("Unable to find config file: " + configFilePath);
                return;
            }

            ObjectMapper mapper = new ObjectMapper();
            configMap = mapper.readValue(input, Map.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBaseUrl() {
        return System.getProperty("baseUrl", configMap.getOrDefault("api.baseUrl", "https://jsonplaceholder.typicode.com"));
    }

    public static int getRequestTimeout() {
        String timeoutStr = System.getProperty("requestTimeout", configMap.getOrDefault("api.timeout", "10000"));
        return Integer.parseInt(timeoutStr);
    }

    public static String getProperty(String key) {
        return configMap.get(key);
    }
}
