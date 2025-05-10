package Project;

import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class ResponseExtractTest
{
    //Extract fULL Response (status code,body as json,headers,cookies)
    @Test
    public void extractFullResponseTest()
    {
        Response response = given().baseUri("https://6714f43e690bf212c76311ca.mockapi.io/")
                .when().get("api/v1/users")
                .then().extract().response();

        System.out.println(response.asString());
    }

    @Test
    public void extractResponseAsStringTest()
    {
        String response = given().baseUri("https://6714f43e690bf212c76311ca.mockapi.io/")
                .when().get("api/v1/users")
                .then().extract().response().asString();

        System.out.println(response);
    }


    //Extract fULL Body Response
    @Test
    public void extractFullBodyResponseTest()
    {
        Response response = given().baseUri("https://6714f43e690bf212c76311ca.mockapi.io/")
                .when().get("api/v1/users")
                .then().log().all().extract().response();

        System.out.println(response.getBody().asString());
    }

    //Extract a Specific Field from the JSON Response in single Object or  If JSON is an array
    @Test
    public void extractSpecificItemInResponseUsingJsonTest()
    {
        Response response = given().baseUri("https://6714f43e690bf212c76311ca.mockapi.io/")
                .when().get("api/v1/users")
                .then().extract().response();

//        String name = response.jsonPath().getString("name");
        String name = response.jsonPath().getString("[4].name");
        System.out.println("Extracted Name: " + name);
    }

    //Extract Response If JSON is an array (Response is an array And you need to extract a specific item by index)
    @Test
    public void extractSpecificItemInResponse1Test() //extract single field only
    {
        Response response = given().baseUri("https://6714f43e690bf212c76311ca.mockapi.io/")
                .when().get("api/v1/users")
                .then().extract().response();

        String name = response.path("[4].name"); //cannot extract "name"
        System.out.println(name);

    }

    //Extract a Specific Item inside Field (key) from the JSON Response
    @Test
    public void extractSpecificItemInResponseTest()
    {
        String name = given().baseUri("https://6714f43e690bf212c76311ca.mockapi.io/")
                .when().get("api/v1/users")
                .then().extract().path("[4].name");

        System.out.println(name);
    }

    //Extract Status Code only
    @Test
    public void extractResponseCodeTest()
    {
        Response response = given().baseUri("https://6714f43e690bf212c76311ca.mockapi.io/api/v1/")
                .when().get("users")
                .then().log().all().extract().response();

        int responseCode = response.statusCode();
        System.out.println(responseCode);
    }


//    @Test
//    public void extractResponseCodeTest()
//    {
//        int responseCode = given().baseUri("https://6714f43e690bf212c76311ca.mockapi.io/api/v1/")
//                .when().get("users")
//                .then().extract().statusCode();
//
//        System.out.println(responseCode);
//    }

    //Extract All Headers From Response
    @Test
    public void extractResponseAllHeadersTest()
    {
        Response response = given().baseUri("https://6714f43e690bf212c76311ca.mockapi.io/api/v1/")
                .when().get("users")
                .then().log().all().extract().response();

        Headers headers = response.getHeaders();
        System.out.println(headers);
    }


    //Extract Response Time Only
    @Test
    public void extractResponseTimeTest()
    {
        long responseTime = given().baseUri("https://6714f43e690bf212c76311ca.mockapi.io/api/v1/")
                .when().get("users")
                .then().extract().time();

        System.out.println(responseTime);
    }

    //Extract All Headers From Response
    @Test
    public void extractResponseSpecificHeadersTest()
    {
        Response response = given().baseUri("https://6714f43e690bf212c76311ca.mockapi.io/api/v1/")
                .when().get("users")
                .then().log().all().extract().response();

        String contentType = response.getHeader("Content-Type");
        System.out.println(contentType);
    }

    //Extract Cookies From Response
    @Test
    public void extractResponseCookiesTest()
    {
        Response response = given().baseUri("https://6714f43e690bf212c76311ca.mockapi.io/api/v1/")
                .when().get("users")
                .then().log().all().extract().response();

        Map<String, String> cookies = response.getCookies();
        System.out.println("All Cookies: " + cookies);

    }

    @Test
    public void extractSpecificResponseCookiesTest()
    {
        Response response = given().baseUri("https://6714f43e690bf212c76311ca.mockapi.io/api/v1/")
                .when().get("users")
                .then().log().all().extract().response();


        String sessionCookie = response.getCookie("JSESSIONID");
        System.out.println(sessionCookie);
    }

}
