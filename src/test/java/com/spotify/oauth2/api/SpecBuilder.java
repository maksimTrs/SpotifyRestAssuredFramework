package com.spotify.oauth2.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.spotify.oauth2.api.RouteApiConstants.BASE_PATH;

public class SpecBuilder {

    //mvn clean -DBASE_URI="https://api.spotify.com" -DTOKEN_BASE_URI="https://accounts.spotify.com" test

    public static RequestSpecification getRequestSpec() {

        return new RequestSpecBuilder()
                .setBaseUri(System.getProperty("BASE_URI"))
                //.setBaseUri("https://api.spotify.com")
                .setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }

    public static RequestSpecification getRequestSpecToken() {

        return new RequestSpecBuilder()
                .setBaseUri(System.getProperty("TOKEN_BASE_URI"))
                // .setBaseUri("https://accounts.spotify.com")
                .setContentType(ContentType.URLENC)
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();
    }

    public static ResponseSpecification getResponseSpec() {

        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                //.expectContentType(ContentType.JSON)
                .build();
    }

}
