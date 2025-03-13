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

    public <T, R> R create(String endpoint, T requestBody, Class<R> responseClass) {

        Response response = given()
                .spec(RequestBuilder.getPostRequest(requestBody))
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .statusCode(201)  // Assuming 201 Created for successful POST
                .extract()
                .response();

        return ResponseHandler.extractResponseAsObject(response, responseClass);
    }

    public <T, R> R createWithQueryParams(String endpoint, T requestBody, Map<String, Object> queryParams, Class<R> responseClass) {

        Response response = given()
                .spec(RequestBuilder.getPostRequestWithQueryParams(queryParams , requestBody))
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .statusCode(201)  // Assuming 201 Created for successful POST
                .extract()
                .response();

        return ResponseHandler.extractResponseAsObject(response, responseClass);
    }

    public <T, R> R update(String endpoint, T requestBody, Class<R> responseClass) {

        Response response = given()
                .spec(RequestBuilder.getPutRequest(requestBody))
                .body(requestBody)
                .when()
                .put(endpoint)
                .then()
                .statusCode(200)  // Assuming 200 OK for successful PUT
                .extract()
                .response();

        return ResponseHandler.extractResponseAsObject(response, responseClass);
    }

    public <T, R> R updateWithQueryParams(String endpoint, T requestBody, Map<String, Object> queryParams, Class<R> responseClass) {

        Response response = given()
                .spec(RequestBuilder.getPutRequestWithQueryParams(queryParams, requestBody))
                .body(requestBody)
                .when()
                .put(endpoint)
                .then()
                .statusCode(200)  // Assuming 200 OK for successful PUT
                .extract()
                .response();

        return ResponseHandler.extractResponseAsObject(response, responseClass);
    }

    public <T, R> R partialUpdate(String endpoint, T requestBody, Class<R> responseClass) {

        Response response = given()
                .spec(RequestBuilder.getPatchRequest(requestBody))
                .body(requestBody)
                .when()
                .patch(endpoint)
                .then()
                .statusCode(200)  // Assuming 200 OK for successful PATCH
                .extract()
                .response();

        return ResponseHandler.extractResponseAsObject(response, responseClass);
    }

    public <T, R> R partialUpdateWithQueryParams(String endpoint, T requestBody, Map<String, Object> queryParams, Class<R> responseClass) {

        Response response = given()
                .spec(RequestBuilder.getPatchRequestWithQueryParams(queryParams, requestBody))
                .body(requestBody)
                .when()
                .patch(endpoint)
                .then()
                .statusCode(200)  // Assuming 200 OK for successful PATCH
                .extract()
                .response();

        return ResponseHandler.extractResponseAsObject(response, responseClass);
    }

}
