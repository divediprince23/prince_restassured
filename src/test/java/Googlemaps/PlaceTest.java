package Googlemaps;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
public class PlaceTest {

    @Test
    public void Addplace(){

        RestAssured.baseURI="https://rahulshettyacademy.com";

        Response response = given().log().all().queryParam("key"," qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                        "    \"location\": {\n" +
                        "        \"lat\": -38.383494,\n" +
                        "        \"lng\": 33.427362\n" +
                        "    },\n" +
                        "    \"accuracy\": 50,\n" +
                        "    \"name\": \"prince house\",\n" +
                        "    \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "    \"address\": \"29, side layout, cohen 09\",\n" +
                        "    \"types\": [\n" +
                        "        \"shoe park\",\n" +
                        "        \"shop\"\n" +
                        "    ],\n" +
                        "    \"website\": \"http://google.com\",\n" +
                        "    \"language\": \"French-IN\"\n" +
                        "}")
                .when().post("/maps/api/place/add/json")
                .then().log().all().statusCode(200).extract().response();

        Assert.assertEquals(response.statusCode(),200);

        String id = response.asString();

        JsonPath js = new JsonPath(id);
        String place_id = js.get("place_id");
        System.out.println("place_id=" +place_id);


    }
}
