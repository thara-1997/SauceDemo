package com.pragmatic.SauceDemo.steps;

import com.pragmatic.SauceDemo.pages.CartPage;
import com.pragmatic.SauceDemo.pages.LoginPage;
import com.pragmatic.SauceDemo.pages.ProductListPage;
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

import java.util.Arrays;
import java.util.List;

public class CartSteps {
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
    @Given("user has logged with the valid credentials")
    public void userHasLoggedWithTheValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername("standard_user");
        loginPage.typePassword("secret_sauce");
        loginPage.clickLoginBtn();
    }

    @When("user has add the product to the cart")
    public void addTheProductToTheCart() {
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.addSauceLabsBackPack();
    }

    @Then("in cart page correct product should need to display")
    public void inCartPageCorrectProductShouldNeedToDisplay() {
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getItemDescription(),"carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.",
                "Item Description is not matching");
    }

    @When("user has add the multiple products to the cart")
    public void addTheMultipleProductsToTheCart() {
        ProductListPage productListPage = new ProductListPage(driver);
        productListPage.addSauceLabsBackPack();
        productListPage.addSauceLabsBikeLight();
        productListPage.clickCart();
    }

    @Then("in cart page correct products should need to display")
    public void inCartPageCorrectProductsShouldNeedToDisplay() {
        CartPage cartPage = new CartPage(driver);
        List<String> expectedCartItems = Arrays.asList("Sauce Labs Backpack","Sauce Labs Bike Light");
        for (int i =0; i<cartPage.getCartItemsCount(); i++){
            String cartItemName = cartPage.getCartItems().get(i).getText();
            String expectedProductName = expectedCartItems.get(i);
            Assert.assertEquals(cartItemName,expectedProductName,"Cart Items are not matching");
        }
    }

    @And("user has click remove button in cart item")
    public void clickRemoveButtonInCartItem() {
        CartPage cartPage =  new CartPage(driver);
        cartPage.clickRemoveSauceLabs();
    }

    @Then("cart item count should need to decrease")
    public void cartItemCountShouldNeedToDecrease() {
        CartPage cartPage =  new CartPage(driver);
        Assert.assertEquals(cartPage.getCartItemsCount(),1,"Cart Item count is not matching");
    }

    @And("user has click the continue shopping button")
    public void clickTheContinueShoppingButton() {
        CartPage cartPage = new CartPage(driver);
        cartPage.clickContinueShopping();
    }

    @Then("user should need to navigate to the product list page")
    public void userShouldNeedToNavigateToTheProductListPage() {
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/inventory.html"));
    }

    @And("user has click checkout button")
    public void clickCheckoutButton() {
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
    }

}
