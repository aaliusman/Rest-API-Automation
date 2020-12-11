package restAPI;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.*;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class API_Test {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //Add Place
        String response = given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(Payload.addPlace())
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

        JsonPath jsonPath = new JsonPath(response);
        String place_id = jsonPath.getString("place_id");

//        Update Place
        given().log().all().queryParam("key", "qaclick123")
                .body("{\n" +
                        "\"place_id\":\"" + place_id + "\",\n" +
                        "\"address\":\"Philadelphia\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .header("Content-Type", "application/json")
                .when().put("maps/api/place/update/json")
                .then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

        // Get place
        String address = "Philadelphia";
        String getPlaceResponse = given().log().all().queryParams("place_id", place_id, "key", "qaclick123")
                .when().get("maps/api/place/get/json")
                .then().assertThat().statusCode(200).body("address", equalTo("Philadelphia"))
                .header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

        JsonPath js = ReUsableMethod.rawToJson(getPlaceResponse);
        String newAddress = js.getString("address");
        Assert.assertEquals(address, newAddress, "New Address Doesn't Match with Expected address");
     }
}
