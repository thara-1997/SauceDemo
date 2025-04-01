package com.pragmatic.SauceDemo.base;

import com.pragmatic.SauceDemo.pages.LoginPage;
import com.pragmatic.SauceDemo.util.BrowserManager;
import com.pragmatic.SauceDemo.util.ConfigReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;

import static com.pragmatic.SauceDemo.util.LogManager.debug;


public class BaseTest {
    protected static WebDriver driver;
    protected SoftAssert softAssert;

    @BeforeMethod
    @Parameters({"chrome", "headless"})
    public void beforeMethod(@Optional("chrome") String browser, @Optional("false") boolean headless, Method method) {
        debug("Initializing the browser");
        driver = BrowserManager.getDriver(browser, headless);
        debug("Browser is initialized");
        driver.get(ConfigReader.getProperty("base.url"));
        softAssert = new SoftAssert();
        //if test method contain 'login', skip login
        if (!method.getName().toLowerCase().contains("login")) login();

    }

    @AfterMethod
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }
    }

    public void login() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.getProperty("demo.username"), ConfigReader.getProperty("demo.password"));
    }

    public byte[] takeScreenshot(){
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
        return screenshot;
        //
    }

}