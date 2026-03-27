package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.AuthenticationRequest;
import pojo.AuthenticationResponse;
import pojo.BankAccountRequest;
import utils.ConfigurationReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BankAccountStep {


    private String endpoint;
    private String token;
    private Response response;

    private String getToken() {
        AuthenticationRequest authBody = new AuthenticationRequest(
                ConfigurationReader.getProperty("email"),
                ConfigurationReader.getProperty("password")
        );

        Response authResponse = RestAssured.given()
                .baseUri(ConfigurationReader.getProperty("baseURL"))
                .contentType(ContentType.JSON)
                .header("apikey", ConfigurationReader.getProperty("apikey"))
                .queryParam("grant_type", "password")
                .body(authBody)
                .when()
                .post("/auth/v1/token");

        AuthenticationResponse authenticationResponse =
                authResponse.as(AuthenticationResponse.class);

        return authenticationResponse.getAccess_token();
    }

    @Given("user sets bank account endpoint")
    public void user_sets_bank_account_endpoint() {
        endpoint = ConfigurationReader.getProperty("baseURL") + "/rest/v1/bank_accounts";
        token = getToken();

        System.out.println("TOKEN: " + token);
        System.out.println("ENDPOINT: " + endpoint);
    }

    @When("user sends GET request to retrieve all bank accounts")
    public void user_sends_get_request_to_retrieve_all_bank_accounts() {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("apikey", ConfigurationReader.getProperty("apikey"))
                .header("Authorization", "Bearer " + token)
                .when()
                .get(endpoint);

        System.out.println("Status Code: " + response.getStatusCode());
        response.prettyPrint();
    }

    @Then("verify bank accounts are returned successfully")
    public void verify_bank_accounts_are_returned_successfully() {
        assertEquals(200, response.getStatusCode());
        assertNotNull(response);

        System.out.println("Response Body: " + response.getBody().asString());
    }
    @When("user sends GET request with type filter {string}")
    public void user_sends_get_request_with_type_filter(String type) {

        response = RestAssured.given()
                .header("apikey", ConfigurationReader.getProperty("apikey"))
                .header("Authorization", "Bearer " + token)
                .queryParam("type", "eq." + type)
                .when()
                .get(endpoint);

        System.out.println("Status Code: " + response.getStatusCode());
        response.prettyPrint();
    }
    @Then("verify only checking accounts are returned")
    public void verify_only_checking_accounts_are_returned() {

        assertEquals(200, response.getStatusCode());

        List<String> types = response.jsonPath().getList("type");

        for (String t : types) {
            assertEquals("checking", t);
        }
    }




}