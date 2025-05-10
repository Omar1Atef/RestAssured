package Project;

import PojoClass.Pojo;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class RequestBodyTest
{
    @Test
    public void sendBodyAsStringTest()
    {
        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        given().baseUri("https://reqres.in/")
                .body(body)
//                .header("Content-Type","application/json")
                .contentType("application/json")
                .log().all()
                .when().post("api/users")
                .then().log().all().statusCode(201);
    }

    @Test
    public void sendBodyAsFileTest() // send file directly
    {
        File file =new File("src/test/resources/file.json");

        given().baseUri("https://reqres.in/")
                .body(file)
                .contentType("application/json")
                .log().all()
                .when().post("api/users")
                .then().log().all().statusCode(201);
    }

    @Test
    public void sendBodyAsStingFileTest() throws IOException //send file as string
    {
        String jsonBody = new String(Files.readAllBytes(Paths.get("src/test/resources/file.json")));

        given().baseUri("https://reqres.in/")
                .body(jsonBody)
                .contentType("application/json")
                .log().all()
                .when().post("api/users")
                .then().log().all().statusCode(201);
    }


    @Test
    public void sendBodyAsAttachmentTest() throws IOException //send file as attachment
    {
        File file =new File("src/test/resources/file.json");

        given().baseUri("https://reqres.in/")
                .multiPart("key",file)
                .contentType("multipart/form-data")
                .log().all()
                .when().post("api/users")
                .then().log().all().statusCode(201);
    }


    @Test
    public void sendBodyAsHashMapTest()
    {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("name","morpheus");
        hashMap.put("job","leader");

        given().baseUri("https://reqres.in/")
                .body(hashMap)
                .contentType("application/json")
                .log().all()
                .when().post("api/users")
                .then().log().all().statusCode(201);
    }

    @Test
    public void sendBodyAsPojoClassTest()
    {
        Pojo pojo = new Pojo("morpheus","leader");
//        pojo.setEmail("morpheus");
//        pojo.setPassword("leader");

        given().baseUri("https://reqres.in/")
                .body(pojo)
                .contentType("application/json")
                .log().all()
                .when().post("api/users")
                .then().log().all().statusCode(201);
    }

    @Test
    public void sendBodyAsKeyValueTest() {
        given().baseUri("https://reqres.in/")
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", "omar")
                .formParam("password", "1234")
                .formParam("grant_type", "password")
                //username=omar&password=1234&grant_type=password
                .when()
                .post("/api/token");
    }


}
