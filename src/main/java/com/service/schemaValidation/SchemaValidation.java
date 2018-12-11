package com.service.schemaValidation;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.service.base.Base;
import com.service.base.ExtentManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class SchemaValidation extends Base {


    ExtentTest extentTest;


    @Test
    public void validateSchema()
    {

        extentTest=reports.createTest("Validating jSON schema");
        RestAssured.baseURI="https://samples.openweathermap.org";
        RestAssured.basePath="/data/2.5/weather";

        File jsonSchema=new File("/Users/rabia/IdeaProjects/web-services-automation/src/main/java/schema.json");

        Map<String,String> headerMap=new HashMap<String,String>();
        headerMap.put("q","London,uk");
        headerMap.put("appid","b6907d289e10d714a6e88b30761fae22");

        Response response= given().when().queryParams(headerMap).get().then().extract().response();

        given().when().queryParams(headerMap).get().then().body(matchesJsonSchema(jsonSchema));
        if(response.getStatusCode()==200)
        {

            extentTest.log(Status.INFO,basePath);
            extentTest.log(Status.INFO,"REQUEST" +given().when().queryParams(headerMap).get().prettyPrint());
            extentTest.log(Status.PASS,"Pass");
            extentTest.log(Status.INFO,"Response content type " +response.getContentType());
            extentTest.log(Status.INFO,"Response " +response.prettyPrint());

        }


    }

    @AfterSuite
    public void tearDown()
    {
        reports.flush();
    }
}
