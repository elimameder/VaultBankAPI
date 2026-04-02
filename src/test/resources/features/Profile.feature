Feature: user can manipulate profile information

  @profileFeatures @successfulProfileFeatures
  Scenario: user can get the profile info by ID
    Given user is authorized
    Given profile exists with user ID "a6c5ead2-112b-4ed4-9348-a1908ec87926"
    When user hits GET "/rest/v1/profiles?user_id=eq.a6c5ead2-112b-4ed4-9348-a1908ec87926"
    Then verify status code is 200
    And the response body should contain the profile for user ID "a6c5ead2-112b-4ed4-9348-a1908ec87926"

  @profileFeatures @unsuccessfulProfileFeatures
  Scenario: unauthorized user failed to get the profile info
    Given user is not authorized
    Given profile exists with user ID "a6c5ead2-112b-4ed4-9348-a1908ec87926"
    When user hits GET "/rest/v1/profiles?user_id=eq.a6c5ead2-112b-4ed4-9348-a1908ec87926"
    Then verify status code is 401

  @profileFeatures @unsuccessfulProfileFeatures
  Scenario: user cannot get the profile info by invalid ID
    Given user is authorized
    Given profile exists with user ID "NOT-VALID-ID"
    When user hits GET "/rest/v1/profiles?user_id=eq.NOT-VALID-ID"
    Then verify status code is 400

  @profileFeatures @successfulProfileFeatures
  Scenario: user can partially update the profile info by ID
    Given user is authorized
    Given profile exists with user ID "a6c5ead2-112b-4ed4-9348-a1908ec87926"
    And user adds "phone" with value "+15551234568" in request body
    And user adds "city" with value "Chicago" in request body
    And user adds "state" with value "IL" in request body
    When user hits PATCH "/rest/v1/profiles?user_id=eq.a6c5ead2-112b-4ed4-9348-a1908ec87926"
    Then verify status code is 204

  @profileFeatures @unsuccessfulProfileFeatures
  Scenario: unauthorized user cannot partially update the profile info by ID
    Given user is not authorized
    Given profile exists with user ID "a6c5ead2-112b-4ed4-9348-a1908ec87926"
    And user adds "phone" with value "+15551234568" in request body
    And user adds "city" with value "Chicago" in request body
    And user adds "state" with value "IL" in request body
    When user hits PATCH "/rest/v1/profiles?user_id=eq.a6c5ead2-112b-4ed4-9348-a1908ec87926"
    Then verify status code is 401

  @profileFeatures @unsuccessfulProfileFeatures
  Scenario: user cannot partially update the profile info by invalid ID
    Given user is authorized
    Given profile exists with user ID "NOT-VALID"
    And user adds "phone" with value "+15551234568" in request body
    And user adds "city" with value "Chicago" in request body
    And user adds "state" with value "IL" in request body
    When user hits PATCH "/rest/v1/profiles?user_id=eq.NOT-VALID"
    Then verify status code is 400

  @profileFeatures @successfulProfileFeatures
  Scenario: user can fully update the profile info by ID
    Given user is authorized
    Given profile exists with user ID "a6c5ead2-112b-4ed4-9348-a1908ec87926"
    And user adds "first_name" with value "James" in request body
    And user adds "last_name" with value "Smith" in request body
    And user adds "phone" with value "+15551234568" in request body
    And user adds "address_line1" with value "123 Wall St" in request body
    And user adds "city" with value "Chicago" in request body
    And user adds "state" with value "IL" in request body
    And user adds "zip_code" with value "60789" in request body
    And user adds "preferred_language" with value "en" in request body
    And user adds "financial_tips" with value "true" in request body
    And user adds "product_updates" with value "true" in request body
    When user hits PUT "/rest/v1/profiles?user_id=eq.a6c5ead2-112b-4ed4-9348-a1908ec87926"
    Then verify status code is 405

  @profileFeatures @successfulProfileFeatures
  Scenario: user can change toggle notifications preferences the profile info by ID
    Given user is authorized
    Given profile exists with user ID "a6c5ead2-112b-4ed4-9348-a1908ec87926"
    And user adds "financial_tips" with value "true" in request body
    And user adds "product_updates" with value "false" in request body
    And user adds "promo_offers" with value "false" in request body
    When user hits PATCH "/rest/v1/profiles?user_id=eq.a6c5ead2-112b-4ed4-9348-a1908ec87926"
    Then verify status code is 200

  @profileFeatures @unsuccessfulProfileFeatures
  Scenario: unauthorized user cannot change toggle notifications preferences the profile info by ID
    Given user is not authorized
    Given profile exists with user ID "a6c5ead2-112b-4ed4-9348-a1908ec87926"
    And user adds "financial_tips" with value "true" in request body
    And user adds "product_updates" with value "false" in request body
    And user adds "promo_offers" with value "false" in request body
    When user hits PATCH "/rest/v1/profiles?user_id=eq.a6c5ead2-112b-4ed4-9348-a1908ec87926"
    Then verify status code is 401

