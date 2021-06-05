Feature: account test

  Scenario: Create a account
    When Create a acount
    Then is de status van de request 201


  Scenario: Get all acounts
    When ik all accounts ophaal
    Then The request status code is 200

  Scenario: Delete a account
    When ik een account status veranderd naar Closed met als iban "NL00INHO0000000004"
    Then The request status code is 200


  Scenario: Update a minBalance
    When Update a minBalance van accounts "NL00INHO0000000004"
    Then is de status van de request 200

  Scenario: Update a maxTransfer
    When Update a maxTransfer van account met iban "NL00INHO0000000004"
    Then is de status van de request 200



