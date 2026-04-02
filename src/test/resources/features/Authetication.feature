@regression
Feature: verify user's authentication

  Scenario Outline: sign up with valid credentials
    Given user enters email "<email>" and password "<password>" in request body
    And cURL
    And user gets response body
    When user hits "POST" "/auth/v1/signup"
    Then verify status code is 200
    Examples:
      | email                 | password       | #
      | newuser11@example.com | SecurePass125! | valid
      | a@example.com         | 123456         | bug -> no min chars for username


  Scenario Outline: sign up with invalid credentials
    Given user enters email "<email>" and password "<password>" in request body
    And cURL
    And user gets response body
    When user hits "POST" "/auth/v1/signup"
    Then verify status code is not 200
    Examples:
      | email         | password       |
      | abc           | SecurePass125! |
      | a@example.com | 123            |
      | a@example.com |                |
      |               | 123            |
      |               |                |

  Scenario Outline: sign in with valid credentials
    Given user enters email "<email>" and password "<password>" in request body
    And cURL
    And user gets response body
    And query param key is "grant_type" and value is "password"
    When user hits "POST" "/auth/v1/token"
    Then verify status code is 200
    Examples:
      | email                    | password   |
      | testuser1@vaultbank.test | Test1Pass! |
      | testuser2@vaultbank.test | Test2Pass! |

  Scenario: Get current user
    Given user has an access token
    When user hits "GET" "/auth/v1/user"
    Then verify status code is 200


  Scenario: Sign out
    Given user has an access token
    When user hits "POST" "/auth/v1/logout"
    Then verify status code is 204

  Scenario: Refresh access token
    Given user has an access token
    And request body "refresh_token"
    When user hits "POST" "/auth/v1/token?grant_type=refresh_token"
    Then verify status code is 200
























