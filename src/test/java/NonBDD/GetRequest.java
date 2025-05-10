package NonBDD;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequest
{
    public static void main(String[] args)
    {
        // Set Base URI
        RestAssured.baseURI = "https://reqres.in/api";

        // Create Request Specification
        RequestSpecification request = RestAssured.given();
        request.queryParam("page", "2");

        // Send GET Request to /users
        Response response = request.when().get("/users");
//        Response response = request.get("/users"); //with or without when() both correct

        // Print Pretty Response
        response.prettyPrint();

        // Validate Status Code (Optional)
        int statusCode = response.getStatusCode();
        System.out.println("Status Code: " + statusCode);
    }
}