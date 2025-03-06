Feature: Check the product list page functionalities
  As a valid user I should able access the product list page and access the product list page functionalities

  Scenario: verify the product page
    Given user has logged with valid credentials
    When user has accessed the product
    Then user should be directed to the product page

  Scenario: checking AddToCart button click functionality for each product
    Given user has logged with valid credentials
    When click add to cart button in each product
    Then cart item count should need to increase

  Scenario: checking Remove button click functionality for each product
    Given user has logged with valid credentials
    When click add to cart button in each product
    And click remove button in each product
    Then all buttons should need to display with AddToCart

  Scenario: checking the product filter A to Z functionality
    Given user has logged with valid credentials
    When select the product filter "az"
    Then user should able to see filtered AToZ products

  Scenario: checking the product filter Z to A functionality
    Given user has logged with valid credentials
    When select the product filter to "za"
    Then user should able to see filtered ZToA products

  Scenario: checking the product filter high to low functionality
    Given user has logged with valid credentials
    When select the product filter in to "hilo"
    Then user should able to see filtered HighToLow products

  Scenario: checking the product filter low to high functionality
    Given user has logged with valid credentials
    When select the product filter in to the "lohi"
    Then user should able to see filtered LowToHigh products

