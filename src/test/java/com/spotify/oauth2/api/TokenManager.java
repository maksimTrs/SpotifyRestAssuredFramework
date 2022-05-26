package com.spotify.oauth2.api;

import io.restassured.response.Response;

import java.time.Instant;
import java.util.HashMap;

import static com.spotify.oauth2.utils.ConfigLoader.getSingletonInstance;

public class TokenManager {

    private static String access_token;
    private static Instant expires_time;

    public synchronized static String getToken() {
        try {
            if (access_token == null || Instant.now().isAfter(expires_time)) {
                System.out.println("Token is updating...");
                Response response = renewToken();
                access_token = response.path("access_token");
                int expiresDurationInSeconds = response.path("expires_in");
                expires_time = Instant.now().plusSeconds(expiresDurationInSeconds - 300);
            } else {
                System.out.println("Token is up to date");
            }
        } catch (Exception exception) {
            throw new RuntimeException("Failed to get Token!");
        }
        return access_token;
    }

    public static Response renewToken() {
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("client_id", getSingletonInstance().getClientId());
        formParams.put("client_secret", getSingletonInstance().getClientSecret());
        formParams.put("refresh_token", getSingletonInstance().getRefreshToken());
        formParams.put("grant_type", getSingletonInstance().getGrantType());

        Response response = RestResource.postToken(formParams);

        if (response.statusCode() != 200) {
            throw new RuntimeException("ALERT!!! refresh_token value is failed!");
        }
        return response;
    }
}
