Feature: account test

  Scenario: Create a account
    When Create a acount
    Then The request status code is 201

  Scenario: Get all acounts
    When Get all account
    Then The request status code is 202

  Scenario: Delete a account
    When Delete a acount
    Then The request status code is 202

  Scenario: update a account status
    When Update a acount status
    Then The request status code is 202

  Scenario: Update a minBalance
    When Update a minBalance
    Then The request status code is 202

  Scenario: Update a maxTransfer
    When Update a maxTransfer
    Then The request status code is 202