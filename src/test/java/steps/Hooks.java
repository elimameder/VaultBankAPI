//package steps;
//
//import io.cucumber.java.Before;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import org.junit.jupiter.api.BeforeEach;
//import pojo.AuthenticationPOJO;
//import utils.ConfigurationReader;
//
//public class Hooks {
//
//    @Before("@auth")
//        public void setUp() {
//            AuthenticationPOJO body = new AuthenticationPOJO("testuser1@vaultbank.test", "Test1Pass!");
//
//            RequestSpecification request = RestAssured.given()
//                    .baseUri(ConfigurationReader.getProperty("baseURL"))
//                    .contentType(ContentType.JSON)
//                    .header("apikey", ConfigurationReader.getProperty("apiKey"))
//                    .queryParam("grant_type", "password")
//                    .body(body);
//
//            Response response = request.post("/token");
//        }
//    }

