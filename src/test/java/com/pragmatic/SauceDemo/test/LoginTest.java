package com.pragmatic.SauceDemo.test;

import com.pragmatic.SauceDemo.pages.LoginPage;
import com.pragmatic.SauceDemo.pages.ProductListPage;
import com.pragmatic.SauceDemo.util.DataDrivenProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest {

    WebDriver driver;
    @BeforeMethod
    public void beforeMethod(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }
    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

    @Test
    public void testVerifyLoginWithValidCredentials(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername("standard_user").typePassword("secret_sauce").clickLoginBtn();

        ProductListPage productListPage = new ProductListPage(driver);
        Assert.assertEquals(productListPage.getTitle(),"Products","Expected result not met");



    }

    @Test(dataProvider ="login-credentials", dataProviderClass = DataDrivenProvider.class ,description = "verifyInvalidLogin")
    public void testLoginWithInvalidCredentials(String username, String password, String expectedError){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername(username).typePassword(password).clickLoginBtn();
        Assert.assertEquals(loginPage.getError(),expectedError,"Expected result not met");
    }

    @Test
    public void testVerifyLoginWithLockedOutUserCredentials(){
        LoginPage loginPage = new  LoginPage(driver);
        loginPage.typeUsername("locked_out_user").typePassword("secret_sauce").clickLoginBtn();
        Assert.assertEquals(loginPage.getError(),"Epic sadface: Sorry, this user has been locked out.", "Expected results not met");
    }

    @Test
    public void testVerifyLoginWithPerformanceGlitchUser(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername("performance_glitch_user").typePassword("secret_sauce").clickLoginBtn();

        ProductListPage productListPage = new ProductListPage(driver);
        Assert.assertEquals(productListPage.getTitle(),"Products","Expected result not met");
    }

    @Test
    public void testVerifyOfUsernameAndPassword(){
        LoginPage loginPage = new LoginPage(driver);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(loginPage.getUsernamePlaceholder(), "Username", "Username placeholder is not matching");
        softAssert.assertEquals(loginPage.getPasswordPlaceholder(),"Password", "Password placeholder is not matching");
        softAssert.assertAll();
    }
}
