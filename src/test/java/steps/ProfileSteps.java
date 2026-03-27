package steps;

import io.cucumber.java.en.*;
import pojo.ProfileResponse;
import org.junit.jupiter.api.Assertions;
import utils.APIUtils;

public class ProfileSteps extends APIUtils {
    @Given("profile exists with user ID {string}")
    public void profile_exists_with_user_id(String userId) {
        response = request
                .when()
                .get("/rest/v1/profiles?user_id=eq." + userId);

        response.then()
                .statusCode(200);
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

}
