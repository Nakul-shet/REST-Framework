package tests.ReqResExample;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.Framework.models.JSONPlaceholderAPImodels.User;
import org.junit.jupiter.api.BeforeAll;
import org.testng.Assert;
import org.testng.annotations.Test;


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
                .header("Authorization", authToken)
                .header("Content-Type", "application/json")
                .queryParam("page" , "2")
                .when()
                .get("/api/users")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // https://api.example.com/users/123/orders?status=shipped&limit=10

        JsonPath jsonPath = response.jsonPath();

        int firstUserId = jsonPath.getInt("data[0].id");
        String firstUserEmail = jsonPath.getString("data[0].email");

        System.out.println("First tests.User ID: " + firstUserId);
        System.out.println("First tests.User Email: " + firstUserEmail);

        Assert.assertTrue(firstUserEmail.contains("@"), "Email should be valid");
    }

    @Test
    public void testCreateUser() {

        ReqResUserPOJO user = new ReqResUserPOJO("morpheus", "leader");

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract()
                .response();

        // Validate response
        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");

        System.out.println("Created User Name: " + name);
        System.out.println("Created User Job: " + job);

        Assert.assertEquals(name, "morpheus");
        Assert.assertEquals(job, "leader");
    }
}