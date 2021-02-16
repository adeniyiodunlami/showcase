Feature: checkout process
  In order to view the dashboard
  As a user
  I want to be able to login into the V6 tenant

Scenario: login with valid credentials
  Given I navigate to the Login page
  Given I Login with email and Password
      | Email         | Password |
      | test@test.com | admin    |
  When I press "Continue"
  Then I should be on the tenant space
  