package com.spotify.oauth2.tests;

import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {

    @BeforeMethod
    public void beforeMethod(Method m) {
        System.out.println("STARTING TEST: " + m.getName());
        System.out.println("THREAD ID: " + Thread.currentThread().getId());
    }

    //mvn clean -DBASE_URI="https://api.spotify.com" -DTOKEN_BASE_URI="https://accounts.spotify.com" test
}
