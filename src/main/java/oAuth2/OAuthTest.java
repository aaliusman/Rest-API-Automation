package oAuth2;

import io.restassured.path.json.JsonPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class OAuthTest {

    @Test
    public void getCode() throws InterruptedException {


//        System.setProperty("webdriver.chrome.driver", "/Users/musman635/IdeaProjects/RestAPI_Automation/src/main/java/driver/chromedriver");
//        WebDriver driver = new ChromeDriver();
//        driver.get("URL");

        // To run this code every time i have to get the new code from postman.
        String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AY0e-g7yv0krRRgGzusbfXEeM-GeJLXbmejiqghRYen8qD-KBSJqWcmJROn7TwXEaJarvw&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
        //Need only code part 4%2F0AY0e-g7IdRBIco158ER5tD6g2XC8H3Ti5TG1fl-QZ-4DbdiKXo9RsqIQfdHTEKT5eUNeRQ
        String partialCode = url.split("code=")[1];
        String code = partialCode.split("&scope")[0];

        String accessTokenResponse = given().urlEncodingEnabled(false).queryParams("code", code)
                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParams( "redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type", "authorization_code")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();

        JsonPath js = new JsonPath(accessTokenResponse);
        String accessToken = js.getString("access_token");
        System.out.println(accessToken);

        String response = given().queryParam("access_token", accessToken)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php").asString();

        System.out.println(response);
    }
}
