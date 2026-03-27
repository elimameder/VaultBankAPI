@regression
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
  Scenario: verify user can not get list of all transactions if requested range not satisfiable
    Given user has an access token
    And sets query params:
      | limit  | 5               |
      | type   | eq.debit        |
      | order  | created_at.desc |
      | offset | 999             |
    When user hits "GET" "rest/v1/transactions"
    Then verify status code is 416
    #failed, bug must be fixed. because its returning status code 200


  @PositiveScenario
  Scenario: verify user can get transactions for a specific account
    Given user has an access token
    And sets query params:
      | user_id  | eq.d87fd373-a02d-411d-8563-c23511cf9f7c |
      | limit    | 3                                       |
      | category | eq.Food                                 |
    When user hits "GET" "rest/v1/transactions"
    Then verify status code is 200
    Then verify param in response body is "Food"


  @NegativeScenario
  Scenario: verify user with invalid token can not get transactions for a specific account
    Given user has invalid token
    And sets query params:
      | user_id  | eq.d87fd373-a02d-411d-8563-c23511cf9f7c |
      | limit    | 3                                       |
      | category | eq.Food                                 |
    When user hits "GET" "rest/v1/transactions"
    Then verify status code is 401

  @PositiveScenario
  Scenario: verify user can get transactions by date range
    Given user has an access token
    And user sets query params with duplicate names:
      | date  | gte.2025-03-01 |
      | date  | lt.2026-12-12  |
      | limit | 3              |
    When user hits "GET" "rest/v1/transactions"
    Then verify status code is 200


  @NegativeScenario
  Scenario: verify user can not get transactions with invalid date format
    Given user has an access token
    And user sets query params with duplicate names:
      | date  | gte.2025-03-01 |
      | date  | lt.0000-12-12  |
      | limit | 3              |
    When user hits "GET" "rest/v1/transactions"
    Then verify status code is 400

  @NegativeScenario
  Scenario: verify user with invalid can not get transactions by date range
    Given user has invalid token
    And user sets query params with duplicate names:
      | date  | gte.2025-03-01 |
      | date  | lt.2026-12-12  |
      | limit | 3              |
    When user hits "GET" "rest/v1/transactions"
    Then verify status code is 401


  @PositiveScenario
  Scenario: verify user can create a new transaction
    Given user has an access token
    And user provides request body
    When user hits "POST" "rest/v1/transactions"
    Then verify status code is 201


  @NegativeScenario
  Scenario: verify user can not create a new transaction with invalid fields
    Given user has an access token
    And user provides invalid request body
    When user hits "POST" "rest/v1/transactions"
    Then verify status code is 400

  @NegativeScenario
  Scenario: verify user can not create a new transaction with invalid account_id
    Given user has an access token
    And user provides invalid request body
    When user hits "POST" "rest/v1/transactions"
    Then verify status code is 409
    #failed, bug must be fixed. because its returning 400 (as bad request) instead of 409

