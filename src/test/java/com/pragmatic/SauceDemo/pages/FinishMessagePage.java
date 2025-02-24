package com.pragmatic.SauceDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FinishMessagePage {

    private final WebDriver driver;

    private final By byFinishMsg = By.xpath("//h2[normalize-space()='Thank you for your order!']");

    public FinishMessagePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getFinishMsg(){
        return driver.findElement(byFinishMsg).getText();
    }
}
