Feature: Use login
  As a valid user I should be able to login to the system with
  valid credentials and view product inventory page

  Scenario: login with valid credentials
    Given user has accessed the login page
    When user provide valid credentials
    Then the user should be directed to product inventory page

  Scenario: Login with invalid password
    Given user has accessed the login page
    When user has entered invalid credentials "standard_user", "invalid"
    Then the user should be see the error message "Epic sadface: Username and password do not match any user in this service"


  Scenario Outline: login with invalid credentials
    Given user has accessed the login page
    When user has entered invalid credentials "<username>", "<password>"
    Then the user should be see the error message "<expectedError>"
    Examples:
      | username        | password  | expectedError                                                               |
      | standard_user | invalid | Epic sadface: Username and password do not match any user in this service |
      |               |          | Epic sadface: Username is required                                                                         |
      |               |secret_sauce | Epic sadface: Username is required                                                                      |
      |standard_user  |             |Epic sadface: Password is required                                                                       |






