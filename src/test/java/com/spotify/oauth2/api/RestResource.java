package com.spotify.oauth2.api;

import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.RouteApiConstants.API;
import static com.spotify.oauth2.api.RouteApiConstants.TOKEN;
import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {


    public static Response postMethod(Object playListRequest, String path, String token) {
        return given()
                .spec(getRequestSpec())
                .auth().oauth2(token)
                .body(playListRequest)
                .when()
                .post(path)
                .then()
                .spec(getResponseSpec())
                .extract().response();
    }


    public static Response getMethod(String path, String token) {
        return given()
                .spec(getRequestSpec())
                .auth().oauth2(token)
                .when()
                .get(path)
                .then()
                .spec(getResponseSpec())
                .extract().response();
    }

    public static Response putMethod(Object playListRequest, String path, String token) {
        return given()
                .spec(getRequestSpec())
                .auth().oauth2(token)
                .body(playListRequest)
                .when()
                .put(path)
                .then()
                .spec(getResponseSpec())
                .extract().response();
    }

    public static Response postToken(HashMap<String, String> formParams) {
        return given()
                .spec(getRequestSpecToken())
                .formParams(formParams)
                .when()
                .post(API + TOKEN)
                .then()
                .spec(getResponseSpec())
                .extract().response();
    }
}
