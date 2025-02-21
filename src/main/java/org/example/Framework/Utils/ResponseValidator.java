package org.example.Framework.Utils;

import io.restassured.response.Response;

import org.testng.Assert;

public class ResponseValidator {

    public static void validateStatusCode(Response response, int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, expectedStatusCode,
                "Expected status code " + expectedStatusCode + " but got " + actualStatusCode);
    }

    public static void validateResponseTime(Response response, long maxTimeInMs) {
        long responseTime = response.getTime();
        Assert.assertTrue(responseTime <= maxTimeInMs,
                "Response time " + responseTime + "ms exceeded maximum " + maxTimeInMs + "ms");
    }

    public static void validateField(Response response, String jsonPath, Object expectedValue) {
        Object actualValue = response.jsonPath().get(jsonPath);
        Assert.assertEquals(actualValue, expectedValue,
                "Field " + jsonPath + " expected " + expectedValue + " but got " + actualValue);
    }

    public static void validateResponseNotEmpty(Response response) {
        String responseBody = response.getBody().asString();
        Assert.assertFalse(responseBody == null || responseBody.isEmpty(),
                "Response body should not be empty");
    }

    /**
     * Validates that a list response contains the expected number of items
     */
//    public static void validateListSize(Response response, String jsonPath, int expectedSize) {
//        List<?> items = response.jsonPath().getList(jsonPath);
//        Assert.assertEquals(items.size(), expectedSize,
//                "Expected " + expectedSize + " items but found " + items.size());
//        logger.info("List size validation passed: {} items", items.size());
//    }
//
//    /**
//     * Validates that the response conforms to the expected schema
//     */
//    public static <T> void validateSchema(Response response, Class<T> schemaClass) {
//        try {
//            objectMapper.readValue(response.asString(), schemaClass);
//            logger.info("Schema validation passed for {}", schemaClass.getSimpleName());
//        } catch (Exception e) {
//            logger.error("Schema validation failed: {}", e.getMessage());
//            Assert.fail("Schema validation failed: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Validates that a specific header exists and has the expected value
//     */
//    public static void validateHeader(Response response, String headerName, String expectedValue) {
//        String actualValue = response.getHeader(headerName);
//        Assert.assertEquals(actualValue, expectedValue,
//                "Header " + headerName + " expected " + expectedValue + " but got " + actualValue);
//        logger.info("Header validation passed for {}: value = {}", headerName, actualValue);
//    }
}
