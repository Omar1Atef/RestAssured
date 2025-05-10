package NonBDD;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequest
{
    public static void main(String[] args)
    {
        String data = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        RestAssured.baseURI = "https://reqres.in/";

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type","application/json");
        request.body(data);

        Response response = request.post("api/users");
        response.prettyPrint();

    }
}
