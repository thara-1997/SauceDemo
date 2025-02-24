package com.pragmatic.SauceDemo.pages;

import jdk.internal.org.jline.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    @FindBy(id = "user-name")
    WebElement username;

    @FindBy(id ="password")
    WebElement password;

    @FindBy(id = "login-button")
    WebElement loginBtn;

    @FindBy(css = "h3[data-test='error']")
    WebElement textError;

    @FindBy(id = "user-name")
    WebElement usernamePlaceholder;

    @FindBy(id = "password")
    WebElement passwordPlaceholder;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage typeUsername(String userName){
        username.sendKeys(userName);
        return this;
    }

    public LoginPage typePassword(String pwd){
        password.sendKeys(pwd);
        return this;
    }

    public void clickLoginBtn(){
        loginBtn.click();
    }

    public String getError(){
        return textError.getText();
    }

    public String getUsernamePlaceholder(){
        return usernamePlaceholder.getDomAttribute("placeholder");
    }

    public String getPasswordPlaceholder(){
       return passwordPlaceholder.getDomAttribute("placeholder");
    }

    public void login(String userName, String pwd){
        typeUsername(userName).typePassword(pwd).clickLoginBtn();
    }
}
