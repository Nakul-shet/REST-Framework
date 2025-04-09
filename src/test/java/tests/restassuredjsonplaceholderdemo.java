package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class restassuredjsonplaceholderdemo {

    @BeforeAll
    public static void setup() {
        // Set base URI for all requests
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testGetAllPosts() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("[0].id", notNullValue())
                .body("[0].title", notNullValue())
                .body("[0].body", notNullValue())
                .extract().response();

        System.out.println("GET Response: " + response.asString());
    }

    @Test
    public void testCreatePost() {
        String requestBody = "{"
                + "\"title\": \"New Post Title\","
                + "\"body\": \"This is the body of the new post\","
                + "\"userId\": 1"
                + "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("New Post Title"))
                .body("body", equalTo("This is the body of the new post"))
                .body("userId", equalTo(1))
                .body("id", notNullValue())
                .extract().response();

        System.out.println("POST Response: " + response.asString());
    }

    @Test
    public void testUpdatePostWithPut() {
        String requestBody = "{"
                + "\"id\": 1,"
                + "\"title\": \"Updated Post Title\","
                + "\"body\": \"This post has been completely updated\","
                + "\"userId\": 1"
                + "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated Post Title"))
                .body("body", equalTo("This post has been completely updated"))
                .body("id", equalTo(1))
                .extract().response();

        System.out.println("PUT Response: " + response.asString());
    }

    @Test
    public void testUpdatePostWithPatch() {
        String requestBody = "{"
                + "\"title\": \"Partially Updated Title\""
                + "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch("/posts/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("Partially Updated Title"))
                .body("id", equalTo(1))
                .extract().response();

        System.out.println("PATCH Response: " + response.asString());
    }

    @Test
    public void testDeletePost() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(200)  // JSONPlaceholder returns 200 for successful DELETE
                .extract().response();

        System.out.println("DELETE Response: " + response.asString());

        // Verify post is deleted by trying to get it (should return empty body)
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body(equalTo("{}"));
    }
}