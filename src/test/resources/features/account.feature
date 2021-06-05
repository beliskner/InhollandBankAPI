Feature: account test

  Scenario: Create a account
    When Create a acount
    Then is the status of the request 201


  Scenario: Get all acounts
    When i get all accounts
    Then is the status of the request 201

  Scenario: Get all holders gives an ArrayOfHolders object
    When Get all holders
    Then The response is an "ArrayOfHolders" object

  Scenario: Get all holders by role employee gives ArrayOfHolders with 1 holder
    When Get all holders by role "Employee"
    Then The holders have role "Employee"
    And The array holds 1 holders

  Scenario: Delete a account
    When i do a status change to closed on account with iban "NL00INHO0000000002"
    Then is the status of the request 201


  Scenario: Update a minBalance
    When Update a minBalance van accounts "NL00INHO0000000002"
    Then is the status of the request 201

  Scenario: Update a maxTransfer
    When Update a maxTransfer of account with iban "NL00INHO0000000002"
    Then is the status of the request 201



