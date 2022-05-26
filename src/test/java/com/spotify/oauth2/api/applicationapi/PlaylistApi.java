package com.spotify.oauth2.api.applicationapi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.PlayListMain;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.RouteApiConstants.*;
import static com.spotify.oauth2.api.TokenManager.getToken;
import static com.spotify.oauth2.utils.ConfigLoader.getSingletonInstance;


public class PlaylistApi {

    @Step
    public static Response postMethod(PlayListMain playListRequest) {
        return RestResource.postMethod(playListRequest, USERS + SEPARATOR + getSingletonInstance().getUser() + PLAYLISTS, getToken());
       /* return given()
                .spec(getRequestSpec())
                .header("Authorization","Bearer " + access_token)
                .body(playListRequest)
                .when()
                .post("/users/" + USER_ID + "/playlists")
                .then()
                .spec(getResponseSpec())
                .extract().response();*/
    }

    public static Response postMethod(PlayListMain playListRequest, String wrongToken) {
        return RestResource.postMethod(playListRequest, USERS + SEPARATOR + getSingletonInstance().getUser() + PLAYLISTS, wrongToken);
    }

    public static Response getMethod(String playlistId) {
        return RestResource.getMethod(PLAYLISTS + SEPARATOR + playlistId, getToken());
    }

    public static Response putMethod(PlayListMain playListRequest, String playlistId) {
        return RestResource.putMethod(playListRequest, PLAYLISTS + SEPARATOR + playlistId, getToken());
    }
}
