package com.pragmatic.SauceDemo.steps;

import com.pragmatic.SauceDemo.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class CheckoutSteps {
    WebDriver driver;
    @Before
    public void before(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }
    @After
    public void after(Scenario scenario){
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "name");
        }
        driver.quit();
    }
    @Given("user has logged with e valid credentials")
    public void userHasLoggedWithTeValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername("standard_user");
        loginPage.typePassword("secret_sauce");
        loginPage.clickLoginBtn();
    }

    @And("user has added the products to the cart,access the cart page and click the checkout button")
    public void addTheProductsToTheCartAccessTheCartPageAndClickTheCheckoutButton() {
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.addSauceLabsBackPack();
        productListPage.addSauceLabsBikeLight();
        productListPage.clickCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
    }

    @When("user has entered invalid user information {string},{string}, {string}")
    public void userHasEnteredInvalidUserInformation(String firstname, String lastname, String postalCode) {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.typeFirstName(firstname).typeLastName(lastname).typePostalCode(postalCode).clickContinueBtn();
    }

    @Then("user should be see the error message {string}")
    public void userShouldBeSeeTheErrorMessage(String expectedError) {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Assert.assertEquals(checkoutPage.getErrorMsg(),expectedError,"Error message is incorrect");
    }

    @When("user has entered valid checkout details")
    public void userHasEnteredValidCheckoutDetails() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.typeFirstName("firstname").typeLastName("lastName").typePostalCode("postalCode").clickContinueBtn();
    }

    @Then("user should be abele to navigate to the checkout overview page")
    public void userShouldBeAbeleToNavigateToTheCheckoutOverviewPage() {
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/checkout-step-two.html"));
    }

    @Then("user should able to access the products inside the overview page")
    public void userShouldAbleToAccessTheProductsInsideTheOverviewPage() {
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        List<String> expectedCartItems = Arrays.asList("Sauce Labs Backpack","Sauce Labs Bike Light");
        SoftAssert softAssert = new SoftAssert();

        for (int i = 0; i< checkoutOverviewPage.getCartItemCount(); i++){
            String cartItemName = checkoutOverviewPage.getCartItems().get(i).getText();
            String expectedProductName = expectedCartItems.get(i);
            softAssert.assertEquals(cartItemName, expectedProductName, "Cart Items are not matching");
        }

        softAssert.assertEquals(checkoutOverviewPage.getTotal(),"Total: $43.18", "Total Value is not matching");
        softAssert.assertAll();
    }

    @And("user has clicked the finish button")
    public void userHasClickedTheFinishButton() {
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutOverviewPage.clickFinishBtn();
    }

    @Then("user should able to see the finish message")
    public void userShouldAbleToSeeTheFinishMessage() {
        FinishMessagePage finishMessagePage = new FinishMessagePage(driver);
        Assert.assertEquals(finishMessagePage.getFinishMsg(), "Thank you for your order!", "Thank you for your order!");
    }

    @When("user has clicked the cancel button")
    public void userHasClickedTheCancelButton() {
        CheckoutPage checkoutPage =  new CheckoutPage(driver);
        checkoutPage.clickCancelBtn();
    }

    @Then("user should able to navigate to the cart page")
    public void userShouldAbleToNavigateToTheCartPage() {
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/cart.html"));
    }
}
