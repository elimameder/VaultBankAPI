@regression
Feature: Bank Account API

  @userCanGetBankAccountsSuccessfully
  Scenario: User gets all bank accounts successfully
    Given user sets bank account endpoint
    When user sends GET request to retrieve all bank accounts
    Then verify bank accounts are returned successfully


@userCanGetBankAccountsWithTypeFilter
  Scenario: User gets checking accounts filtered by type
    Given user sets bank account endpoint
    When user sends GET request with type filter "checking"
    Then verify only checking accounts are returned