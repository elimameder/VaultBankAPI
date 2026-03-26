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
    //String token;

    @Given("user is authorized")
    public void user_is_authorized() {

       // token = getToken();

        request = RestAssured.given()
                .baseUri(ConfigurationReader.getProperty("baseURL"))
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer eyJhbGciOiJFUzI1NiIsImtpZCI6ImU2NjdhMzU3LTcwOWEtNGRkZS04MThlLTAwODM2MjUwM2NiMiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL3FtZWljcGZsdmd6cHl3dWZxeXB6LnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiJhNmM1ZWFkMi0xMTJiLTRlZDQtOTM0OC1hMTkwOGVjODc5MjYiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzc0NTA0NTIzLCJpYXQiOjE3NzQ1MDA5MjMsImVtYWlsIjoidGVzdHVzZXIxQHZhdWx0YmFuay50ZXN0IiwicGhvbmUiOiIiLCJhcHBfbWV0YWRhdGEiOnsicHJvdmlkZXIiOiJlbWFpbCIsInByb3ZpZGVycyI6WyJlbWFpbCJdfSwidXNlcl9tZXRhZGF0YSI6eyJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiZmlyc3RfbmFtZSI6IkphbWVzIiwibGFzdF9uYW1lIjoiU21pdGgifSwicm9sZSI6ImF1dGhlbnRpY2F0ZWQiLCJhYWwiOiJhYWwxIiwiYW1yIjpbeyJtZXRob2QiOiJwYXNzd29yZCIsInRpbWVzdGFtcCI6MTc3NDQ4Njg0OH1dLCJzZXNzaW9uX2lkIjoiMDM4MTFhZDMtNTU5My00YmM0LTgzNjUtYWJmNmZmY2EwYzU5IiwiaXNfYW5vbnltb3VzIjpmYWxzZX0.I4mZtX_CqIGdbRy6I2XyUcQtfCePLpoGGKnqd5SN2FafptuTKKgxeF_vbca15Bh5qqOctTE_19fM_MdCu9GPIA");



    }

    @When("user provides valid year and month")
    public void user_provides_valid_year_and_month() {
        requestBody = new GenerateStatement();
        requestBody.setYear(2025);
        requestBody.setMonth(3);


    }

    @When("user sends request to generate statement")
    public void user_sends_request_to_generate_statement() {
        response = request
                .body(requestBody)
                .post("/functions/v1/generate-statement");

    }

    @Then("status code should be {int}")
    public void status_code_should_be(Integer int1) {
        Assertions.assertEquals(200,response.statusCode());

    }


}
