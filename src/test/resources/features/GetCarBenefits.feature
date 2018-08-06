@wip
Feature: Testing GET Car Benefits API
  Users should be able to submit GET request and view the data for Car Benefits
  using WireMock

  Scenario: Testing GET Car Benefits API
    Given user starts wiremock server
    When user configures stubs for nino NINO for taxyear 2018 for getCarBenefits with GET request
    Then the server should handle get for nino NINO for taxyear 2018 for getCarBenefits and return success status
    And user stops wiremock server