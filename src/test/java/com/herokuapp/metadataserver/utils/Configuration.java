package com.herokuapp.metadataserver.utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Configuration {
    static final String URL = System.getProperty("url", "http://metadata-server-mock.herokuapp.com");

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
    public static RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(URL)
                .build()
                .with()
                .contentType(ContentType.JSON);
    }
}

