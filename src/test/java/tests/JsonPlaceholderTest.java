package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class JsonPlaceholderTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testGetAllPosts() {
        given()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("[0].id", notNullValue());
    }

    @Test
    public void testGetPostById() {
        int postId = 1;
        given()
                .pathParam("id", postId)
                .when()
                .get("/posts/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(postId))
                .body("userId", notNullValue())
                .body("title", notNullValue())
                .body("body", notNullValue());
    }

    @Test
    public void testCreatePost() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }")
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("foo"))
                .body("body", equalTo("bar"))
                .body("userId", equalTo(1));
    }

    @Test
    public void testUpdatePost() {
        int postId = 1;
        given()
                .contentType(ContentType.JSON)
                .body("{ \"title\": \"updated title\" }")
                .pathParam("id", postId)
                .when()
                .put("/posts/{id}")
                .then()
                .statusCode(200)
                .body("title", equalTo("updated title"));
    }

    @Test
    public void testDeletePost() {
        int postId = 1;
        given()
                .pathParam("id", postId)
                .when()
                .delete("/posts/{id}")
                .then()
                .statusCode(200);
    }
}
