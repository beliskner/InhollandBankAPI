Feature: account test

  Scenario: Create a account
    When Create a acount
    Then is the status of the request 201


  Scenario: Get all acounts
    When i get all accounts
    Then is the status of the request 200

  Scenario: Get all accounts gives an ArrayOfAccount object
    When Get all accounts
    Then The response is an "ArrayOfAccount" object

  Scenario: Get all account by role employee gives ArrayOfAccount with 1 holder
    When Get all accounts by role "Employee"
    Then The accounts have role "Employee"
    And The array holds 1 accout

  Scenario: Delete a account
    When i do a status change to closed on account with iban "NL00INHO0000000002"
    Then is the status of the request 200


  Scenario: Update a minBalance
    When Update a minBalance van accounts "NL00INHO0000000002"
    Then is the status of the request 200

  Scenario: Update a maxTransfer
    When Update a maxTransfer of account with iban "NL00INHO0000000002"
    Then is the status of the request 200



