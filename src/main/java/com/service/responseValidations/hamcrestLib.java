package com.service.responseValidations;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public class hamcrestLib {

    @Test
    public void validateResponse() {

        //http://hamcrest.org/JavaHamcrest/tutorial
        RestAssured.baseURI = "https://samples.openweathermap.org";
        RestAssured.basePath = "/data/2.5/weather";

        Map<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("q", "London,uk");
        headerMap.put("appid", "b6907d289e10d714a6e88b30761fae22");

        //Verify base field is equal to stations (string)

        given().when().queryParams(headerMap).get().then().body("base",equalTo("stations"));


        //Verify integer value

        given().when().queryParams(headerMap).get().then().body("id",equalTo(2643743));

        given().when().queryParams(headerMap).get().then().body("weather[0].id",greaterThan(200));

        given().when().queryParams(headerMap).get().then().body("name",equalToIgnoringCase("london"));

        //check if collections (Ex: lists )have particular fields

        given().when().queryParams(headerMap).get().then().body("weather.main",hasItem("Drizzle"));

      //check if map is present

      //  given().when().queryParams(headerMap).get().then().body("weather[0].main",hasEntry("Drizzle"));

        //check if key is present - hasKey

        Response response = given().when().queryParams(headerMap).get().then().extract().response();
        System.out.println(response.prettyPrint());


    }
}
