package steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import org.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import utils.APIUtils;
import utils.ConfigurationReader;

import java.util.Map;

public class TransactionsStep extends APIUtils {

    @Given("sets query params:")
    public void sets_query_params(io.cucumber.datatable.DataTable dataTable) {

        Map<String, String> params = dataTable.asMap(String.class, String.class);
        request.queryParams(params);
    }

    @Then("verify size of list equals to {int}")
    public void verify_size_of_list_equals_to(Integer limitSize) {
        JSONArray jsonArray = new JSONArray(response.getBody().asString());
        Assertions.assertEquals(limitSize, jsonArray.length());
    }

    @Given("user has invalid token")
    public void user_has_invalid_token() {
        String token = invalidToken();

        request = RestAssured.given()
                .baseUri(ConfigurationReader.getProperty("baseURL"))
                .header("apikey",ConfigurationReader.getProperty("apikey"))
                .header("Authorization", "Bearer " + token);
    }
}
