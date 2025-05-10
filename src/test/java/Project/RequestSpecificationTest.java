package Project;

import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RequestSpecificationTest
{
    RequestSpecification requestSpecification;

    @BeforeMethod
    public void beforeClass()
    {
        requestSpecification = given().baseUri("https://todo.qacart.com/")
                .header("type","WEB")
                .header("language","JAVA");
    }
    @Test
    public void Test()
    {
        given().spec(requestSpecification)
                .when().get("api/v1/info/courses")
                .then().log().all().statusCode(200);
    }

}
