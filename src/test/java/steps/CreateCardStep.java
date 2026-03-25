package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import utils.APIUtils;
import utils.ConfigurationReader;

public class CreateCardStep extends APIUtils {

    @Given("user sets base request with token")
    public void user_sets_base_request_with_token() {

        String token = getToken();

        request = RestAssured.given()
                .baseUri(ConfigurationReader.getProperty("baseURL") + "/rest/v1")
                .contentType(ContentType.JSON)
                .header("apikey", ConfigurationReader.getProperty("apikey"))
                .header("Authorization", "Bearer " + token);
    }

    @When("user hits POST {string}")
    public void user_hits_post(String endpoint) {

        String body = """
    {
      "type": "credit",
      "last4": "4169",
      "brand": "Mastercard",
      "expiry_date": "12/30",
      "card_holder": "Aiperi Lulu",
      "credit_limit": 1000
    }
    """;

        response = request
                .body(body)
                .post(endpoint);
    }

    @Then("verify response status code is {int}")
    public void verify_status(Integer code) {
        System.out.println("Status code: " + response.statusCode());
        Assertions.assertEquals(code, response.statusCode());
    }
}