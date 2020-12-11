package restAPI;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ComplexJsonParse {
    @Test
    public void complexJSONParse() {
        JsonPath js = new JsonPath(Payload.coursePrice());

        //by default we can use generic js.get method
        //Print Number of Courses
        int countCourses = js.getInt("courses.size()");
        System.out.println(countCourses);

        //Purchase Amount
        int purchasedAmount = js.get("dashboard.purchaseAmount");
        System.out.println(purchasedAmount);

        //Print title of first course
       String firstCourseTitle = js.getString("courses[0].title");
        System.out.println(firstCourseTitle);

        //Print all course title and prices

        for (int i=0; i<countCourses; i++){
            String titleOfCourse = js.getString("courses["+i+"].title");
            int priceOfCourse = js.get("courses["+i+"].price");
            System.out.println("Title of Course: "+ titleOfCourse+ " Price = $"+priceOfCourse);


            if (titleOfCourse.equalsIgnoreCase("RPA")) {
                int numberOfCopiesSoldByRPA = js.getInt("courses[" + i + "].copies");
                System.out.println("Copies sold by RPA: " + numberOfCopiesSoldByRPA);
            }
        }

        int actualPurchasedAmount = 0;
        for (int i=0; i<countCourses; i++){
            actualPurchasedAmount = actualPurchasedAmount + (js.getInt("courses["+i+"].price") * js.getInt("courses["+i+"].copies"));
        }
        System.out.println(actualPurchasedAmount);
        Assert.assertEquals(purchasedAmount, actualPurchasedAmount, "Actual purchased price doesn't match with Expected purchased price");
    }
}
