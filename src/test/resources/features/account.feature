Feature: account test

  Scenario: Create a account
    When Create a acount
    Then The a new account will be created

  Scenario: Get all acounts
    When ik all accounts ophaal
    Then krijg ik een list van <accounts>

  Scenario: Delete a account
    When ik een acount delete
    Then word de status closed


  Scenario: Update a minBalance
    When Update a minBalance
    Then The requested status code is 200

  Scenario: Update a maxTransfer
    When Update a maxTransfer
    Then The requested changed status code is 202

