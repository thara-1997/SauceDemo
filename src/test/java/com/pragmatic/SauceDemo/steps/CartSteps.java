package com.pragmatic.SauceDemo.steps;

import com.pragmatic.SauceDemo.pages.CartPage;
import com.pragmatic.SauceDemo.pages.LoginPage;
import com.pragmatic.SauceDemo.pages.ProductListPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

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

    @When("add the product to the cart")
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
}
