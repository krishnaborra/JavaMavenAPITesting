@wip
Feature: Testing Remove Car and Fuel API
  Users should be able to submit POST requests to remove Company car and fuel
  using WireMock

  Scenario: Testing Remove Car and Fuel API
    Given user starts wiremock server
    When user configures stubs for nino NINO for taxyear 2018 for removeCarFuel with POST request
    Then the server should handle post for nino NINO for taxyear 2018 for removeCarFuel and return success status
    And user stops wiremock server
