package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import pojo.AuthenticationRequest;
import utils.APIUtils;
import utils.ConfigurationReader;

public class AuthenticationStep extends APIUtils {

    AuthenticationRequest body;

    @Given("user enters email {string} and password {string} in request body")
    public void user_enters_email_and_password_in_request_body(String email, String password) {
        body = new AuthenticationRequest(email, password);
    }

    @Given("cURL")
    public void c_url() {
        request = RestAssured.given()
                .baseUri(ConfigurationReader.getProperty("baseURL"))
                .contentType(ContentType.JSON)
                .header("apikey", ConfigurationReader.getProperty("apikey"));
    }

    @Given("user gets response body")
    public void user_gets_response_body() {
        request.body(body);
    }

    @Then("verify status code is {int}")
    public void verify_status_code_is(Integer statusCode) {
        System.out.println("Status code is " + response.statusCode());
       // System.out.println("Response body is " + response.asPrettyString());
        Assertions.assertEquals(statusCode,response.statusCode());
    }

    @Then("verify status code is not {int}")
    public void verify_with_invalid_credentials(Integer statusCode) {
        Assertions.assertNotEquals(statusCode,response.statusCode());
    }

    //sign in

    @Given("query param key is {string} and value is {string}")
    public void query_param_key_is_and_value_is(String key, String value) {
        request.queryParam(key,value);
    }

    //get current user

    @Given("user has an access token")
    public void user_has_an_access_token() {
        String token = getToken();

        request = RestAssured.given()
                .baseUri(ConfigurationReader.getProperty("baseURL"))
                .header("apikey",ConfigurationReader.getProperty("apikey"))
                .header("Authorization", "Bearer " + token);
    }
    @When("user hits {string} {string}")
    public void user_hits_method_and_endpoint(String http ,String endpoint) {
        System.out.println("Request URI: " + request.log().uri());
        response = hitEndpoint(http, endpoint);
    }

    //refresh access token
    @Given("request body {string}")
    public void request_body(String refreshToken) {
        request.body(refreshToken + getRefreshToken());
    }
}

































