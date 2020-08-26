Feature: Checking response

  Background:
    * url 'http://localhost:8081/'
    * header Accept = 'application/json'
#    * url demoBaseUrl

  Scenario: get list of movies

    Given path '/movieservice/v1/allMovies'
    When method get
    Then status 200
    And def userResponse = response
    * print 'userResponse', userResponse
    Then match userResponse[*].name contains ['Batman Begins']

#  Scenario: get custom greeting
#
#    Given path 'greeting'
#    And param name = 'Billie'
#    When method get
#    Then status 200
#    And match response == { id: '#number', content: 'Hello Billie!' }
