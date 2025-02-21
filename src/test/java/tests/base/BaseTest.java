package tests.base;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.example.Framework.config.EnvironmentConfig;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void setupTestSuite() {
        System.out.println("Setting up test suite");
    }

    @BeforeClass
    public void setupTest() {
        System.out.println("Setting up test class");

        // Configure REST Assured
        RestAssured.baseURI = EnvironmentConfig.getBaseUrl();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    }
}
