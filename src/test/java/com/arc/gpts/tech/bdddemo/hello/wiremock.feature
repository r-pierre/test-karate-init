Feature: WireMock

  Background:
    * url wiremockUrl

  Scenario: le mock WireMock répond avec le stub de ping
    Given path 'api', 'ping'
    When method get
    Then status 200
    And match response == { status: 'UP' }
