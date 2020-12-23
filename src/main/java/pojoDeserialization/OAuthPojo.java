package pojoDeserialization;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class OAuthPojo {
    @Test
    public void getCode() throws InterruptedException {

        // To run this code every time i have to get the new code from postman.
        String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AY0e-g69qW8y4DZNXmnmpxHmQYM_9TvKlSTHusQUUu86nGlEVpkRbpdn5SpGzLWDdGywcg&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
        //Need only code part 4%2F0AY0e-g7IdRBIco158ER5tD6g2XC8H3Ti5TG1fl-QZ-4DbdiKXo9RsqIQfdHTEKT5eUNeRQ
        String partialCode = url.split("code=")[1];
        String code = partialCode.split("&scope")[0];

        String accessTokenResponse = given().urlEncodingEnabled(false).queryParams("code", code)
                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type", "authorization_code")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();

        JsonPath js = new JsonPath(accessTokenResponse);
        String accessToken = js.getString("access_token");
        System.out.println(accessToken);

        GetCourse gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);

        gc.getLinkedIn();
        System.out.println("LinkedIn URL: " + gc.getLinkedIn());
        System.out.println("Instructor: " + gc.getInstructor());

        int apiCourseSize = gc.getCourses().getApi().size();
        int mobileCourseSize = gc.getCourses().getMobile().size();
        int webAutoCourseSize = gc.getCourses().getWebAutomation().size();

        System.out.println("~~~~~~~~Api Courses~~~~~~~~");
        for (int i = 0; i < apiCourseSize; i++) {
            System.out.println(gc.getCourses().getApi().get(i).getCourseTitle());
            System.out.println(gc.getCourses().getApi().get(i).getPrice());
        }

        System.out.println("~~~~~~~~Mobile Automation Courses~~~~~~~~");
        for (int i = 0; i < mobileCourseSize; i++) {
            System.out.println(gc.getCourses().getMobile().get(i).getCourseTitle());
            System.out.println(gc.getCourses().getMobile().get(i).getPrice());
        }

        System.out.println("~~~~~~~~Web Automation Courses~~~~~~~~");
        for (int i = 0; i < webAutoCourseSize; i++) {
            System.out.println(gc.getCourses().getWebAutomation().get(i).getCourseTitle());
            System.out.println(gc.getCourses().getWebAutomation().get(i).getPrice());
        }
    }
}
