package com.pragmatic.SauceDemo.steps;

import com.pragmatic.SauceDemo.pages.LoginPage;
import com.pragmatic.SauceDemo.pages.NavigationAndLogoutPage;
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

public class NavigationAndLogoutSteps {
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
    @Given("user has logged within the valid user credentials")
    public void userHasLoggedWithinTheValidUserCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername("standard_user");
        loginPage.typePassword("secret_sauce");
        loginPage.clickLoginBtn();
    }

    @When("user has clicked the burger menu")
    public void userHasClickedTheBurgerMenu() {
        NavigationAndLogoutPage navigationAndLogoutPage = new NavigationAndLogoutPage(driver);
        navigationAndLogoutPage.burgerMenuClick();
    }

    @Then("user should need to access the menu bar")
    public void userShouldNeedToAccessTheMenuBar() {
        NavigationAndLogoutPage navigationAndLogoutPage = new NavigationAndLogoutPage(driver);
        Assert.assertTrue(navigationAndLogoutPage.getMenu(), "The menu is not visible");
    }

    @And("user has clicked the logout option")
    public void userHasClickedTheLogoutOption() {
        NavigationAndLogoutPage navigationAndLogoutPage = new NavigationAndLogoutPage(driver);
        navigationAndLogoutPage.clickLogout();
    }

    @Then("user should navigate to the login page")
    public void userShouldNavigateToTheLoginPage() {
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/"));
    }

    @And("user has clicked all items option in the menu")
    public void userHasClickedAllItemsOptionInTheMenu() {
        NavigationAndLogoutPage navigationAndLogoutPage = new NavigationAndLogoutPage(driver);
        navigationAndLogoutPage.clickAllItems();
    }

    @Then("user should be able to access the product list page")
    public void userShouldBeAbleToAccessTheProductListPage() {
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://www.saucedemo.com/inventory.html"));
    }

    @And("user has clicked about option in the menu")
    public void userHasClickedAboutOptionInTheMenu() {
        NavigationAndLogoutPage navigationAndLogoutPage = new NavigationAndLogoutPage(driver);
        navigationAndLogoutPage.clickAbout();
    }

    @Then("user should be able to access the about page")
    public void userShouldBeAbleToAccessTheAboutPage() {
        Assert.assertTrue(driver.getCurrentUrl().startsWith("https://saucelabs.com/"));
    }
}
