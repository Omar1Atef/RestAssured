package Project;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class RequestHeaderTest
{

    @Test
    public void headerMethodTest()
    {
        given().baseUri("https://todo.qacart.com/")
                .header("type","WEB")
                .header("language","JAVA")
                .when().get("api/v1/info/courses")
                .then().log().all().statusCode(200);
    }

    @Test
    public void headersMethodTest()
    {
        given().baseUri("https://todo.qacart.com/")
                .headers("type","WEB","language","JAVA")
                .when().get("api/v1/info/courses")
                .then().log().all().statusCode(200);
    }

    @Test
    public void headerClassTest()
    {
        Header typeHeader = new Header("type","WEB");
        Header languageHeader = new Header("language","JAVA");

        given().baseUri("https://todo.qacart.com/")
                .header(typeHeader)
                .header(languageHeader)
                .when().get("api/v1/info/courses")
                .then().log().all().statusCode(200);
    }

    @Test
    public void headersClassTest()
    {
        Header typeHeader = new Header("type","WEB");
        Header languageHeader = new Header("language","JAVA");

        Headers headers = new Headers(typeHeader,languageHeader);

        given().baseUri("https://todo.qacart.com/")
                .headers(headers)
                .when().get("api/v1/info/courses")
                .then().log().all().statusCode(200);
    }

    @Test
    public void headersAsHashMapTest()
    {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("type","WEB");
        hashMap.put("language","JAVA");

        given().baseUri("https://todo.qacart.com/")
                .headers(hashMap)
                .when().get("api/v1/info/courses")
                .then().log().all().statusCode(200);
    }


}
