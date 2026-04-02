@regression
Feature: Generating monthly statement

  @GenerateStatement
  Scenario: user generates monthly statement successfully
    Given user is authorized
    When user provides valid year and month
    And user sends request to generate statement
    Then status code should be 200

  @GenerateStatementUnauthorized
  Scenario: user fails to generate monthly statement when unauthorized
    Given user is not authorized
    When user provides valid year and month
    And user sends request to generate statement
    Then status code should be 401

  @GenerateStatementInvalidInput
  Scenario: user fails to generate monthly statement with invalid input
    Given user is authorized
    When user provides invalid year and month
    And user sends request to generate statement
    Then status code should be 400






