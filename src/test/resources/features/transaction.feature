Feature: transactions test

  Scenario: Create a transaction between accounts
    When Create a transaction between accounts
    Then is the status of the request 201

  Scenario: Create a transaction for deposit
    When Create a transaction for deposit
    Then is the status of the request 201

  Scenario: Create a transaction for widrawl
    When Create a transaction for widrawl
    Then is the status of the request 201

Scenario: Get all transactions
When i get all transactions
Then is the status of the request 200

  Scenario: Get transactions by id
    When i get transactions by id is 5
    Then is the status of the request 200

  Scenario: Get tan transactions by id
    When i get tan transactions by id is 5
    Then is the status of the request 200

  Scenario: verify transaction by tan
    When i verify transaction by tan
    Then is the status of the request 200

Scenario: Get all transactions gives an ArrayOfTransactions object
When Get all transactions
Then The response is an "ArrayOfTransactions" object