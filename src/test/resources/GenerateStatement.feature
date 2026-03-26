Feature: Generating monthly statement

  Scenario: user generates monthly statement successfully
    Given user is authorized
    When user provides valid year and month
    And user sends request to generate statement
    Then status code should be 200
