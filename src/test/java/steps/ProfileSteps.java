package steps;

import io.cucumber.java.en.*;
import org.json.JSONObject;
import pojo.ProfileResponse;
import org.junit.jupiter.api.Assertions;
import utils.APIUtils;

public class ProfileSteps extends APIUtils {
    JSONObject requestBody = new JSONObject();

    @Given("profile exists with user ID {string}")
    public void profile_exists_with_user_id(String userId) {
        response = request
                .when()
                .get("/rest/v1/profiles?user_id=eq." + userId);
        System.out.println(response.asPrettyString());
    }
    @When("user hits GET {string}")
    public void user_hits_get(String endpoint) {
        response = request.when().get(endpoint);
    }

    @Then("the response body should contain the profile for user ID {string}")
    public void the_response_body_should_contain_the_profile_for_user_id(String expectedUserId) {

        ProfileResponse[] profiles = response.as(ProfileResponse[].class);
        String actualUserId = profiles[0].getUserId();
        Assertions.assertEquals(actualUserId, expectedUserId);
    }

    @Given("user adds {string} with value {string} in request body")
    public void user_adds_with_value_in_request_body(String key, String value) {
        requestBody.put(key, value);
        request = request.body(requestBody.toString());
    }

    @When("user hits PATCH {string}")
    public void user_hits_patch(String endpoint) {
        response = request.when().patch(endpoint);
    }

    @When("user hits PUT {string}")
    public void user_hits_put(String endpoint) {
        response = request.when().put(endpoint);
    }

}
