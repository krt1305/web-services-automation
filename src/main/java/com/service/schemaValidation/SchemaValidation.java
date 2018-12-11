package com.service.schemaValidation;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class SchemaValidation {


    @Test
    public void validateSchema()
    {

        RestAssured.baseURI="https://samples.openweathermap.org";
        RestAssured.basePath="/data/2.5/weather";

        File jsonSchema=new File("/Users/rabia/IdeaProjects/web-services-automation/src/main/java/schema.json");

        Map<String,String> headerMap=new HashMap<String,String>();
        headerMap.put("q","London,uk");
        headerMap.put("appid","b6907d289e10d714a6e88b30761fae22");

        Response response= given().when().queryParams(headerMap).get().then().extract().response();
        given().when().queryParams(headerMap).get().then().body(matchesJsonSchema(jsonSchema));
        System.out.println(response.prettyPrint());

    }
}
