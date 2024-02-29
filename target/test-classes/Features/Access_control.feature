@regresion
Feature: Access control user management
    #    Use cases for app access control

  @valid_login @smoke_test
  Scenario: As a user, I should be able to login
    Given I am on the login page
    And login page components exist
    When I enter valid username and valid password
    And I click login button
    Then I should be on the Dashboard page
    And the success message displays


  @valid_login_variable @smoke_test
   Scenario: As a user, I should be able to login
   Given I am on the login page
   And login page components exist
   When I enter valid username and valid password
   And I click login button
   Then I should be on the " Dashboard" page
   And "Success!" message displays
   
   
   
   
  
   
   
   @invalid_login_outline
   Scenario Outline: As a user i should not be able to login with invalid credentials
   Given I am on the login page
   When i enter invalid username "<username>" and password "<password>"
   And I click login button 
   Then user should not be logged in 
   
   Examples:
   |username                     |password        |
   |notrael@prim.com             |notrael         |
   |notrael@prim.com             |                |
   |dummy@primetechschool.com    |notrael         |
   |                             |primetech@school|
   |                             |                |
   |                             |notrael         |
   |notrael@prim.com             |primetech@school|
   |dummy@primetechschool.com    |                |