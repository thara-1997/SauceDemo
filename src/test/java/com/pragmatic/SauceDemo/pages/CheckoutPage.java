package com.pragmatic.SauceDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

    private final WebDriver driver;

    @FindBy(id = "first-name")
    WebElement firstName;

    @FindBy(id = "last-name")
    WebElement lastName;

    @FindBy(id = "postal-code")
    WebElement postalCODE;

    @FindBy(id = "continue")
    WebElement continueBTN;

    @FindBy(id = "cancel")
    WebElement cancerBtn;

    private final By byErrorMessage = By.xpath("//div[@class='error-message-container error']");

    public CheckoutPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CheckoutPage typeFirstName(String firstname){
        firstName.sendKeys(firstname);
        return this;
    }

    public CheckoutPage typeLastName(String lastname){
        lastName.sendKeys(lastname);
        return this;
    }

    public CheckoutPage typePostalCode(String postalCode){
        postalCODE.sendKeys(postalCode);
        return this;
    }

    public void clickContinueBtn(){
        continueBTN.click();
    }

    public void clickCancelBtn(){
        cancerBtn.click();
    }

    public String getErrorMsg(){
        return driver.findElement(byErrorMessage).getText();
    }
}
