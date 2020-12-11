package restAPI;

import io.restassured.path.json.JsonPath;

public class ReUsableMethod {
    public static JsonPath rawToJson(String response) {
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath;
    }
}
