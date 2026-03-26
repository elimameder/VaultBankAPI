Feature: transactions requests

  @PositiveScenario
  Scenario: verify user can get list of all transactions
    Given user has an access token
    And sets query params:
      | limit | 5               |
      | type  | eq.debit        |
      | order | created_at.desc |
    When user hits "GET" "rest/v1/transactions"
    Then verify status code is 200
    Then verify size of list equals to 5

  @NegativeScenario
  Scenario: verify user with invalid token can not get list of transactions
    Given user has invalid token
    And sets query params:
      | limit | 5               |
      | type  | eq.debit        |
      | order | created_at.desc |
    When user hits "GET" "rest/v1/transactions"
    Then verify status code is 401

  @NegativeScenario
  Scenario: verify user can get list of all transactions
    Given user has an access token
    And sets query params:
      | limit  | 5               |
      | type   | eq.debit        |
      | order  | created_at.desc |
      | offset | 999             |
    When user hits "GET" "rest/v1/transactions"
    Then verify status code is 416
    #failed, bug must be fixed