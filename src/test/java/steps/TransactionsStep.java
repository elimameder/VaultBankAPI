package steps;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import org.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import pojo.Transactions;
import utils.APIUtils;
import utils.ConfigurationReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    @Then("verify param in response body is {string}")
    public void verify_param_in_response_body_is(String value) {

        Transactions[] transactionsData = response.as(Transactions[].class);
        System.out.println(response.asPrettyString());

        for (Transactions eachTransaction : transactionsData) {
            System.out.println(eachTransaction.getCategory());
            Assertions.assertEquals(value, eachTransaction.getCategory());
        }
    }

    @Given("user sets query params with duplicate names:")
    public void user_sets_query_params_with_duplicate_names(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        for (List<String> row : rows) {
            request.queryParam(row.get(0), row.get(1));
        }
    }

    @Given("user provides request body")
    public void user_provides_request_body() {

        Transactions transactionBody = new Transactions();
        transactionBody.setDescription("Coffee Shop");
        transactionBody.setAmount("-6.80");
        transactionBody.setCategory("Food");
        transactionBody.setType("debit");
        transactionBody.setMethod("card");
        transactionBody.setAccount_id("9a351bb7-750c-4e78-a94d-2a1463ecc389");
        transactionBody.setUser_id("d87fd373-a02d-411d-8563-c23511cf9f7c");

        request.contentType("application/json");
        request.header("Prefer", "return=representation");
        request.body(transactionBody);
        System.out.println(transactionBody);
    }

    @Given("user provides invalid request body")
    public void user_provides_invalid_request_body() {
        Transactions transactionBody = new Transactions();
        transactionBody.setDescription("Coffee Shop");
        transactionBody.setAmount("-6.80");
        transactionBody.setCategory("Food");
        transactionBody.setType("debit");
//        transactionBody.setMethod("card");
//        transactionBody.setAccount_id("a351bb7-750c-4e78-a94d-2a1463ecc389");
        transactionBody.setUser_id("d87fd373-a02d-411d-8563-c23511cf9f7c");

        request.contentType("application/json");
        request.header("Prefer", "return=representation");
        request.body(transactionBody);
        System.out.println(transactionBody);

    }
}
