package org.example.Framework.core;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.example.Framework.config.EnvironmentConfig;

import java.util.Map;

public class RequestBuilder {

    public static RequestSpecification getDefaultRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(EnvironmentConfig.getBaseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getRequestWithQueryParams(Map<String, Object> queryParams) {
        RequestSpecBuilder builder = new RequestSpecBuilder()
                .setBaseUri(EnvironmentConfig.getBaseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL);

        if (queryParams != null) {
            queryParams.forEach(builder::addQueryParam);
        }

        return builder.build();
    }

}
