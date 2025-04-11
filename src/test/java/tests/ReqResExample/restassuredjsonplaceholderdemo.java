package tests.ReqResExample;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.testng.Assert;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class restassuredjsonplaceholderdemo {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test()
    public void testGetRequestWithAuthToken() {

        String authToken = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.fakeUser.3x4mpl3T0k3nStr1ng";

        Response response = RestAssured
                .given()
                .auth().basic("username", "password")
                .header("Authorization", authToken)
                .header("Content-Type", "application/json")
                .queryParam("page" , "2")
                .when()
                .get("/api/users")
                .then()
                .statusCode(200)
                .extract()
                .response();

        JsonPath jsonPath = response.jsonPath();

        int firstUserId = jsonPath.getInt("data[0].id");
        String firstUserEmail = jsonPath.getString("data[0].email");

        System.out.println("First tests.User ID: " + firstUserId);
        System.out.println("First tests.User Email: " + firstUserEmail);

        Assert.assertTrue(firstUserEmail.contains("@"), "Email should be valid");
    }
}