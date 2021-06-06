Feature: account test

  Scenario: Create a account
    When Create a account
    Then is the status of the request 201


  Scenario: Get all accounts
    When i get all accounts
    Then is the status of the request 200

  Scenario: Get all accounts gives an ArrayOfAccount object
    When Get all accounts
    Then The response is an "ArrayOfAccount" object

  Scenario: Delete a account
    When i do a status change to closed on account with iban "NL00INHO0000000002"
    Then is the status of the request 200


  Scenario: Update a minBalance
    When Update a minBalance van accounts "NL00INHO0000000002"
    Then is the status of the request 200

  Scenario: Update a maxTransfer
    When Update a maxTransfer of account with iban "NL00INHO0000000002"
    Then is the status of the request 200



