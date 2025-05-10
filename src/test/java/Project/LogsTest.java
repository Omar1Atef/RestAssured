package Project;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LogsTest
{

    @Test
    public void logFullRequestTest()
    {
        given().log().all()
                .baseUri("https://6714f43e690bf212c76311ca.mockapi.io/api/v1/")
                .when().get("users")
                .then();
    }

    @Test
    public void logSpecificRequestTest()
    {
        given().baseUri("https://6714f43e690bf212c76311ca.mockapi.io/api/v1/").log().headers()
                .when().get("users")
                .then();
    }

    @Test
    public void logAllResponseTest()
    {
        given().baseUri("https://6714f43e690bf212c76311ca.mockapi.io/api/v1/")
                .when().get("users")
                .then().log().all();
    }

    @Test
    public void specificResponseTest()
    {
        given().baseUri("https://6714f43e690bf212c76311ca.mockapi.io/api/v1/")
                .when().get("users")
                .then().log().body();
    }

    @Test
    public void logIfErrorTest()
    {
        given().baseUri("https://6714f43e690bf212c76311ca.mockapi.io/api/v1/")
                .when().get("users")
                .then().log().ifError();
    }

    @Test
    public void logIfValidationTest()
    {
        given().baseUri("https://6714f43e690bf212c76311ca.mockapi.io/api/v1/")
                .when().get("users")
                .then().log().ifValidationFails()
//                .statusCode(200)
                .body("[4].name",equalTo("Omar"));
    }

}
