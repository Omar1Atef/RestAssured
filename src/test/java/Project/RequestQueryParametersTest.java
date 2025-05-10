package Project;


import org.testng.annotations.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class RequestQueryParametersTest
{
    @Test
    public void queryParamMethodTest()
    {
        given().baseUri("https://todo.qacart.com/")
                .queryParam("type", "PAID")
                .queryParam("mode", "VIDEO")
                .when().get("api/v1/info/lectures")
                .then().log().all().assertThat()
                .statusCode(200)
                .body("count",equalTo(4));
    }

    @Test
    public void queryParamsMethodTest()
    {
        given().baseUri("https://todo.qacart.com/")
                .queryParams("type", "PAID","mode", "VIDEO")
                .when().get("api/v1/info/lectures")
                .then().log().all().assertThat()
                .statusCode(200)
                .body("count",equalTo(4));
    }

    @Test
    public void queryParamsAsHashMapTest()
    {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("type","PAID");
        hashMap.put("mode","VIDEO");

        given().baseUri("https://todo.qacart.com/")
                .queryParams(hashMap)
                .when().get("api/v1/info/lectures")
                .then().log().all().assertThat()
                .statusCode(200)
                .body("count",equalTo(4));
    }
}