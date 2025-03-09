Feature: check the cart page functionalities
  As a valid user I should able add the products cart and access the cart page functionalities

  Scenario: verify the correct product added to the cart
    Given user has logged with the valid credentials
    When add the product to the cart
    Then in cart page correct product should need to display

  Scenario: verify the multiple products are added to the cart
    Given user has logged with valid credentials
    When add the multiple products to the cart
    Then in cart page correct products should need to display

  Scenario: verify remove the cart item functionality
    Given user has logged with valid credentials
    When add the multiple products to the cart
    And click remove button in cart item
    Then cart item count should need to decrease

  Scenario: verify the continue shopping button functionality in cart page
    Given user has logged with valid credentials
    When add the multiple products to the cart
    And click the continue shopping button
    Then user should need to navigate to the product list page

  Scenario: verify the checkout button functionality in the cart page
    Given user has logged with valid credentials
    When add the multiple products to the cart
    And click checkout button
    Then user should need to navigate to the checkout your information page



