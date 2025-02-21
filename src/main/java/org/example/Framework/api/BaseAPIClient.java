package org.example.Framework.api;

import org.example.Framework.core.ResponseHandler;
import org.example.Framework.core.RequestBuilder;
import io.restassured.response.Response;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseAPIClient {

    public <T> List<T> getAll(String endpoint, Class<T[]> responseArrayClass) {

        Response response = given()
                .spec(RequestBuilder.getDefaultRequestSpec())
                .when()
                .get(endpoint)
                .then()
                .statusCode(200)
                .extract()
                .response();

        return ResponseHandler.extractResponseAsList(response, responseArrayClass);
    }

    public <T> T getById(String endpoint, int id, Class<T> responseClass) {
        String fullEndpoint = endpoint + "/" + id;

        Response response = given()
                .spec(RequestBuilder.getDefaultRequestSpec())
                .when()
                .get(fullEndpoint)
                .then()
                .statusCode(200)
                .extract()
                .response();

        return ResponseHandler.extractResponseAsObject(response, responseClass);
    }

    public <T> List<T> getAllWithQueryParams(String endpoint, Map<String, Object> queryParams, Class<T[]> responseArrayClass) {

        Response response = given()
                .spec(RequestBuilder.getRequestWithQueryParams(queryParams))
                .when()
                .get(endpoint)
                .then()
                .statusCode(200)
                .extract()
                .response();

        return ResponseHandler.extractResponseAsList(response, responseArrayClass);
    }
}
