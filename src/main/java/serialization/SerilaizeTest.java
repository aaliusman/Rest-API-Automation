package serialization;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SerilaizeTest {

    @Test
    public void testSerialize(){
        AddAddress ad = new AddAddress();
        ad.setAccuracy(50);
        ad.setName("Ali Usman");
        ad.setLanguage("Urdu");
        ad.setAddress("800 trenton rd");
        ad.setWebsite("https://google.com");
        ad.setPhone_number("215-111-2222");
        List list = new ArrayList();
        list.add("shoe park");
        list.add("shop");
        ad.setTypes(list);
        Location location = new Location();
        location.setLat(-234.433);
        location.setLng(32.4333);
        ad.setLocation(location);

       RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response =  given().body(ad).queryParam("key", "qaclick123")
                .when().put("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response);
        System.out.println(js.getString("place_id"));
    }
}
