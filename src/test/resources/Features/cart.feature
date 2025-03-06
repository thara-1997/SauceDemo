Feature: check the cart page functionalities
  As a valid user I should able add the products cart and access the cart page functionalities

  Scenario: verify the correct product added to the cart
    Given user has logged with the valid credentials
    When add the product to the cart
    Then in cart page correct product should need to display