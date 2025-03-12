Feature: Check navigation menu
  As a valid user I should be able to access the navigation menu and should be able to navigate by using menu options

  Scenario: verify the burger menu functionality
    Given user has logged within the valid user credentials
    When user has clicked the burger menu
    Then user should need to access the menu bar

  Scenario: verify logout feature functionality
    Given user has logged within the valid user credentials
    When user has clicked the burger menu
    And  user has clicked the logout option
    Then user should navigate to the login page

  Scenario: verify All Items functionality
    Given user has logged within the valid user credentials
    When user has clicked the burger menu
    And user has clicked all items option in the menu
    Then user should be able to access the product list page

  Scenario: verify about option in the menu
    Given user has logged within the valid user credentials
    When user has clicked the burger menu
    And user has clicked about option in the menu
    Then user should be able to access the about page