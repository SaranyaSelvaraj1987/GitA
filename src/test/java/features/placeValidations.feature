Feature: Add Place API Validations
  I want to use this template for my AddPlace API Validations file

  Scenario: AddPlace
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls AddPlaceAPI with POST HTTP request
		Then the API call is success with status code 200
		And the "status" response body is "OK"
		And the "scope" response body is "APP"

Examples:
		|name    |language     |address         |
		|Saran23 |Telugu       |Bangalore       |
		|Leela12 |Tamil        |Coimbatore      |
		|Bavith  |Telugu       |Bangalore       |
		