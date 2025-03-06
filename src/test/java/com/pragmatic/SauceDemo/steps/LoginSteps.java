package com.pragmatic.SauceDemo.steps;

import com.pragmatic.SauceDemo.pages.LoginPage;
import com.pragmatic.SauceDemo.pages.ProductListPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LoginSteps {
    WebDriver driver;

    @Before
    public void before(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

//    @After
//    public void after(Scenario scenario){
//        if (scenario.isFailed()) {
//            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//            scenario.attach(screenshot, "image/png", "name");
//        }
//        driver.quit();
//    }
    @Given("user has accessed the login page")
    public void userHasAccessedTheLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("user provide valid credentials")
    public void userProvideValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername("standard_user");
        loginPage.typePassword("secret_sauce");
        loginPage.clickLoginBtn();
    }

    @Then("the user should be directed to product inventory page")
    public void theUserShouldBeDirectedToProductInventoryPage() {
        ProductListPage productListPage = new ProductListPage(driver);
        Assert.assertEquals(productListPage.getTitle(),"Products", "Products text not meet");
    }

    @When("user has entered invalid credentials {string}, {string}")
    public void userHasEnteredInvalidCredentials(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername(username);
        loginPage.typePassword(password);
        loginPage.clickLoginBtn();
    }

    @Then("the user should be see the error message {string}")
    public void theUserShouldBeSeeTheErrorMessage(String expectedError) {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.getError(), expectedError, "error message is not correct");
    }
}
