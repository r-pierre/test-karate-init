Feature: API Hello

  Background:
    * url baseUrl

  Scenario: saluer une personne par son nom
    Given path 'api', 'hello'
    And param name = 'Karate'
    When method get
    Then status 200
    And match response == { message: 'Bonjour Karate' }

  Scenario: saluer sans nom
    Given path 'api', 'hello'
    When method get
    Then status 200
    And match response.message == 'Bonjour World'

  Scenario: une ressource inconnue renvoie une erreur au format ProblemDetail
    Given path 'api', 'inconnu'
    When method get
    Then status 404
    And match responseHeaders['Content-Type'][0] contains 'application/problem+json'
    And match response contains { status: 404, title: 'Not Found', instance: '/api/inconnu' }
