package org.example.Framework.core;

import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

public class ResponseHandler {

    /**
     * Extracts response as a single object
     */
    public static <T> T extractResponseAsObject(Response response, Class<T> responseClass) {
        try {
            return response.as(responseClass);
        } catch (Exception e) {
            throw new RuntimeException("Response mapping failed", e);
        }
    }

    /**
     * Extracts response as a list of objects
     */
    public static <T> List<T> extractResponseAsList(Response response, Class<T[]> arrayClass) {
        try {
            T[] extractedArray = response.as(arrayClass);
            return Arrays.asList(extractedArray);
        } catch (Exception e) {
            throw new RuntimeException("Response list mapping failed", e);
        }
    }

    /**
     * Extracts value from JSON path
     */
    public static <T> T extractValueFromJsonPath(Response response, String jsonPath, Class<T> valueType) {
        try {
            return response.jsonPath().getObject(jsonPath, valueType);
        } catch (Exception e) {
            throw new RuntimeException("JSON path extraction failed", e);
        }
    }
}
