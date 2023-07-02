package org.example;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    private static final String BASE_URL = "https://dummy.restapiexample.com/api/v1/";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }
}