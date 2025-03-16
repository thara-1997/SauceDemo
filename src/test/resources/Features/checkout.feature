Feature: check the checkout page functionality
  As a valid user I should be able to access the checkout page functionalities

  Scenario Outline: verify invalid checkout information functionality
    Given user has logged with e valid credentials
    And user has added the products to the cart,access the cart page and click the checkout button
    When user has entered invalid user information "<firstname>","<lastname>", "<postalCode>"
    Then user should be see the error message "<expectedError>"


    Examples:
    | firstname | lastname | postalCode| expectedError  |
    |           |          |           |Error: First Name is required |
    |Thathsarani|          |           |Error: Last Name is required  |
    |Thathsarani|Sudusinghe |          |Error: Postal Code is required |
    |Thathsarani|            |82100    |Error: Last Name is required   |


    Scenario: verify valid checkout information functionality
      Given user has logged with e valid credentials
      And user has added the products to the cart,access the cart page and click the checkout button
      When user has entered valid checkout details
      Then user should be abele to navigate to the checkout overview page

    Scenario: verify order summary page functionality
      Given user has logged with e valid credentials
      And user has added the products to the cart,access the cart page and click the checkout button
      When user has entered valid checkout details
      Then user should able to access the products inside the overview page

    Scenario: verify finish button functionality
      Given user has logged with e valid credentials
      And user has added the products to the cart,access the cart page and click the checkout button
      When user has entered valid checkout details
      And user has clicked the finish button
      Then user should able to see the finish message

    Scenario: verify cancel button functionality
      Given user has logged with e valid credentials
      And user has added the products to the cart,access the cart page and click the checkout button
      When user has clicked the cancel button
      Then user should able to navigate to the cart page






