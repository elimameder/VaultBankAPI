package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.AuthenticationRequest;
import pojo.AuthenticationResponse;

public class APIUtils {

     protected static RequestSpecification request;
     protected static Response response;

    public String getToken() {
        AuthenticationRequest body = new AuthenticationRequest(
                ConfigurationReader.getProperty("email"),
                ConfigurationReader.getProperty("password")
        );

        request = RestAssured.given()
                .baseUri(ConfigurationReader.getProperty("baseURL"))
                .contentType(ContentType.JSON)
                .header("apikey", ConfigurationReader.getProperty("apikey"))
                .queryParam("grant_type", "password")
                .body(body);

        response = request.post("/auth/v1/token");

        AuthenticationResponse authResponse = response.as(AuthenticationResponse.class);

        return authResponse.getAccess_token();
    }

    public String invalidToken(){
        String invalidToken;
        invalidToken = "invalid" + getToken();
        return invalidToken;
    }


    public String getRefreshToken() {
        AuthenticationRequest body = new AuthenticationRequest("testuser1@vaultbank.test", "Test1Pass!");

        request = RestAssured.given()
                .baseUri(ConfigurationReader.getProperty("baseURL"))
                .contentType(ContentType.JSON)
                .header("apikey", ConfigurationReader.getProperty("apikey"))
                .queryParam("grant_type", "password")
                .body(body);

        response = request.post("/auth/v1/token");
        AuthenticationResponse authresponse = response.as(AuthenticationResponse.class);

        return authresponse.getRefresh_token();
    }


    public Response hitEndpoint(String http,String endPoint){

        switch (http.toUpperCase()){
            case "GET":
                response = request.get(endPoint);
                return response;
            case "POST":
                response = request.post(endPoint);
                return response;
            case "PUT":
                response = request.put(endPoint);
                return response;
            case "PATCH":
                response = request.patch(endPoint);
                return response;
            case "DELETE":
                response = request.delete(endPoint);
                return response;
            default:
                throw new IllegalArgumentException("Invalid HTTP method: " + http);       }
    }
}
