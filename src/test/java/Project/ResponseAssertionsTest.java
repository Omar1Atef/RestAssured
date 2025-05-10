package Project;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ResponseAssertionsTest
{
    @Test
    public void Test()
    {
        given().baseUri("https://6714f43e690bf212c76311ca.mockapi.io/api/v1/")
                .when().get("users")
                .then().assertThat().statusCode(200)
                .header("Content-Type", equalTo("application/json; charset=utf-8"))
                .body("[4].name", not(equalTo("ahmed")))
                .body("id", hasItem("5"))
                .body("id", hasItems("1", "2", "4"))
                .body("name", not(hasItems("vini", "ramos")))
                .body("id", contains("1", "2", "3", "4", "5"));

        //first one is better as it's cleaner You don’t need to repeat assertThat() for each assertion

        given().baseUri("https://6714f43e690bf212c76311ca.mockapi.io/api/v1/")
                .when().get("users")
                .then().assertThat().statusCode(200)
                .assertThat().header("Content-Type", equalTo("application/json; charset=utf-8"))
                .assertThat().body("[4].name", not(equalTo("ahmed")))
                .assertThat().body("id", hasItem("5"))
                .assertThat().body("id", hasItems("1", "2", "4"))
                .assertThat().body("name", not(hasItems("vini", "ramos")))
                .assertThat().body("id", contains("1", "2", "3", "4", "5"))
                .assertThat().body("id", containsInAnyOrder("1", "3", "4", "2", "5"))
                .assertThat().body("country", not(empty()))
                .assertThat().body("name", hasSize(5))
                .assertThat().body("name.size()", equalTo(5))
                .assertThat().body("[4].name", startsWith("O"))
                .assertThat().body("createdAt", everyItem(startsWith("2025")))
                .assertThat().body("[4]", hasKey("name"))
                .assertThat().body("[4]", hasValue("Omar"))
                .assertThat().body("[4]", hasEntry("name", "Omar"));

    }

}
