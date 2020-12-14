package restAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReadJsonFile {
    @Test
    public void addPlace() throws IOException {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //Add Place
        String response = given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(new String(Files.readAllBytes(Paths.get("/Users/musman635/Desktop/AddPlace.json"))))
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

        JsonPath jsonPath = new JsonPath(response);
        String place_id = jsonPath.getString("place_id");
    }
}
