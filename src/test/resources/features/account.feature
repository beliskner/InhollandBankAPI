Feature: account test

  Scenario: Create a account
    When Create a acount
    Then is de status van de request 201

#  Scenario: Get all acounts
#    When ik all accounts ophaal
#    Then krijg ik een list van accounts

  Scenario: Delete a account
    When ik een account status veranderd naar "CLOSED"
    Then The request status code is 200


  Scenario: Update a minBalance
    When Update a minBalance
    Then is de status van de request 200

#  Scenario: Update a maxTransfer
#    When Update a maxTransfer
#    Then is de status van de request 200

