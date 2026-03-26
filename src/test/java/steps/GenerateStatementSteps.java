package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import pojo.GenerateStatement;
import utils.APIUtils;
import utils.ConfigurationReader;



public class GenerateStatementSteps extends APIUtils {

    GenerateStatement requestBody;
    Response response;
    String token;

    @Given("user is authorized")
    public void user_is_authorized() {

       token = getToken();

        request = RestAssured.given()
                .baseUri(ConfigurationReader.getProperty("baseURL"))
                .contentType(ContentType.JSON)
                .header("apikey", ConfigurationReader.getProperty("apikey"))
                .header("Authorization", "Bearer " + token);


    }

    @Given("user is not authorized")
    public void user_is_not_authorized() {
        request = RestAssured.given()
                .baseUri(ConfigurationReader.getProperty("baseURL"))
                .contentType(ContentType.JSON)
                .header("apikey", ConfigurationReader.getProperty("apikey"))
                .header("Authorization", "Bearer " + invalidToken());


    }

    @When("user provides valid year and month")
    public void user_provides_valid_year_and_month() {
        requestBody = new GenerateStatement();
        requestBody.setYear(2025);
        requestBody.setMonth(3);
    }

    @When("user provides invalid year and month")
    public void user_provides_invalid_year_and_month() {
        requestBody = new GenerateStatement();
        requestBody.setYear(2020);
        requestBody.setMonth(13);

    }


    @When("user sends request to generate statement")
    public void user_sends_request_to_generate_statement() {
        response = request
                .body(requestBody)
                .post("/functions/v1/generate-statement");

    }

    @Then("status code should be {int}")
    public void status_code_should_be(Integer expectedStatusCode) {
        Assertions.assertEquals(expectedStatusCode,response.statusCode());

    }


}
