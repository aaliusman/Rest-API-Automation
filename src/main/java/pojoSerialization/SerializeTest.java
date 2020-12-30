package pojoSerialization;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SerializeTest {

//    @Test
//    public void testSerialization(){
//        AddPlace addPlace = new AddPlace();
//        addPlace.setAccuracy(50);
//        addPlace.setAddress("800 Trenton Rd");
//        addPlace.setLanguage("Urdu");
//        addPlace.setPhone_number("2223334444");
//        addPlace.setWebsite("www.aaaa.com");
//        addPlace.setName("Muhammad Usman");
//        List<String> list = new ArrayList<>();
//        list.add("shoe park");
//        list.add("shop");
//        addPlace.setTypes(list);
//        Location location = new Location();
//        location.setLat(-23.343);
//        location.setLng(12.2444);
//        addPlace.setLocation(location);
//
//        RestAssured.baseURI = "https://rahulshettyacademy.com";
//
//       String response =  given().log().all().queryParam("key", "qaclick123")
//                .body(addPlace)
//                .when().post("maps/api/place/add/json")
//                .then().assertThat().statusCode(200).extract().response().asString();
//
//           System.out.println(response);
//        JsonPath js = new JsonPath(response);
//        System.out.println(js.getString("place_id"));

//    }
}
