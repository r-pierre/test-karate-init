Feature: Person API

  Background:
    * url baseUrl
    * def reference = 'REF-' + Java.type('java.util.UUID').randomUUID()
    * def person = { firstName: 'Jean', lastName: 'Dupont', reference: '#(reference)' }

  Scenario: créer une personne
    Given path 'api', 'v1', 'person'
    And request person
    When method post
    Then status 201
    And match response == person

  Scenario: créer une personne avec une référence déjà existante est refusé
    Given path 'api', 'v1', 'person'
    And request person
    When method post
    Then status 201

    Given path 'api', 'v1', 'person'
    And request person
    When method post
    Then status 406
    And match response contains { status: 406, detail: '#("Person with reference " + reference + " already exists")' }
