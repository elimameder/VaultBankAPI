@regression
Feature: Create card

  Scenario: Create card returns 403
    Given user sets base request with token
    When user hits POST "/bank_cards"
    Then verify response status code is 403