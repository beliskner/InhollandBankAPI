Feature: Holder test

  Scenario: Get all holders gives status code 200
    When Get all holders
    Then The request status code is 200

  Scenario: Get all holders gives an ArrayOfHolders object
    When Get all holders
    Then The response is an "ArrayOfHolders" object

  Scenario: Get all holders by role employee gives ArrayOfHolders with 1 holder
    When Get all holders by role "Employee"
    Then The holders have role "Employee"
    And The array holds 1 holders

  Scenario: Get all holders by first name search gives list of correlating holders
    When Get all holders by first name "in"
    Then The holders have "in" in their first name
    And The array holds 2 holders

  @allow-rescue
  Scenario: Make sure customer can't execute employee calls
    When Getting all holders as customer
    Then The bad request status code is 403

  Scenario: Create a holder
    When Create a holder
    Then The request status code is 201

  Scenario: Make sure holder can't be created if email already exists
    When Failing to create a holder with email "bank@inholland.nl"
    Then The request status code is 422

  Scenario: Get holder by id
    When Getting a holder with id 1
    Then The request status code is 200
    And The holder email is "bank@inholland.nl"

  Scenario: Update holder by id
    When Update holder with id 1 to first name
    Then The request status code is 200

  Scenario: Delete holder by id
    When Delete holder by "id"
    Then The request status code is 200

  Scenario: Get accounts by holder id
    When Get accounts by holder id
    Then The request status code is 200

  Scenario: Update daily limit by holder id
    When Update daily limit by holder "id"
    Then The request status code is 200

  Scenario: Update holder status by id
    When Update holder status by "id"
    And Holder status is "Open"
    Then The request status code is 200
    And Holder status is