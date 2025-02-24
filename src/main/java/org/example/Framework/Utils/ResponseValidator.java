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

}
